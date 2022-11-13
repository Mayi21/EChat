package Ch3.TraditionalByChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TraditionalClientByChannel {
	public static void main(String[] args) {
		try(SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost",12345))) {
			System.out.println("connected server");
			socketChannel.write(ByteBuffer.wrap("Hello server".getBytes()));
			System.out.println("数据已发送");
			ByteBuffer buffer = ByteBuffer.allocate(128);
			socketChannel.read(buffer);
			System.out.println(new String(buffer.array(), 0, buffer.remaining()));
		}catch (IOException e){
			System.out.println("服务端连接失败！");
			e.printStackTrace();
		}finally {
			System.out.println("客户端断开连接！");
		}
	}
}
