package Ch4.SingleReactor;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MyAccepter implements Runnable {
	private final ServerSocketChannel serverSocketChannel;

	private final Selector selector;

	public MyAccepter(ServerSocketChannel socketChannel, Selector selector) {
		this.serverSocketChannel = socketChannel;
		this.selector = selector;
	}
	@Override
	public void run() {
		try {
			SocketChannel socketChannel = serverSocketChannel.accept();
			System.out.println("client ip address is " + socketChannel.getRemoteAddress());
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ, new MyHandler(socketChannel));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
