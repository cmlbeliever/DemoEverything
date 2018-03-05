package com.cml.framework.assignable;

public class AssignableTest {

	public static void main(String[] args) {
		System.out.println(A.class.isAssignableFrom(B.class));
		System.out.println(B.class.isAssignableFrom(A.class));
	}

	public static class A {
	}

	public static class B extends A {
	}
}
