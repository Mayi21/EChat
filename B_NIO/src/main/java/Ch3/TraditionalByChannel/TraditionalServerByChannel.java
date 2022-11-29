package Ch3.TraditionalByChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TraditionalServerByChannel {
	public static void main(String[] args) throws Exception {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			System.out.println("wait connection ....");
			serverSocketChannel.bind(new InetSocketAddress(12345));
			SocketChannel accept = serverSocketChannel.accept();
			System.out.println("connect. remote address:" + accept.getRemoteAddress());
			ByteBuffer buffer = ByteBuffer.allocate(128);
			accept.read(buffer);
			System.out.println("receive data from client:" + new String(buffer.array(), 0, buffer.remaining()));
			ByteBuffer writeBuffer = ByteBuffer.wrap("server received".getBytes());
			accept.write(writeBuffer);
		}
	}
}
