package com.cml.framework.util;

import java.util.Stack;

/**
 * 给定括号数量，生成对应的组合
 * 
 * @author cml
 *
 */
public class StringTest {
	public static void main(String[] args) {
		int n = 3;// 括号数
		StringBuffer sb = new StringBuffer();
		permutation(sb, n);
	}

	private static void permutation(StringBuffer sb, int n) {
		if (sb.length() == 2 * n) {
			if (isLegal(sb)) {
				System.out.println(sb.toString());
			}
			return;
		}
		// 增加后回溯
		sb.append('(');
		permutation(sb, n);
		sb.deleteCharAt(sb.length() - 1);
		sb.append(')');
		permutation(sb, n);
		sb.deleteCharAt(sb.length() - 1);
	}

	private static boolean isLegal(StringBuffer sb) {
		Stack<Character> stk = new Stack<>();
		int len = sb.length();
		for (int i = 0; i < len; i++) {
			if (sb.charAt(i) == '(') {
				stk.push(sb.charAt(i));
			}
			if (sb.charAt(i) == ')') {
				if (!stk.isEmpty() && stk.peek() == '(') {
					stk.pop();
				} else {
					return false;
				}
			}
		}
		return stk.isEmpty();
	}
}