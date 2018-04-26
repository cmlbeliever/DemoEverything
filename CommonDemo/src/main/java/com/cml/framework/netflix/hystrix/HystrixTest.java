package com.cml.framework.netflix.hystrix;

import java.util.concurrent.ExecutionException;

import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class HystrixTest {
	public static void main(String[] args) throws Exception, ExecutionException {

		HystrixCommandGroupKey key = HystrixCommandGroupKey.Factory.asKey("ExampleGroup");

		Setter setter = Setter.withGroupKey(key).andCommandKey(HystrixCommandKey.Factory.asKey("CircuitBreakerTestKey"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircuitBreakerTest")).andThreadPoolPropertiesDefaults( // 配置线程池
						HystrixThreadPoolProperties.Setter().withCoreSize(100) // 配置线程池里的线程数，设置足够多线程，以防未熔断却打满threadpool
				).andCommandPropertiesDefaults( // 配置熔断器
						HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true).withCircuitBreakerRequestVolumeThreshold(3)
								.withCircuitBreakerErrorThresholdPercentage(100).withExecutionTimeoutInMilliseconds(10_000)
		// .withCircuitBreakerForceOpen(true) // 置为true时，所有请求都将被拒绝，直接到fallback
		// .withCircuitBreakerForceClosed(true) // 置为true时，将忽略错误
		 .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)
		// // 信号量隔离
		// .withExecutionTimeoutInMilliseconds(5000)
		);

		for (int i = 0; i < 50; i++) {
			final int index = i;
			new Thread(() -> {
				try {
					String result = new MyHystrixCommand(setter, index).execute();
					System.out.println(result);
				} catch (Exception e) {
					System.out.println("报错了：" + index + "," + e.getMessage());
				}
			}).start();
		}
		// new HystrixObservableCommandTest(key).observe().subscribe((t) -> {
		// System.out.println("===>" + t);
		// });

		Thread.sleep(200000);
	}
}
