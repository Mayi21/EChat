package v2;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
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

import com.xaohii.chat.netty.Message;

public class NettyServer {
	private int port = 20803;

	/**
	 * user id 到channel的映射
	 * */
	private Map<Long, Channel> channelMap = new HashMap<>();

	/**
	 * userid 到 username的映射
	 * */
	private Map<Long, String> onlineIdToNameMap = new HashMap<>();

	private Map<String, Channel> clientName2ChannelMap = new HashMap<>();

	public synchronized void setClientName2ChannelMap(String clientName, Channel channel) {
		clientName2ChannelMap.put(clientName, channel);
	}

	public Map<String, Channel> getClientName2ChannelMap() {
		return this.clientName2ChannelMap;
	}

	public synchronized void setOnlineIdToNameMap(Long userId, String userName) {
		onlineIdToNameMap.put(userId, userName);
	}

	public Map<Long, String> getOnlineIdToNameMap() {
		return onlineIdToNameMap;
	}


	public synchronized void setChannel(Long userId, Channel channel) {
		this.channelMap.put(userId, channel);
	}


	public Map<Long, Channel> getChannelMap() {
		return this.channelMap;
	}
	/**
	 * 上线通知
	 * 新用户上线后，需要将当前的在线进行更新
	 * */
	public void notifyOnlineMemChange() {
		for (String clientName : clientName2ChannelMap.keySet()) {
			Channel channel = clientName2ChannelMap.get(clientName);
			channel.writeAndFlush(getNotifyMessage());
		}
	}

	public Message getNotifyMessage() {
		String msg = JSON.toJSONString(onlineIdToNameMap);
		Message message = new Message();
		message.setMessage(msg);
		message.setType(3);
		return message;
	}

	//发送消息给下游设备
	public void writeMsg(Message msg) {
		Map<Long, Channel> channelMap = getChannelMap();
		try {
			Channel channel = channelMap.get(msg.getToUserId());
			if (!channel.isActive()) {
				System.out.println("it's not online");
				channelMap.remove(msg.getToUserId());
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
	}
}