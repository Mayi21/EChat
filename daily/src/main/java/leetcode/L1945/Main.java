package leetcode.L1945;

public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int zbax = solution.getLucky("iiii", 1);
		System.out.println(zbax);
	}
}

class Solution {
	public int getLucky(String s, int k) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			stringBuilder.append(s.charAt(i) - 'a' + 1);
		}
		int sum = 0;
		for (int i = 0; i < k; i++) {
			if (i == 0) {
				sum = getRes(stringBuilder.toString());
			} else {
				sum = getRes(String.valueOf(sum));
			}
		}
		return sum;
	}

	private int getRes(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += s.charAt(i) - '0';
		}
		return sum;
	}
}