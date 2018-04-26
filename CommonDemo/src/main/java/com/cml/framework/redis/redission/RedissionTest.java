package com.cml.framework.redis.redission;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryExpiredListener;
import org.redisson.api.map.event.MapEntryListener;
import org.redisson.config.Config;

public class RedissionTest {
	public static void main(String[] args) throws Exception {
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

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				final String key = "newTest" + i;
				RMapCache<String, String> cache = redisson.getMapCache(key);
				long ttl = i > 7 ? 1 : 50;
				cache.put("testV" + i, "xxx", ttl, TimeUnit.SECONDS);
				cache.addListener(new EntryExpiredListener<String, String>() {

					@Override
					public void onExpired(EntryEvent<String, String> event) {
						System.out.println("====>expire:" + event.getKey() + ",key:" + key + ",value:" + event.getValue());
					}
				});
			}
		}).start();

		Thread.sleep(20000);

		redisson.shutdown();
	}
}
