package Ch2;

import util.BufferUtil;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Ch2_2 {
	public static void main(String[] args) throws Exception {
		// use channel
		ByteBuffer buffer = ByteBuffer.allocate(10);
		ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
		while (true) {
			readableByteChannel.read(buffer);
			buffer.flip();
			BufferUtil.getInfoAboutBuffer(buffer);
			System.out.println("data is:" + new String(buffer.array(), 0, buffer.remaining()));
			buffer.clear();
		}
	}
}
