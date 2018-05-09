package com.cml.framework.interview;

import java.util.Stack;

public class Brackets2 {
    public static void main(String[] args) {
        System.out.println("====计算括号=====");
        int count = 3;
        int size = count * 2;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append("(");
        }

        System.out.println(sb.toString());

        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                sb.setCharAt(i, '(');
                System.out.println(sb.toString());
                print(sb);
                sb.setCharAt(i, ')');
                System.out.println(sb.toString());
                print(sb);
            }
        }
    }

    static void print(StringBuffer sb) {
        if (isLegal(sb))
            System.out.println(sb.toString());
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
