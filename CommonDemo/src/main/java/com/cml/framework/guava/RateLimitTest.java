package com.cml.framework.guava;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimitTest {
	public static void main(String[] args) throws InterruptedException {
		RateLimiter rateLimiter = RateLimiter.create(1);
		rateLimiter.setRate(1111);
		Thread.sleep(5_000);
		int i=0;
		while (rateLimiter.tryAcquire()){
		    i++;
            System.out.println("get:"+i);
        }

	}
}
