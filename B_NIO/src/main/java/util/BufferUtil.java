package util;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class BufferUtil {
	public static void getInfoAboutBuffer(Buffer buffer) {
		System.out.println("position: " + buffer.position() + " limit: " + buffer.limit()
				+ " mark: " + buffer.mark() + " remaining: " + buffer.remaining());
	}

	public static void getValueOfBuffer(IntBuffer intBuffer) {
		System.out.println(Arrays.toString(intBuffer.array()));
	}
}
