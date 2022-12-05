package leetcode.L933;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		RecentCounter recentCounter = new RecentCounter();
		System.out.println(recentCounter.ping(1));
		System.out.println(recentCounter.ping(2));
		System.out.println(recentCounter.ping(3));
		System.out.println(recentCounter.ping(4));
		System.out.println(recentCounter.ping(5));
		System.out.println(recentCounter.ping(2999));
		System.out.println(recentCounter.ping(3000));
		System.out.println(recentCounter.ping(6001));
	}
}
class RecentCounter {
	private Queue<Integer> list;

	public RecentCounter() {
		this.list = new ArrayDeque<>();
	}

	public int ping(int t) {
		if (list.isEmpty()) {
			list.add(t);
			return list.size();
		}
		while (!list.isEmpty() && list.peek() + 3000 < t) {
			list.remove();
		}
		list.add(t);
		return list.size();
	}
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */