package com.cml.framework.jdk.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
	public static void main(String[] args) {
		LockSupport.park();

		System.out.println("end");
	}
}
