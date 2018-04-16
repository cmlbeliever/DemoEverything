package com.cml.framework;

import java.util.concurrent.atomic.AtomicLong;

public class Test {
	public static void main(String[] args) {
		Integer a = 1000, b = 1000;
		AtomicLong al = new AtomicLong();
		System.out.println(a == b);
	}
}
