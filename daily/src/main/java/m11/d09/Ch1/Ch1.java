package m11.d09.Ch1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ch1 {
	public static void main(String[] args) {
		String s = "12345";
		Solution solution = new Solution();
		List<String> list = solution.ladderLength(s);
		list.forEach(System.out::println);
		System.out.println(list.size());
	}
}
/**
 *
 *
 * */
class Solution {
	public List<String> ladderLength(String s) {
		String[] chars = s.split("");
		List<String> res = new ArrayList<>();
		List<String> remainStrings = new ArrayList<>(Arrays.asList(chars));
		StringBuilder stringBuilder = new StringBuilder();
		loop(remainStrings, stringBuilder, res, chars);
		return res;
	}

	public void loop(List<String> remainStrings, StringBuilder stringBuilder, List<String> res, String[] chars) {
		if (stringBuilder.length() == chars.length) {
			res.add(stringBuilder.toString());
			return;
		}
		for (String c : chars) {
			if (stringBuilder.indexOf(c) != -1) {
				continue;
			}
			String remove = remainStrings.remove(0);
			stringBuilder.append(remove);
			loop(remainStrings, stringBuilder, res, chars);
			stringBuilder.deleteCharAt(stringBuilder.length()-1);
			remainStrings.add(remove);
		}
	}
}