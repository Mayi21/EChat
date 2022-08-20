package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<String> {
	//Inbound是对于进来的请求的处理，OutBound是对于return返回的处理但是底层都是Adapter实现的，和Servlet一样
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		//ChannelHandlerContext是上下文，msg是client发过来的信息
		//输出远程客户端的地址和消息
		System.out.println(ctx.channel().remoteAddress() + "," + msg);
		ctx.channel().writeAndFlush("from server:" + msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}