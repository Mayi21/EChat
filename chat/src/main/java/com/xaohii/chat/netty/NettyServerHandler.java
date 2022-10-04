package com.xaohii.chat.netty;

import com.alibaba.fastjson.JSON;
import com.xaohii.chat.utils.common.SpringContextUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Application;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
//@ChannelHandler.Sharable
//public class NettyServerHandler extends SimpleChannelInboundHandler<Message> {
//	private Map<Long, Channel> id2Channel = new HashMap<>();
//	private Map<Long, String> id2UserName = new HashMap<>();
//
//	private Map<String, Channel> client2Channel = new HashMap<>();
//
//	private void setId2Channel(Long id, Channel channel) {
//		id2Channel.put(id, channel);
//	}
//
//	private void setId2UserName(Long id, String userName) {
//		id2UserName.put(id, userName);
//	}
//
//	private void setClient2Channel(String clientName, Channel channel) {
//		client2Channel.put(clientName, channel);
//	}
//
//	private void notifyOnlineMemChange() {
//		for (String clientName : client2Channel.keySet()) {
//			Channel channel = client2Channel.get(clientName);
//			channel.writeAndFlush(getNotifyMessage());
//		}
//	}
//
//	private Message getNotifyMessage() {
//		String msg = JSON.toJSONString(id2UserName);
//		Message message = new Message();
//		message.setMessage(msg);
//		message.setType(3);
//		return message;
//	}
//
//	public void writeMsg(Message msg) {
//		try {
//			Channel channel = id2Channel.get(msg.getToUserId());
//			if (!channel.isActive()) {
//				System.out.println("it's not online");
//				id2Channel.remove(msg.getToUserId());
//			}
//			channel.writeAndFlush(msg);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
//		switch (message.getType()) {
//			case 0:
//				writeMsg(message);
//				break;
//			case 1:
//				// 新用户上线后
//				Long userId = message.getUserId();
//				setId2Channel(userId, channelHandlerContext.channel());
//				setId2UserName(userId, message.getUserName());
//				System.out.println(userId + "注册成功");
//				notifyOnlineMemChange();
//				channelHandlerContext.writeAndFlush(getNotifyMessage());
//				break;
//			case 2:
//
//
//		}
//	}
//
//
//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		/**
//		 * 在收到新的链接后，先把当前在线的人员发给客户端
//		 * */
//		Channel channel = ctx.channel();
//		String clientName = channel.remoteAddress().toString();
//		System.out.println("RemoteAddress:"+clientName+"active!");
//		setClient2Channel(clientName, channel);
//		// 获取当前在线的用户，发送给新上线的用户
//		channel.writeAndFlush(getNotifyMessage());
//	}
//
//	@Override
//	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//		super.channelInactive(ctx);
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		Channel channel = ctx.channel();
//		channel.close();
//		super.exceptionCaught(ctx, cause);
//	}
//}
public class NettyServerHandler extends SimpleChannelInboundHandler<Message>{
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