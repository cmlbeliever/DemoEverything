package com.cml.request.distribute;

public abstract class AbstractTokenRequestDistribute<T, R> extends AbstractRequestDistribute<T, R> {

    private DistributeRateLimiter rateLimiter;
    private DistributeGroupManager<T> groupManager;

    @Override
    protected boolean tryAquireToken(T args) {
        String group = groupManager.getGroup(args);
        if (null != group) {
            return rateLimiter.tryAcquireToken(group);
        }
        return false;
    }

    public DistributeGroupManager<T> getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(DistributeGroupManager<T> groupManager) {
        this.groupManager = groupManager;
    }

    public DistributeRateLimiter getRateLimiter() {
        return rateLimiter;
    }

    public void setRateLimiter(DistributeRateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }
}
