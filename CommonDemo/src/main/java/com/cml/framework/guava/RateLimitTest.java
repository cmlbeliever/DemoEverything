package com.cml.framework.guava;

import com.google.common.cache.*;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimitTest {
    static boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        LoadingCache<String, AtomicLong> cache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS).removalListener(new RemovalListener<String, AtomicLong>() {
            @Override
            public void onRemoval(RemovalNotification<String, AtomicLong> notification) {
                System.out.println("time:" + notification.getKey() + " ,count:" + notification.getValue().longValue());
            }
        }).build(new CacheLoader<String, AtomicLong>() {
            @Override
            public AtomicLong load(String key) throws Exception {
                return new AtomicLong(0);
            }
        });

        RateLimiter rateLimiter = RateLimiter.create(100);
        rateLimiter.tryAcquire();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rateLimiter.setRate(1);
        }).start();

        while (running) {
            if (rateLimiter.tryAcquire()) {
                try {
                    AtomicLong count = cache.get(System.currentTimeMillis() / 1000 + "");
                    count.incrementAndGet();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
