package com.cml.framework.jdk.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest implements Runnable {
	public static void main(String[] args) {
		ReentrantLockTest test = new ReentrantLockTest();

		new Thread(test).start();
		new Thread(test).start();
		new Thread(test).start();
		

	}

	private ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		try {
			lock.lock();
			setValue();
			System.out.println("获得锁：" + Thread.currentThread().getId());
		} finally {
			lock.unlock();
		}

	}

	private void setValue() {
		try {
			lock.lock();
			System.out.println("setValue获得锁：" + Thread.currentThread().getId());
		} finally {
			lock.unlock();
		}
	}
}
