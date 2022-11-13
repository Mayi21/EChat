package leetcode.L841;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<List<Integer>> lists = new ArrayList<>();

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(3);
		lists.add(list);
		list = new ArrayList<>();
		list.add(3);
		list.add(0);
		list.add(1);
		lists.add(list);
		list = new ArrayList<>();
		list.add(2);
		lists.add(list);

		list = new ArrayList<>();
		list.add(0);
		lists.add(list);

		Solution solution = new Solution();
		System.out.println(solution.canVisitAllRooms(lists));
	}
}
/**
 * 首先拿到第一间房子的钥匙
 *
 * */
class Solution {
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		if (rooms.size() == 1 || rooms.size() == 0) {
			return true;
		}
		int[] room = new int[rooms.size()];
		room[0] = 1;
		List<Integer> keys = rooms.get(0);
		while (keys.size() != 0) {
			Integer key = keys.remove(0);
			if (room[key] == 0) {
				keys.addAll(rooms.get(key));
				room[key] = 1;
			}
		}
		boolean flag = true;
		for (int i : room) {
			if (i == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}