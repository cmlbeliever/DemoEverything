package com.cml.framework.distribute.lock;

import com.cml.framework.distribute.lock.impl.DistributeLockImpl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DistributeLockTest {
    public static void main(String[] args) {
        testLocalLock();

        DistributeLock distributeLock = new DistributeLockImpl();

        String key = "lockKey";
        String token = UUID.randomUUID().toString();
        boolean locked = false;
        try {
            locked = distributeLock.tryLock(key, token, 1, TimeUnit.SECONDS);

        } finally {
            distributeLock.release(key, token);
        }

    }

    /**
     * 本地锁逻辑处理
     */
    private static void testLocalLock() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        try {
            //TODO 业务逻辑
        } finally {
            reentrantLock.unlock();
        }
    }
}
