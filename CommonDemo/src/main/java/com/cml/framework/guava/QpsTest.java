package com.cml.framework.guava;

import com.google.common.cache.*;
import com.google.common.util.concurrent.AtomicDouble;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * 网关访问流量统计
 */
public class QpsTest {

    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, AtomicLong> cache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS).removalListener(new RemovalListener<String, AtomicLong>() {
            @Override
            public void onRemoval(RemovalNotification<String, AtomicLong> notification) {
                System.out.println("remove,key:" + notification.getKey() + " qps:" + notification.getValue().longValue());
            }
        }).build(new CacheLoader<String, AtomicLong>() {
            @Override
            public AtomicLong load(String key) throws Exception {
                return new AtomicLong(0);
            }
        });

        IntStream.range(1, 1000_0000).forEach(t -> {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    AtomicLong count = cache.get(System.currentTimeMillis() / 1000 + "");
                    count.incrementAndGet();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }
}
