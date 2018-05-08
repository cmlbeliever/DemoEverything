package com.cml.framework.jdk.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {
	public static void main(String[] args) throws Exception, ExecutionException {
		CompletableFuture future = CompletableFuture.runAsync(() -> {
			System.out.println("run");
		});
		System.out.println(future.get());

		future = CompletableFuture.supplyAsync(() -> "测试" + Thread.currentThread().getId());
		System.out.println(Thread.currentThread().getId() + ":" + future.get());

		System.out.println("end");
	}
}
