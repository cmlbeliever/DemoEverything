package com.cml.framework.interview;

/**
 * 二分查找
 * 
 * @author cml
 *
 */
public class BinarySearch {
	public static void main(String[] args) {
		int[] data = new int[] { 1, 4, 6, 8, 10, 33, 44, 66, 77 };
		int target = 32;

		int start = 0;
		int end = data.length - 1;
		int targetPosition = -1;
		while (start <= end) {
			int index = (start + end) / 2;
			if (data[index] == target) {
				targetPosition = index;
				break;
			}
			if (data[index] > target) {
				end = index - 1;
			} else {
				start = index + 1;
			}
		}
		System.out.println("找到了吗？" + targetPosition);
	}
}
