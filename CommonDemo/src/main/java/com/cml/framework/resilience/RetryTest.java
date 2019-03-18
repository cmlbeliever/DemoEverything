package com.cml.framework.resilience;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

public class RetryTest {
    public static void main(String[] args) {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(5)
                .retryOnException(e -> true)
                .build();

        Retry myRetry = Retry.of("myRetry", retryConfig);
        String t = myRetry.executeSupplier(() -> {
            System.out.println("执行代码了1");
            throw new RuntimeException("xxx");
        });
        System.out.println(t);
        myRetry.executeRunnable(() -> {
            System.out.println("执行代码了");
            throw new RuntimeException("xxx");
        });
//        Try<String> result = Try.of(retryableSupplier).recover((throwable) -> "Hello world from recovery function");
    }
}
