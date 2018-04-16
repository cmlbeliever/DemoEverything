package com.cml.framework.guava;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimitTest {
	public static void main(String[] args) {
		RateLimiter rateLimiter = RateLimiter.create(1);
		System.out.println(rateLimiter.tryAcquire());
		System.out.println(rateLimiter.tryAcquire());
		System.out.println(rateLimiter.tryAcquire());
		System.out.println(rateLimiter.acquire());
		System.out.println(rateLimiter.acquire());
		System.out.println(rateLimiter.acquire());
		System.out.println(rateLimiter.acquire());
		
	}
}
