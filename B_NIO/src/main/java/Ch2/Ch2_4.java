package Ch2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * FileLock介绍和使用
 * 防止多进程间文件争抢操作
 * 支持对文件某一段进行加锁
 * */
public class Ch2_4 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile rw = new RandomAccessFile("test.txt", "rw");
		FileChannel channel = rw.getChannel();
		// 如果其他文件拿到了这个锁，就会一直阻塞在这里
		// 支持对文件某一段加锁
		FileLock lock = channel.lock(0, 6, false);
		System.out.println(System.currentTimeMillis());
		lock.release();
	}
}
