package com.cml.framework.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: cml
 * @Date: 2018-07-25 09:18
 * @Description:
 */
public class RedisRateLimiter {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.99.100:6379");
        RedissonClient redisson = Redisson.create(config);

        final ConcurrentHashMap<String, AtomicLong> cacheValue = new ConcurrentHashMap<>();
        final RedisRateLimiter redisRateLimiter = new RedisRateLimiter(0.1, redisson);
        new Thread(() -> {
            while (true) {
                System.out.println("===>结果为：" + cacheValue);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 10000; i++) {
            final int index = i;

            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean acquired = redisRateLimiter.acquire();
//                System.out.println("acquired:" + acquired);
                if (acquired) {
                    String time = System.currentTimeMillis() / 1000 + "";
                    synchronized (time.intern()) {
                        if (cacheValue.get(time) == null) {
                            cacheValue.put(time, new AtomicLong());
                        }
                        cacheValue.get(time).incrementAndGet();
                    }
                }
                if (index == 5000) {
                    System.out.println("=====================");
                    redisRateLimiter.setRate(100);
                }
            }).start();
        }
        Thread.sleep(2000);

    }

    private double rate;
    private long startTime = System.currentTimeMillis();
    private String keyPrefix;
    private double keyStep = 1;
    private double limitCount;
    private RedissonClient redissonClient;

    public RedisRateLimiter(double rate, RedissonClient redissonClient) {
        this.rate = rate;
        this.redissonClient = redissonClient;
        initKeyStep();
        keyPrefix = startTime / 1000 + "";
    }

    public void setRate(double rate) {
        this.rate = rate;
        initKeyStep();
    }

    private synchronized void initKeyStep() {
        if (rate < 1) {
            keyStep = 1 / rate;
            limitCount = 1;
        } else {
            keyStep = 1;
            limitCount = rate;
        }
    }

    public boolean acquire() {
        RAtomicDouble value = redissonClient.getAtomicDouble(getKey());
        if (value.getAndIncrement() < limitCount) {
            return true;
        }
        return false;
    }

    private String getKey() {
        return keyPrefix + ":" + ((int) ((System.currentTimeMillis() - startTime) / 1000 / keyStep));
    }
}
