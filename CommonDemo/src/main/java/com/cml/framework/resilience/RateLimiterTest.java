package com.cml.framework.resilience;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 详见：http://resilience4j.github.io/resilience4j/#_ratelimiter
 */
public class RateLimiterTest {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterConfig config = RateLimiterConfig
                .custom()
                .limitForPeriod(10)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ofMillis(100))
                .build();
//        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
//        // Use registry
//        RateLimiter rateLimiterWithDefaultConfig = rateLimiterRegistry.rateLimiter("backend");
//        RateLimiter rateLimiterWithCustomConfig = rateLimiterRegistry.rateLimiter("backend#2", config);

        // Or create RateLimiter directly
        RateLimiter rateLimiter = RateLimiter.of("testA", config);

        CheckedRunnable decorateRunnable = RateLimiter.decorateCheckedRunnable(rateLimiter, () -> {
            System.out.println("doSmthing");
            Thread.sleep(1000);
        });

        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                Try.run(decorateRunnable).onFailure(throwable -> {
                    System.out.println("-----------失败了--------------" + throwable.getMessage());
                });
            });

        }

        executorService.shutdown();

        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
        }

    }
}
