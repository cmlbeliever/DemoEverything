package com.cml.request.distribute;

public interface DistributeRateLimiter {
    /**
     * 获取请求令牌
     * @return true：
     */
    boolean tryAcquireToken(String group);
}
