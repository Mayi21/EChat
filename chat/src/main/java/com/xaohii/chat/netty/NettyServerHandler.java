package com.xaohii.chat.netty;

import com.alibaba.fastjson.JSON;
import com.xaohii.chat.repository.entity.po.MessagePo;
import com.xaohii.chat.service.MessageServiceImpl;
import com.xaohii.chat.utils.common.P2vUtil;
import com.xaohii.chat.utils.common.SpringContextUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class NettyServerHandler extends SimpleChannelInboundHandler<Message> {
	private NettyServer nettyServer;

	public NettyServerHandler(NettyServer nettyServer){
		this.nettyServer = nettyServer;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
		switch (message.getType()) {
			case 0:
				nettyServer.writeMsg(message);
				break;
			case 1:
				// 新用户上线后
				Long userId = message.getUserId();
				nettyServer.setChannel(userId, channelHandlerContext.channel());
				nettyServer.setOnlineIdToNameMap(userId, message.getUserName());
				System.out.println(userId + "注册成功");
				nettyServer.notifyOnlineMemChange();
				channelHandlerContext.writeAndFlush(nettyServer.getNotifyMessage());
				break;
			case 2:


		}
	}


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		/**
		 * 在收到新的链接后，先把当前在线的人员发给客户端
		 * */
		Channel channel = ctx.channel();
		String clientName = channel.remoteAddress().toString();
		System.out.println("RemoteAddress:"+clientName+"active!");
		nettyServer.setClientName2ChannelMap(clientName, channel);
		// 获取当前在线的用户，发送给新上线的用户
		channel.writeAndFlush(nettyServer.getNotifyMessage());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel channel = ctx.channel();
		channel.close();
		super.exceptionCaught(ctx, cause);
	}
}