package v2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.Set;

public class NettyClientHandler extends SimpleChannelInboundHandler<Message>{
	private NettyClient nettyClient;

	public NettyClientHandler(NettyClient nettyClient){
		this.nettyClient = nettyClient;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) {
		switch (message.getType()) {
			case 0:
				System.out.printf("%s 收到一条来自 %s 的消息：%s%n", message.getToUserName(),
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
}
