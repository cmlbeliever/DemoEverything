package com.cml.framework.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissionTest {
	public static void main(String[] args) {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://192.168.99.100:6379");
		RedissonClient redisson = Redisson.create(config);

		RAtomicLong testLong = redisson.getAtomicLong("testLong");
		// testLong.set(11);
		testLong.incrementAndGetAsync().thenAccept(t -> {
			System.out.println("value :" + t);
		});

		System.out.println(testLong.get());

		RMapCache<String, String> map = redisson.getMapCache("mapTest");
		map.addAndGet("" + System.currentTimeMillis(), 1);
		System.out.println(map.keySet());

		RLock rlock = redisson.getLock("mylock");
		rlock.lock();

		System.out.println("获得锁对象：" + rlock.getName());

		rlock.unlock();

		redisson.shutdown();
	}
}
