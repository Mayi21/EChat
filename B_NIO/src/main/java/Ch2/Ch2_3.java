package Ch2;

import util.BufferUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

// FileChannel example
public class Ch2_3 {
	public static void main(String[] args) throws Exception {
		/**
		 * 传统读取的方式
		 * */
//		try(FileOutputStream out = new FileOutputStream("test.txt")) {
//			FileInputStream input = new FileInputStream("test.txt");
//			String data = "Hello world";
//			out.write(data.getBytes());
//			out.flush();

//			byte[] bytes = new byte[input.available()];
//			input.read(bytes);
//			System.out.println(new String(bytes));
//		}

		/**
		 * FileChannel读取的方式
		 * */
//		FileInputStream fileInputStream = new FileInputStream("test.txt");
//		FileChannel channel = fileInputStream.getChannel();
//		ByteBuffer byteBuffer = ByteBuffer.allocate(128);
//		channel.read(byteBuffer);
//		BufferUtil.getInfoAboutBuffer(byteBuffer);
//		System.out.println(new String(byteBuffer.array()));

		/**
		 * FileChannel 写入文件
		 * */
//		FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
//		FileChannel channel = fileOutputStream.getChannel();
//		ByteBuffer buffer = ByteBuffer.wrap("Hello world".getBytes());
//		channel.write(buffer);


		/**
		 * FileChannel既可以读也可以写
		 * */
//		try(RandomAccessFile rw = new RandomAccessFile("test.txt", "rw")) {
//			FileChannel channel = rw.getChannel();
//			ByteBuffer byteBuffer = ByteBuffer.wrap("Hello world asdakjhsb ajshdfkasdhaj ajdhvjahd".getBytes());
//			channel.write(byteBuffer);
//
//			ByteBuffer readBuffer = ByteBuffer.allocate(10);
//			do {
//				channel.write(readBuffer);
//				BufferUtil.getInfoAboutBuffer(readBuffer);
//				readBuffer.clear();
//			} while (!readBuffer.hasRemaining());
//		}

		/**
		 * 使用FileChannel进行文件复制操作
		 * */
//		try(FileOutputStream out = new FileOutputStream("test2.txt")) {
//			FileInputStream in = new FileInputStream("test.txt");
//			FileChannel inChannel = in.getChannel();
//			FileChannel outChannel = out.getChannel();
//			inChannel.transferTo(0, inChannel.size(), outChannel);
//		}


		/*
		* 使用MappedByteBuffer对文件进行修改
		* 不对劲呀，为什么修改不成功呢？
		* */

		try(RandomAccessFile rw = new RandomAccessFile("test.txt", "rw");
			FileChannel channel = rw.getChannel()) {
			MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
			BufferUtil.getInfoAboutBuffer(map);
			map.put(0, (byte) 'H');
			BufferUtil.getInfoAboutBuffer(map);
			map.force();
		}
	}
}
