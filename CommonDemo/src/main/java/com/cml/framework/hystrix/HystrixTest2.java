package com.cml.framework.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * @Auther: cml
 * @Date: 2019-01-25 15:57
 * @Description:
 */
public class HystrixTest2 {
    public static void main(String[] args) {
        HystrixThreadPoolKey.Factory.asKey("");
        HystrixCommandProperties.Setter()
                .withCircuitBreakerEnabled(true)
                .withExecutionTimeoutInMilliseconds(100);
        //设置线程数量
        HystrixCommand.Setter.withGroupKey(null)
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(5).withMaximumSize(10).withMaxQueueSize(1000));
    }
}
