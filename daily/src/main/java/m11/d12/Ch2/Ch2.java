package m11.d12.Ch2;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Ch2 {
	public static void main(String[] args) {
		IntBuffer intBuffer = IntBuffer.allocate(10);
		int[] array = new int[10];
		IntBuffer buffer = IntBuffer.wrap(array);

	}
}
