package com.cml.learn.async;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class AsyncTaskPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(100);
        executor.setCorePoolSize(50);
        executor.setThreadNamePrefix("Async-ThreadPool-");
        executor.initialize();

        for (int i = 0; i < 100; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + "," + Thread.currentThread().getName());
                }
            });
        }
        Thread.sleep(2000);
        executor.shutdown();
    }
}
