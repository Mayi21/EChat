package Ch1;

import util.BufferUtil;

import java.nio.IntBuffer;
import java.util.Arrays;

public class Ch1_2 {
	public static void main(String[] args) {
		// put
		IntBuffer intBuffer = IntBuffer.allocate(10);
		intBuffer.put(10);
		// put(index, value)
		intBuffer.put(9, 10);
		// put
		System.out.println(intBuffer.position());
		intBuffer.put(new int[]{1,2,3});
		/**
		 * remaining = limit - position
		 * 不准确来判断剩余的容量，如果你插入在指定位置，这个remaining计算就有问题。
		 *
		 * */
		BufferUtil.getInfoAboutBuffer(intBuffer);
		System.out.println(Arrays.toString(intBuffer.array()));

		// return current position value
		System.out.println(intBuffer.get());
		intBuffer.flip();
		System.out.println(Arrays.toString(intBuffer.array()));
		// mark: mark current position
		// reset: return mark position
		while (intBuffer.hasRemaining()) {
			int i = intBuffer.get();
			if (i == 10) {
				intBuffer.mark();
			}
			if (i == 0) {
				intBuffer.reset();
			}
			System.out.println(i);
		}
		BufferUtil.getInfoAboutBuffer(intBuffer);
	}
}
