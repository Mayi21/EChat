package v2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<Message>{
	private NettyClient nettyClient;

	public NettyClientHandler(NettyClient nettyClient){
		this.nettyClient = nettyClient;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
		System.out.println(message.toString());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String clientName = ctx.channel().remoteAddress().toString();
		System.out.println("RemoteAddress:"+clientName+"active!");
		Message message = new Message();
		message.setType(1);
		message.setSourceName("1");
		ctx.writeAndFlush(message);
		nettyClient.setChannel(clientName, ctx.channel());
		super.channelActive(ctx);
	}
}
