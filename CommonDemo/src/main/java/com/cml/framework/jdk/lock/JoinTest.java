package com.cml.framework.jdk.lock;

public class JoinTest {
	public static void main(String[] args) throws Exception {
		Thread a = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("a");
		});
		Thread b = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("b");
		});
		Thread c = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("c");
		});

		a.start();
		a.join();
		b.start();
		b.join();
		c.start();
		c.join();

		System.out.println("end===>");
	}
}
