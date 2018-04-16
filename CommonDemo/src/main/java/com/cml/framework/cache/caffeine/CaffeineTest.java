package com.cml.framework.cache.caffeine;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

/**
 * https://github.com/ben-manes/caffeine Caffeine测试代码
 * 
 * @author cml
 *
 */
public class CaffeineTest {
	public static void main(String[] args) throws Exception {
		LoadingCache<String, Object> cache = Caffeine.newBuilder().maximumSize(100_0000).expireAfterAccess(10, TimeUnit.MINUTES).initialCapacity(10)
				.recordStats().build(new CacheLoader<String, Object>() {

					@Override
					public Object load(String key) throws Exception {
						// 如果缓存中没有数据，则从db中加载数据
						return "defauly:" + key;
					}
				});
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 100_0000; i++) {
			cache.put("key" + i, "values:" + i);
		}
		System.out.println("100W条数据耗时：" + (System.currentTimeMillis() - startTime) + "ms");

		System.out.println(cache.get("key1"));
		System.out.println(cache.get("key2"));
		cache.put("key2", "am key2 value!");
		System.out.println(cache.get("key-1"));
		
		//获取缓存命中率
		System.out.println(cache.stats().hitRate());

		// // 强制刷新缓存数据
		// cache.refresh("key2");
		// System.out.println(cache.get("key2"));
		// Thread.sleep(20000);
		// System.out.println("after sleep 20s!!!");
		// System.out.println(cache.get("key1"));
		// System.out.println(cache.get("key2"));
	}
}
