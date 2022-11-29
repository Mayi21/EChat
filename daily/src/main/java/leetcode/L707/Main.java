package leetcode.L707;

public class Main {
	public static void main(String[] args) {

	}
}

/**
 * 使用数组实现
 * 分为两个数组，一个用来存头的，一个用来存尾的
 * 数组可以实现自动扩展
 * 用两个数来记录头数组和尾数组的长度
 *
 * get:输入一个index，首先根据两个数headCount and tailCount判断是头数组还是尾数组，
 * 	   头数组就直接headArray[headCount-index],尾数组就tailArray[tailCount-index+headCount]
 *
 * addAtHead:headArray[headCount]=val,headCount+=1
 *
 * addAtTail:tailArray[tailCount]=val,tailCount+=1
 *
 * addAtIndex:首先根据index判断一下
 *
 * */
class MyLinkedList {

	public MyLinkedList() {

	}

	public int get(int index) {
		return 1;

	}

	public void addAtHead(int val) {

	}

	public void addAtTail(int val) {

	}

	public void addAtIndex(int index, int val) {

	}

	public void deleteAtIndex(int index) {

	}
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
