package Ch3.BySelector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Server {
	public static void main(String[] args) {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			Selector selector = Selector.open();
			serverSocketChannel.bind(new InetSocketAddress(8080));
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while (true) {
				int count = selector.select();
				System.out.println("监听到 "+count+" 个事件");
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					if (selectionKey.isAcceptable()) {
						SocketChannel channel = serverSocketChannel.accept();
						System.out.println("客户端已连接，IP地址为："+channel.getRemoteAddress());
						//现在连接就建立好了，接着我们需要将连接也注册选择器，比如我们需要当这个连接有内容可读时就进行处理
						channel.configureBlocking(false);
						channel.register(selector, SelectionKey.OP_READ);
					}
					if (selectionKey.isReadable()) {
						SocketChannel channel = (SocketChannel) selectionKey.channel();
						ByteBuffer buffer = ByteBuffer.allocate(128);
						channel.read(buffer);
						buffer.flip();
						System.out.println("接收到客户端数据："+new String(buffer.array(), 0, buffer.remaining()));
						//直接向通道中写入数据就行
						channel.write(ByteBuffer.wrap("已收到！".getBytes()));
					}
					iterator.remove();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
