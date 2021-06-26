package com.cml.framework.distribute.lock.impl;

import com.cml.framework.distribute.lock.DistributeLock;

import java.util.concurrent.TimeUnit;

public class DistributeLockImpl implements DistributeLock {
    @Override
    public boolean tryLock(String key, String token, int time, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public boolean release(String key, String token) {
        return false;
    }
}
