package v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
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
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
	private String host;
	private int port;
	private Channel channel;

	private Map<String, Channel> channelMap = new HashMap<>();

	public synchronized void setChannel(String name, Channel channel) {
		this.channelMap.put(name, channel);
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
		Scanner scanner = new Scanner(System.in);
		String hostName = "127.0.0.1";
		int port = 20803;
		NettyClient nettyClient = new NettyClient(hostName, port);
		new Thread(){
			@Override
			public void run() {
				nettyClient.start();
			}
		}.start();
		while (true) {
			String line = scanner.nextLine();
			if ("\\q".equals(line.trim())) {
				break;
			}
			Message message = new Message();
			message.setSourceName("1");
			message.setToName("2");
			message.setMessage(line.trim());
			nettyClient.sendMsg(message);
		}
	}
}