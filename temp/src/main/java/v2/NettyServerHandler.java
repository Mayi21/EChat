package v2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<Message>{
	private NettyServer nettyServer;

	public NettyServerHandler(NettyServer nettyServer){
		this.nettyServer = nettyServer;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
		if (0 == message.getType()) {
			nettyServer.writeMsg(message);
		} else {
			String sourceName = message.getSourceName();
			nettyServer.setChannel(sourceName, channelHandlerContext.channel());
			System.out.println(sourceName + "注册成功");
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String clientName = ctx.channel().remoteAddress().toString();
		System.out.println("RemoteAddress:"+clientName+"active!");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}
}