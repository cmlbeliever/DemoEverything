package com.cml.framework.spring.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ListenableFutureTest {
	public static void main(String[] args) {
		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
		final ListenableFuture<Integer> listenableFuture = service.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("call execute..");
				TimeUnit.SECONDS.sleep(1);
				return 7;
			}
		});
		listenableFuture.addListener(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("callback end==>" + listenableFuture.get());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, service);
		System.out.println("end");

	}
}
