package com.cml.framework.distribute.id;

import com.google.common.util.concurrent.RateLimiter;

public class RequesTokenGenerator {
    public static void main(String[] args) {
        RateLimiter rateLimiter=RateLimiter.create(1);
    }
}
