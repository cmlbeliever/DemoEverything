package com.cml.request.distribute;

public abstract class AbstractTokenRequestDistribute<T> extends AbstractRequestDistribute<T> {

    private DistributeRateLimiter rateLimiter;
    private DistributeGroupManager<T> groupManager;

    @Override
    protected boolean tryAquireToken(T args) {
        String group = groupManager.getGroup(args);
        if (null != group) {
            return rateLimiter.tryAquireToken(group);
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
