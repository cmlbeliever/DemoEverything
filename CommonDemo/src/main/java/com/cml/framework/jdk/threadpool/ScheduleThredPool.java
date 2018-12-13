package com.cml.framework.jdk.threadpool;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleThredPool {
    public static void main(String[] args) throws InterruptedException {


        final Runnable runnable = () -> {
            System.out.println("=====>" + System.currentTimeMillis());
        };

        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(runnable, 0L, 1, TimeUnit.SECONDS);
        System.out.println("hashcode:" + scheduledFuture.hashCode());

        Thread.sleep(3_000);
        boolean cancel = scheduledFuture.cancel(true);
        System.out.println("after remove!!!" + cancel);

        scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(runnable, 0L, 1, TimeUnit.SECONDS);
        System.out.println("hashcode:" + scheduledFuture.hashCode());
        Thread.sleep(3_000);
        cancel = scheduledFuture.cancel(true);
        System.out.println("after remove!!!" + cancel);

        scheduledExecutorService.shutdown();
    }
}
