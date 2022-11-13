package leetcode.L127;

import org.w3c.dom.ls.LSInput;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> list = new ArrayList<>(Arrays.asList("hot","dot","tog","cog"));
		Solution solution = new Solution();
		System.out.println(solution.ladderLength(beginWord, endWord, list));
	}
}
/**
 * 采用BFS
 * */
class Solution {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (!wordList.contains(endWord)) {
			return 0;
		}
		List<String> layerWordLsit = new ArrayList<>();
		layerWordLsit.add(beginWord);
		int layerCount = 0;
		while (true) {
			Set<String> tempList = new HashSet<>();
			while (layerWordLsit.size() != 0) {
				String word = layerWordLsit.remove(0);
				wordList.remove(word);
				tempList.addAll(getAdjWord(word, wordList));
			}
			if (tempList.size() == 0) {
				return 0;
			}
			if (tempList.contains(endWord)) {
				return layerCount + 2;
			}
			layerWordLsit.addAll(tempList);
			layerCount += 1;
		}
	}
	private List<String> getAdjWord(String word, List<String> wordList) {
		List<String> temp = new ArrayList<>();
		for (String c : wordList) {
			if (judgeString(word, c)) {
				temp.add(c);
			}
		}
		return temp;
	}

	public boolean judgeString(String a, String b) {
		int count = 0;
		for (int index = 0; index < a.length(); index++) {
			if (a.charAt(index) != b.charAt(index)) {
				count += 1;
			}
		}
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}
}


/**
 * 这种回溯的实现方式不行的
 * */
//class Solution {
//	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//		if (!wordList.contains(endWord)) {
//			return 0;
//		}
//		Set<String> path = new HashSet<>();
//		List<Integer> pathLength = new ArrayList<>();
//		pathLength.add(5000);
//		loop(beginWord, endWord, wordList, path, pathLength);
//		return Collections.min(pathLength) >= wordList.size() ? 0:Collections.min(pathLength) + 1;
//	}
//
//	public void loop(String currentWord, String endWord, List<String> wordList, Set<String> path, List<Integer> pathLength) {
//		if (path.size() >= Collections.min(pathLength)) {
//			return;
//		}
//		if (judgeString(currentWord, endWord)) {
//			pathLength.add(path.size() + 1);
//			return;
//		}
//		for (int i = 0; i < wordList.size(); i++) {
//			String word = wordList.get(i);
//			if (path.contains(word)) {
//				continue;
//			}
//			if (judgeString(currentWord, word)) {
//				path.add(word);
//				loop(word, endWord, wordList, path, pathLength);
//				path.remove(word);
//			}
//		}
//	}
//
//	public boolean judgeString(String a, String b) {
//		int count = 0;
//		for (int index = 0; index < a.length(); index++) {
//			if (a.charAt(index) != b.charAt(index)) {
//				count += 1;
//			}
//		}
//		if (count == 1) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//}