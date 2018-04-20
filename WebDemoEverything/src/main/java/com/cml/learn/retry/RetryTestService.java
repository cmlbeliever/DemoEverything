package com.cml.learn.retry;

import java.util.function.Consumer;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class RetryTestService {

	@Retryable(maxAttempts = 5, include = Exception.class, backoff = @Backoff(delay = 5000, multiplier = 1))
	public String testRetry(int age) {
		System.out.println("test:" + age + ":" + (System.currentTimeMillis() / 1000));
		if (age < 1000) {
			throw new IllegalArgumentException("数字不符合规范！！！");
		}
		return "返回的数据:" + age;
	}

	@Retryable(maxAttempts = 5, include = Exception.class, backoff = @Backoff(delay = 5000, multiplier = 1))
	public <T> void testRetry2(Runnable r) {
		System.out.println("testRetry2:" + (System.currentTimeMillis() / 1000));
		r.run();
	}
}
