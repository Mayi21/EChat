package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
	private int port = 12345;

	private String url = "localhost";

	private Channel channel;

	private Bootstrap bootstrap;
	private void bind(String msg) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			bootstrap = new Bootstrap();
			bootstrap.group(group)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							ChannelPipeline pipeline = socketChannel.pipeline();
							pipeline.addLast(new StringDecoder());
							pipeline.addLast(new StringEncoder());
							pipeline.addLast(new NettyClientHandler());
						}
					});
			channel = bootstrap.connect(url, port).sync().channel();
			channel.writeAndFlush(msg + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMsg(String msg) {
		bind(msg);
//		while (!channel.isWritable()) {
//			try {
//				Thread.sleep(800);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		channel.writeAndFlush(msg + "\n");

	}
}
class NettyClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		super.channelUnregistered(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
		System.out.println("client received: " + s);
	}
}
