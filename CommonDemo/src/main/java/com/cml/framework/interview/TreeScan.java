package com.cml.framework.interview;

/**
 * 二叉树的遍历实现
 * 
 * @author cml
 *
 */
public class TreeScan {

	static class TreeNode {
		TreeNode left;
		TreeNode right;
		String value;

		public TreeNode(TreeNode left, TreeNode right, String value) {
			super();
			this.left = left;
			this.right = right;
			this.value = value;
		}

		public TreeNode(String value) {
			super();
			this.value = value;
		}

		@Override
		public String toString() {
			return " [" + value + "]";
		}

	}

	public static void main(String[] args) {
		TreeNode a = new TreeNode("A");
		TreeNode b = new TreeNode("B");
		TreeNode c = new TreeNode("C");
		TreeNode d = new TreeNode("D");
		TreeNode e = new TreeNode("E");
		TreeNode f = new TreeNode("F");
		TreeNode g = new TreeNode("G");
		TreeNode h = new TreeNode("H");
		TreeNode k = new TreeNode("K");

		a.left = b;
		b.right = c;
		c.left = d;

		a.right = e;
		e.right = f;
		f.left = g;
		g.left = h;
		g.right = k;

		System.out.println("先序遍历");
		preScan(a);
		System.out.println();

		System.out.println("中序遍历");
		midScan(a);
		System.out.println();

		System.out.println("后序遍历");
		lastScan(a);
		System.out.println();
	}

	private static void lastScan(TreeNode a) {
		if (a == null) {
			return;
		}
		lastScan(a.left);
		lastScan(a.right);
		System.out.print(a.value + " ");
	}

	private static void midScan(TreeNode a) {
		if (a == null) {
			return;
		}
		midScan(a.left);
		System.out.print(a.value + " ");
		midScan(a.right);
	}

	/**
	 * 先序遍历
	 * 
	 * @param node
	 */
	private static void preScan(TreeNode node) {

		if (node == null) {
			return;
		}
		System.out.print(node.value + " ");
		preScan(node.left);
		preScan(node.right);
	}
}
