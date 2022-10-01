package v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyClient {
	private String host;

	private int port;

	private Map<Integer, String> noToUserName = new HashMap<>();

	private Channel channel;

	private Integer userId;

	private String userName;

	private Integer toUserId;

	private Map<String, Channel> channelMap = new HashMap<>();

	public synchronized void setChannel(String name, Channel channel) {
		this.channelMap.put(name, channel);
	}

	public void setMap(Map<Integer, String> map) {
		map.remove(userId);
		this.noToUserName = map;
	}

	public synchronized Map<String, Channel> getChannelMap() {
		return this.channelMap;
	}
	private Bootstrap b = new Bootstrap();

	public NettyClient(String host, int port) {
		this.host = host;
		this.port = port;
		init();
	}

	private void init() {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		b.group(workerGroup).option(ChannelOption.SO_KEEPALIVE, true)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						ChannelPipeline pipeline = socketChannel.pipeline();
						//字符串编码解码
						pipeline.addLast("encoder", new ObjectEncoder());
						pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
						//客户端的逻辑
						pipeline.addLast("handler", new NettyClientHandler(NettyClient.this));
					}
				});
	}

	public void start() {
		ChannelFuture f = b.connect(host, port);
		//断线重连
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture channelFuture) throws Exception {
				if (!channelFuture.isSuccess()) {
					final EventLoop loop = channelFuture.channel().eventLoop();
					loop.schedule(new Runnable() {
						@Override
						public void run() {
							System.out.println("not connect service");
							start();
						}
					}, 1L, TimeUnit.SECONDS);
				} else {
					channel = channelFuture.channel();
					System.out.println("connected");
				}
			}
		});
	}

	private void heartBeat() {
		Message message = new Message();
		message.setType(2);
		channel.writeAndFlush(message);
	}

	public Channel getChannel() {
		return channel;
	}

	public void sendMsg(Message message) {
		Map<String, Channel> channelMap = getChannelMap();
		for (String channelName : channelMap.keySet()) {
			try {
				Channel channel = channelMap.get(channelName);
				if (!channel.isActive()) {
					continue;
				}
				channel.writeAndFlush(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String hostName = "127.0.0.1";
		int port = 20803;
		NettyClient nettyClient = new NettyClient(hostName, port);
		threadManage(nettyClient);
		nettyClient.loginHandler();
		nettyClient.onlineUser();
		nettyClient.menu();
	}

	public void menu() {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.printf("欢迎【%s】登录%n", userName);
			mainMenu();
			toUserId = scanner.nextInt();
			if (toUserId == -1) {
				continue;
			}
			System.out.printf("开始和【%s】愉快聊天%n", noToUserName.get(toUserId));
			scanner.nextLine();
			while (true) {
				String line = scanner.nextLine();
				if ("q".equals(line.trim())) {
					break;
				}
				channel.writeAndFlush(getChatMessage(line));
			}
		}
	}


	public void mainMenu() {
		onlineUser();
		System.out.println("选择在线用户聊天:");
	}


	/**
	 * 构造正常发送的消息
	 * */
	public Message getChatMessage(String msg) {
		Message message = new Message();
		message.setType(0);
		message.setMessage(msg);
		message.setUserName(userName);
		message.setUserId(userId);
		message.setToUserId(toUserId);
		message.setToUserName(noToUserName.get(toUserId));
		return message;
	}

	/**
	 * 格式化输出当前在线的用户ID和用户名
	 * */
	public void onlineUser() {
		System.out.println("当前在在线的用户:");
		if (noToUserName.size() == 0) {
			System.out.println("omaga，当前没人在线");
		}
		for (Integer userId : noToUserName.keySet()) {
			System.out.printf("%d.%s%n", userId, noToUserName.get(userId));
		}
	}

	/**
	 * 获取注册消息
	 * 设置当前用户ID、当前用户名字
	 * */
	public Message getRegisterMessage() {
		Message message = new Message();
		message.setUserId(userId);
		message.setUserName(userName);
		message.setType(1);
		return message;
	}


	/**
	 * 处理登录逻辑
	 * 用户输入ID和用户名
	 * 如果当前ID不存在，即可登录成功
	 * 否则就一直输入直到登录成功。
	 * */
	public void loginHandler() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入编号和用户名(以,号分割)");
		String line = scanner.nextLine();
		String[] strings = line.split(",");
		int userId = Integer.parseInt(strings[0]);
		String userName = strings[1];
		while (noToUserName.containsKey(userId)) {
			System.out.println("当前用户ID已经存在,请重新输入编号和用户名(以,号分割)");
			line = scanner.nextLine();
			strings = line.split(",");
			userId = Integer.parseInt(strings[0]);
			userName = strings[1];
		}
		this.userId = userId;
		this.userName = userName;
		channel.writeAndFlush(getRegisterMessage());
	}

	/**
	 * 线程管理，里面存放了各种线程
	 * */
	public static void threadManage(NettyClient nettyClient) {
		Thread clientThread = new Thread() {
			@Override
			public void run() {
				nettyClient.start();
			}
		};
		clientThread.setName("客户端线程");
		clientThread.start();

//		Thread heartBeatThread = new Thread() {
//			@Override
//			public void run() {
//				nettyClient.heartBeat();
//				try {
//					Thread.sleep(5);
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}
//			}
//		};
//		heartBeatThread.setName("心跳发送线程");
//		heartBeatThread.start();
	}
}