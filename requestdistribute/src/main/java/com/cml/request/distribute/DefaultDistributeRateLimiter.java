package com.cml.request.distribute;

import com.cml.request.distribute.config.RequestDistributeConfig;
import com.google.common.util.concurrent.RateLimiter;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultDistributeRateLimiter implements DistributeRateLimiter {

    private RequestDistributeConfig requestDistributeConfig;
    private ConcurrentHashMap<String, RateLimiter> rateLimiterConcurrentHashMap = new ConcurrentHashMap<>();
    private RateLimiter defaultRateLimiter;

    public boolean tryAcquireToken(final String group) {
        //启用了分流设置
        if (requestDistributeConfig.isEnable()) {
            //获取分流配置
            Optional<RequestDistributeConfig.DistributePolicy> policyOptional = requestDistributeConfig.getPolicy().stream().filter(t -> group.equals(t.getGroup())).findFirst();
            //有专门为这个分组配置速率
            if (policyOptional.isPresent()) {
                return policyOptional.get().isEnable() && rateLimiterConcurrentHashMap.get(group).tryAcquire();
            }
            //使用全局速率获取令牌
            return defaultRateLimiter != null && defaultRateLimiter.tryAcquire();
        }
        return false;
    }

    public DefaultDistributeRateLimiter init() {
        if (this.requestDistributeConfig == null) {
            throw new IllegalArgumentException("requestDistributeConfig 不能为空！");
        }
        rateLimiterConcurrentHashMap.clear();

        if (requestDistributeConfig.isEnable()) {
            defaultRateLimiter = RateLimiter.create(requestDistributeConfig.getRate());
            for (RequestDistributeConfig.DistributePolicy policy : requestDistributeConfig.getPolicy()) {
                if (policy.isEnable()) {
                    rateLimiterConcurrentHashMap.put(policy.getGroup(), RateLimiter.create(policy.getRate()));
                }
            }
        } else {
            defaultRateLimiter = null;
        }
        return this;
    }

    public RequestDistributeConfig getRequestDistributeConfig() {
        return requestDistributeConfig;
    }

    public void setRequestDistributeConfig(RequestDistributeConfig requestDistributeConfig) {
        this.requestDistributeConfig = requestDistributeConfig;
    }
}
