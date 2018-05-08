package com.cml.framework.jdk.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class Test {
	public static void main(String[] args) {
		// AtomicReference<Thread> ac = new AtomicReference<Thread>();
		// ac.compareAndSet(null, Thread.currentThread());

		LockSupport.park();
		Thread.currentThread().interrupt();
		System.out.println("end"+Thread.currentThread().isInterrupted());
	}
}
