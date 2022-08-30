package adapter;

import server.NettyServer;

public class NettyServerAdapter {
	public static void sendMsg(String msg) {
		NettyServer nettyServer = new NettyServer();
		nettyServer.sendMsg(msg);
	}
}
