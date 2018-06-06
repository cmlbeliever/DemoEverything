package com.cml.framework.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RExpirable;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.PatternMessageListener;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryExpiredListener;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissionExpireMapTest {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.99.100:6379");
        RedissonClient redisson = Redisson.create(config);

        String cacheKey = "testCacheKey";
        RMapCache<String, String> cache = redisson.getMapCache(cacheKey);


        cache.addListener(new EntryExpiredListener<String, String>() {

            @Override
            public void onExpired(EntryEvent<String, String> event) {
                System.out.println("====>expire:" + event.getKey() + ",key:" + ",value:" + event.getValue());
            }
        });
        cache.addListener(new EntryExpiredListener<String, String>() {

            @Override
            public void onExpired(EntryEvent<String, String> event) {
                System.out.println("====>expire:" + event.getKey() + ",key:" + ",value:" + event.getValue());
            }
        });
        for (int i = 0; i < 10; i++) {
            long ttl = 2;
            final String key = "expireTest2:" + i;
            cache.putIfAbsent(key, "newValue:" + i, ttl, TimeUnit.SECONDS);
//            cache.addListener(new EntryExpiredListener<String, String>() {
//
//                @Override
//                public void onExpired(EntryEvent<String, String> event) {
//                    System.out.println("====>expire:" + event.getKey() + ",key:" + key + ",value:" + event.getValue());
//                }
//            });
        }
        Thread.sleep(10000);
        System.out.println("=========================================");
        for (int i = 0; i < 10; i++) {
            long ttl = 2;
            final String key = "expireTest2:" + i;
            cache.putIfAbsent(key, "newValue:" + i, ttl, TimeUnit.SECONDS);
//            cache.addListener(new EntryExpiredListener<String, String>() {
//
//                @Override
//                public void onExpired(EntryEvent<String, String> event) {
//                    System.out.println("====>expire:" + event.getKey() + ",key:" + key + ",value:" + event.getValue());
//                }
//            });
        }
        int evictIndex = (int) (Math.random() * 10);
        System.out.println("移除===》" + evictIndex);
        cache.remove("expireTest2:" + evictIndex);


        Thread.sleep(1000000);
        redisson.shutdown();
    }
}
