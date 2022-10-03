package v2;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

public class NettyClientHandler extends SimpleChannelInboundHandler<Message>{
	private NettyClient nettyClient;

	public NettyClientHandler(NettyClient nettyClient){
		this.nettyClient = nettyClient;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) {
		switch (message.getType()) {
			case 0:
				System.out.printf("收到一条来自 %d.%s 的消息：%s%n", message.getUserId(),
						message.getUserName(), message.getMessage());
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				String s = message.getMessage();
				Map<Integer, String> map = (Map<Integer, String>) JSON.parseObject(s, Map.class);
				nettyClient.setMap(map);
				break;
		}

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String clientName = ctx.channel().remoteAddress().toString();
		System.out.println("RemoteAddress:"+clientName+"active!");
		nettyClient.setChannel(clientName, ctx.channel());
		super.channelActive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		System.out.println(cause.getMessage());
//		super.exceptionCaught(ctx, cause);
	}
}
