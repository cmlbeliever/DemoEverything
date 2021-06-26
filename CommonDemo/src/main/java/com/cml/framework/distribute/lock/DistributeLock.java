package com.cml.framework.distribute.lock;

import java.util.concurrent.TimeUnit;

public interface DistributeLock {
    /**
     * 获取锁
     *
     * @param key
     * @param token
     * @param time
     * @param timeUnit
     * @return
     */
    boolean tryLock(String key, String token, int time, TimeUnit timeUnit);

    /**
     * 释放锁
     *
     * @param key
     * @param token
     * @return
     */
    boolean release(String key, String token);
}
