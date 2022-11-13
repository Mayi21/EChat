package Ch4;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 用来处理读写
 * */
public class MyHandler implements Runnable {
	private final SocketChannel channel;
	public MyHandler(SocketChannel channel) {
		this.channel = channel;
	}

	@Override
	public void run() {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(128);
			channel.read(buffer);
			buffer.flip();
			System.out.println("receive data from client:" + new String(buffer.array(), 0, buffer.remaining()));
			channel.write(ByteBuffer.wrap("received".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
