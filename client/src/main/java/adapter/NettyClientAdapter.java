package adapter;

import client.NettyClient;

public class NettyClientAdapter {
	public static void sendMsg(String msg) {
		NettyClient nettyClient = new NettyClient();
		nettyClient.sendMsg(msg);
	}
}
