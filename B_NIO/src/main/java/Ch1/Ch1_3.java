package Ch1;

import util.BufferUtil;

import java.nio.IntBuffer;

public class Ch1_3 {
	public static void main(String[] args) {
		IntBuffer intBuffer = IntBuffer.wrap(new int[]{1,2,3, 4,5,6,7,8,9});
		for (int i=0; i<4;i++) {
			intBuffer.get();
		}
		BufferUtil.getValueOfBuffer(intBuffer);
		BufferUtil.getInfoAboutBuffer(intBuffer);
		intBuffer.compact();
		BufferUtil.getValueOfBuffer(intBuffer);
		BufferUtil.getInfoAboutBuffer(intBuffer);
	}
}
