package com.cml.framework.guava;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: cml
 * @Date: 2018-08-08 15:10
 * @Description:
 */
public class CacheTimeoutTest {
    public static void main(String[] args) throws Exception {
        LoadingCache<String, Boolean> cache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS).build(new CacheLoader<String, Boolean>() {
            @Override
            public Boolean load(String key) throws Exception {
                System.out.println("load-->" + key);
                return false;
            }
        });
        System.out.println(cache.get("ddd"));
        System.out.println(cache.get("ddd"));
        System.out.println(cache.get("ddd"));
        Thread.sleep(2000);
        System.out.println(cache.get("ddd"));
    }
}
