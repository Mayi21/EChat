package Ch4.SingleReactor;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用来处理读写
 * */
public class MyHandler implements Runnable {
	private static final ExecutorService POLL = Executors.newFixedThreadPool(10);
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
			POLL.submit(() -> {
				try {
					System.out.println("receive data from client:" + new String(buffer.array(), 0, buffer.remaining()));
					channel.write(ByteBuffer.wrap("received".getBytes()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
