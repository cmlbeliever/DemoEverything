package com.cml.framework.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.PatternMessageListener;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryExpiredListener;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissionExpireTest {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.99.100:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getPatternTopic("__key*__:*").addListener(new PatternMessageListener<Object>() {
            @Override
            public void onMessage(String pattern, String channel, Object msg) {
                System.out.println("__key:===channel===>" + channel + ":msg:" + msg + ",pattern:" + pattern);
            }
        });
        redisson.getPatternTopic("__key*__:*").addListener(new PatternMessageListener<Object>() {
            @Override
            public void onMessage(String pattern, String channel, Object msg) {
                System.out.println("getPatternTopic:===channel===>" + channel + ":msg:" + msg + ",pattern:" + pattern);
            }
        });

        RExpirable expirable = redisson.getMap("cache_timeout_ttt");
        expirable.expireAt(System.currentTimeMillis() + 1000);
        RBucket<String> bucket = redisson.getBucket("");
        bucket.set("testExpire", 5, TimeUnit.SECONDS);


        redisson.getPatternTopic("redisson_map_cache_*").addListener(new PatternMessageListener<Object>() {
            @Override
            public void onMessage(String pattern, String channel, Object msg) {
                System.out.println("===channel===>" + channel + ":msg:" + msg + ",pattern:" + pattern);
            }
        });

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                final String key = "expireTest" + i;
                RMapCache<String, String> cache = redisson.getMapCache(key);
                long ttl = i > 7 ? 3 : 4;
                cache.putIfAbsent("testV" + i, "newValue:" + i, ttl, TimeUnit.SECONDS);
                cache.addListener(new EntryExpiredListener<String, String>() {

                    @Override
                    public void onExpired(EntryEvent<String, String> event) {
                        System.out.println("====>expire:" + event.getKey() + ",key:" + key + ",value:" + event.getValue());
                    }
                });
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                final String key = "expireTest" + i;
                RMapCache<String, String> cache = redisson.getMapCache(key);
                System.out.println("remove===>" + cache.remove("testV" + i));
            }
        }).start();

        Thread.sleep(1000000);
        redisson.shutdown();
    }
}
