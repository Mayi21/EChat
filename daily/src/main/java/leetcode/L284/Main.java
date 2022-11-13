package leetcode.L284;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
	public static void main(String[] args) {
	}
}

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {

	private int index = -1;

	private List<Integer> list = new ArrayList<>();
	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return list.get(index + 1);
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		index += 1;
		return list.get(index);
	}

	@Override
	public boolean hasNext() {
		return index + 1 < list.size();
	}
}