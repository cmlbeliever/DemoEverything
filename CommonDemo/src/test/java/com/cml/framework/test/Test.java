package com.cml.framework.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) throws Exception {
		char a = 'a';
		System.out.println((int) a);
		Class.forName(T1.class.getName());
		System.out.println("--------------");
		Test.class.getClassLoader().loadClass(T1.class.getName());
		ExecutorService ser = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			ser.submit(new Runnable() {

				@Override
				public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("end");
				}
			});
		}
		System.out.println("add success");
	}

	private static class T1 {
		static {
			System.out.println("222222222");
		}
	}
}
