package v2;

import java.util.HashMap;
import java.util.Map;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
public class NettyServer {
	private int port = 20803;

	//维护设备在线的表
	private Map<String, Integer> clientMap = new HashMap<>();

	//维护设备连接的map 用于推送消息
	private Map<String, Channel> channelMap = new HashMap<>();

	public synchronized void setChannel(String name, Channel channel) {
		this.channelMap.put(name, channel);
	}

	public synchronized Map<String, Channel> getChannelMap() {
		return this.channelMap;
	}

	//发送消息给下游设备
	public void writeMsg(Message msg) {
		Map<String, Channel> channelMap = getChannelMap();
		try {
			Channel channel = channelMap.get(msg.getToName());
			if (!channel.isActive()) {
				System.out.println("it's not online");
			}
			channel.writeAndFlush(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void bind() {
		System.out.println("service start successful");
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						ChannelPipeline pipeline = socketChannel.pipeline();
						pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
						pipeline.addLast("encoder", new ObjectEncoder());
						pipeline.addLast("handler", new NettyServerHandler(NettyServer.this));
					}
				});
		try {
			ChannelFuture f = bootstrap.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		final NettyServer nettyServer = new NettyServer();
		new Thread() {
			@Override
			public void run() {
				nettyServer.bind();
			}
		}.start();
//		Scanner scanner = new Scanner(System.in);
//		String msg = "";
//		while (!(msg = scanner.nextLine()).equals("exit")) {
//			Message message = new Message();
//			message.setSourceName("2");
//			message.setToName("1");
//			message.setMessage(msg.trim());
//			nettyServer.writeMsg(message);
//		}
	}
}