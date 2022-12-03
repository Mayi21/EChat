package leetcode.L1796;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		String s = "vwkxfq9791769";
		Solution solution = new Solution();
		int i = solution.secondHighest(s);
		System.out.println(i);
	}
}

/**
 *
 * */
class Solution {
	public int secondHighest(String s) {
		Set<Integer> results = new TreeSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		for (int i = 0; i < s.length(); i++) {
			int res = s.charAt(i) - '0';
			if (res >= 10) {
				continue;
			}
			results.add(res);
		}
		if (results.size() == 0 || results.size() == 1) {
			return -1;
		}
		ArrayList<Integer> list = new ArrayList<>(results);
		return list.get(1);
	}
}