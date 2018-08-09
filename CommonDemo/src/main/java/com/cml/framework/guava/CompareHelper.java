package com.cml.framework.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * @Auther: cml
 * @Date: 2018-07-24 17:59
 * @Description:
 */
public class CompareHelper {
    public static void main(String[] args) throws ExecutionException {

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return "";
            }
        });

        String token = cache.get("");
        System.out.println("token:" + token);
    }
}
