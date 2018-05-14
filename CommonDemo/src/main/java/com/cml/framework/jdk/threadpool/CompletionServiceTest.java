package com.cml.framework.jdk.threadpool;

import java.util.concurrent.*;

/**
 * CompletionService 使用测试
 */
public class CompletionServiceTest {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(100, 1000, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        int taskSize = 1500;
        for (int i = 0; i < taskSize; i++) {
            final int index = i;
            completionService.submit(() -> {
                Thread.sleep(100);
                return Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + ":" + index;
            });
        }
        for (int i = 0; i < taskSize; i++) {
            try {
                Future<String> resultFuture = completionService.take();
                System.out.println("获取到返回值：" + resultFuture.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        System.out.println("end");
    }
}
