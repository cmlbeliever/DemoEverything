package com.cml.framework;

public class StringTest {
	public static void main(String[] args) {
		String a = "hello world";
		String b = "hello " + new String("world");
		String c = new String("hello world");

		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(b == c);
		
		System.out.println(Integer.valueOf(-128) == Integer.valueOf(-128));
	}
}
