package com.cml.framework.jdk.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// 异步获取数据，同步处理返回值
		String result = CompletableFuture.supplyAsync(() -> "hello " + Thread.currentThread().getId())
				.thenApply(s -> s + " world " + Thread.currentThread().getId()).join();
		System.out.println("==>1:mainThreadId:" + Thread.currentThread().getId() + ",result:" + result);

		// 只处理返回结果，没有返回值
		CompletableFuture.supplyAsync(() -> "getValueFromApi" + Thread.currentThread().getId()).thenAccept(c -> {
			System.out.println("operateValue:" + c + ",ThreadId:" + Thread.currentThread().getId());
		});

		// 不关心上一步的数据，只要上一步处理完成即可
		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "cacheValue";
		}).thenRun(() -> {
			System.out.println("不关心上一步获取的数据！！！" + Thread.currentThread().getId());
		});

		// 合并两个异步的处理数据
		String combineResult = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "value from 1 " + Thread.currentThread().getId();
		}).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "value from 2 " + Thread.currentThread().getId();
		}), (v1, v2) -> {
			return v1 + "," + v2 + ",mergeThreadId:" + Thread.currentThread().getId();
		}).join();
		System.out.println("==>2:合并两个请求值：" + combineResult);

		//
		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello " + Thread.currentThread().getId();
		}).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "world " + Thread.currentThread().getId();
		}), (s1, s2) -> System.out.println(s1 + " " + s2 + "," + Thread.currentThread().getId()));

		// applyToEither 谁先返回的就用谁的值
		result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "s1";
		}).applyToEither(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello world";
		}), s -> s).join();
		System.out.println("==>3:"+result);

		// 测试异常时返回处理数据
		result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (true) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "s1";
		}).exceptionally(e -> {
			// System.out.println(e.getMessage());
			return "异常结果自定义返回了！！！";
		}).join();
		System.out.println("==>4:"+result);

		// 出现异常时进行处理，这里可以一起处理正常和异常的数据
		result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 出现异常
			if (1 == 1) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "s1";
		}).handle((s, t) -> {
			if (t != null) {
				return "hello world " + t.getMessage();
			}
			return s;
		}).join();
		System.out.println("==>5:异常情况处理，正常时不处理：" + result);

		System.out.println("=====end=====");
		Thread.sleep(1000);
	}
}
