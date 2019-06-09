package com.cml.framework.rxjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxJavaTest {
	public static void main(String[] args) throws Exception {
		System.out.println("=====");
		ExecutorService executors = new ThreadPoolExecutor(1000, 1000, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		Scheduler defaultScduler=Schedulers.from(executors);
		Observable.just(1, 2, 3).subscribeOn(defaultScduler).subscribe(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("----:" + t);
			}
		});
	}
}
