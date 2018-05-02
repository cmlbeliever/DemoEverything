package com.cml.framework.jdk.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {
	public static void main(String[] args) throws Exception, ExecutionException {
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "call result";
			}
		};
		FutureTask<String> task = new FutureTask<>(callable);
		new Thread(task).start();
		String result = task.get();
		System.out.println("result:" + result);
	}
}
