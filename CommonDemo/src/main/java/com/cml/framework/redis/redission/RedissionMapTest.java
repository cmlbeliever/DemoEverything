package com.cml.framework.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Iterator;

public class RedissionMapTest {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.99.100:6379");
        RedissonClient redisson = Redisson.create(config);

        System.out.println("====================================");
        RKeys keys = redisson.getKeys();
        Iterator it = keys.getKeys().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("====================================");

        RMapCache cache = redisson.getMapCache("myCache");
        for (int i = 0; i < 10; i++) {
            cache.putIfAbsent("key::" + i, "value:" + i);
        }

        System.out.println("====================================");
        keys = redisson.getKeys();
        it = keys.getKeys().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("====================================");
    }
}
