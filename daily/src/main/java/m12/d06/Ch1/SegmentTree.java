package m12.d06.Ch1;

public class SegmentTree {
	private Node root;

	public SegmentTree(int[] values) {
		this.root = buildTree(0, values.length - 1, values);
	}

	private Node buildTree(int start, int end, int[] values) {
		if (start == end) {
			return new Node(start, end, values[start]);
		}

		int mid = (start + end) / 2;
		Node left = buildTree(start, mid, values);
		Node right = buildTree(mid + 1, end, values);
		int min = Math.min(left.min, right.min);

		return new Node(start, end, min, left, right);
	}

	public int query(int start, int end) {
		return query(root, start, end);
	}

	private int query(Node node, int start, int end) {
		if (node.start >= start && node.end <= end) {
			return node.min;
		}

		int mid = (node.start + node.end) / 2;
		int min = Integer.MAX_VALUE;

		if (start <= mid) {
			min = Math.min(min, query(node.left, start, end));
		}

		if (end > mid) {
			min = Math.min(min, query(node.right, start, end));
		}

		return min;
	}

	public void update(int index, int value) {
		update(root, index, value);
	}

	private void update(Node node, int index, int value) {
		if (node.start == node.end) {
			node.min = value;
			return;
		}

		int mid = (node.start + node.end) / 2;

		if (index <= mid) {
			update(node.left, index, value);
		} else {
			update(node.right, index, value);
		}

		node.min = Math.min(node.left.min, node.right.min);
	}

	private static class Node {
		private int start, end, min;
		private Node left, right;

		public Node(int start, int end, int min, Node left, Node right) {
			this.start = start;
			this.end = end;
			this.min = min;
			this.left = left;
			this.right = right;
		}

		public Node(int start, int end, int min) {
			this(start, end, min, null, null);
		}
	}
}
