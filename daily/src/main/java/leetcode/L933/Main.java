package leetcode.L933;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {

	}
}
class RecentCounter {
	List<Integer> list;

	public RecentCounter() {
		this.list = new ArrayList<>();
	}

	public int ping(int t) {
		list = list.stream().filter(x -> x + 3000 >= t).collect(Collectors.toList());
		list.add(t);

		return list.size();
	}
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */