package Ch4.MultiReactor;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MyAccepter implements Runnable {
	private final ServerSocketChannel serverSocketChannel;

	public MyAccepter(ServerSocketChannel socketChannel) {
		this.serverSocketChannel = socketChannel;
	}
	@Override
	public void run() {
		try {
			SocketChannel socketChannel = serverSocketChannel.accept();
			System.out.println("client ip address is " + socketChannel.getRemoteAddress());
			socketChannel.configureBlocking(false);
			Selector selector = SubReactor.nextSelector();
			selector.wakeup();
			socketChannel.register(selector, SelectionKey.OP_READ, new MyHandler(socketChannel));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
