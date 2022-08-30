package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Objects;

public class NettyServer {
	private final Logger LOG = LoggerFactory.getLogger(NettyServer.class);

	private int port = 12345;

	private ServerBootstrap bootstrap;

	private Channel ch;
	public void bind() {
		bootstrap = new ServerBootstrap();
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			bootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel channel) throws Exception {
							ch = channel;
							ChannelPipeline pipeline = channel.pipeline();
							pipeline.addLast(new NettyServerHandler());
						}
					});
			ch = bootstrap.bind(new InetSocketAddress(port)).sync().channel();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public void sendMsg(String msg) {
		bind();
		while (!ch.isWritable()) {
			try {
				Thread.sleep(800);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ch.writeAndFlush(msg);
	}
}
