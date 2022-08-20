package v2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class NettyServerHandler extends SimpleChannelInboundHandler<String>{
	private int counter=0;
	private NettyServer nettyServer;

	public NettyServerHandler(NettyServer nettyServer){
		this.nettyServer = nettyServer;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
		System.out.println("client say" + msg);
		//重置心跳次数
		counter = 0;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String clientName = ctx.channel().remoteAddress().toString();
		System.out.println("RemoteAddress:"+clientName+"active!");
		nettyServer.setClient(clientName);
		nettyServer.setChannel(clientName,ctx.channel());
		super.channelActive(ctx);
		counter = 0;
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent){
			IdleStateEvent event = (IdleStateEvent)evt;
			if (event.state().equals(IdleState.READER_IDLE)){
				//空闲4s后触发
				if (counter>=10){
					ctx.channel().close().sync();
					String clientName = ctx.channel().remoteAddress().toString();
					System.out.println(""+clientName+"offline");
					nettyServer.removeClient(clientName);
					//判断是否有在线的
					if (nettyServer.getClientMapSize()){
						return;
					}
				}else{
					counter++;
					System.out.println("loss"+counter+"count HB");
				}
			}
		}
	}
}