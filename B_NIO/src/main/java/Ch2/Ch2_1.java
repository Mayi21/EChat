package Ch2;

import java.io.InputStream;

public class Ch2_1 {
	public static void main(String[] args) throws Exception {
		// 传统流式
		byte[] data = new byte[10];
		InputStream in = System.in;
		while (true) {
			int len;
			while ((len = in.read(data)) >= 0) {
				System.out.println("data is " + new String(data, 0, len));
			}
		}

	}
}
