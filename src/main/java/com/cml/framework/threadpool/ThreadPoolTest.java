package com.cml.framework.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor pool = null;
		try {
			
			int threadCount = 5;
			pool = new ThreadPoolExecutor(threadCount, threadCount * 2, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new ThreadFactory() {

				@Override
				public Thread newThread(Runnable r) {
					System.out.println("getThread!!!");
					return new Thread(r);
				}
			}, new RejectedExecutionHandler() {

				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					System.out.println("你的请求被拒绝了---------------》" + executor.getActiveCount());
				}
			});
			// 在初始化开始的时候就填充好线程
			pool.prestartAllCoreThreads();
			System.out.println("-------------------------------------------------------------------------------");
			for (int i = 0; i < 20; i++) {
				final int j = i;
				pool.execute(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("execute:" + j);
					}
				});

			}
		} finally {
			Thread.sleep(9000);
			pool.shutdown();
		}
		
		Executors.newCachedThreadPool();

	}
}
