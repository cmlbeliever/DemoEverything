package com.cml.framework.cache.ehcache3;

import java.io.File;
import java.io.Serializable;
import java.nio.ByteBuffer;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;

public class EhcacheTest {
	public static void main(String[] args) throws Exception {
		PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.with(CacheManagerBuilder.persistence(new File("E:/cache", "myData")))
				.withCache("threeTieredCache",
						CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, Serializable.class,
								ResourcePoolsBuilder.newResourcePoolsBuilder().heap(1024, EntryUnit.ENTRIES).disk(200, MemoryUnit.MB, true)))
				.build(true);
		Cache<Long, Serializable> threeTieredCache = persistentCacheManager.getCache("threeTieredCache", Long.class, Serializable.class);

		long testSize = 3000;
		for (long i = 0; i < testSize; i++) {
			final long index = i;
			new Thread() {
				public void run() {
					System.out.println(index + ",上次缓存value:" + threeTieredCache.get(index));
					threeTieredCache.put(index, new TestModel("test" + index, System.currentTimeMillis()));
				};
			}.start();
		}

		Thread.sleep(20000);
		persistentCacheManager.close();
	}

	static class TestModel implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private long time;

		public TestModel(String name, long time) {
			super();
			this.name = name;
			this.time = time;
		}

		@Override
		public String toString() {
			return "TestModel [name=" + name + ", time=" + time + "]";
		}

	}

	public static class ObjectSerial implements Serializer<Object> {

		@Override
		public ByteBuffer serialize(Object object) throws SerializerException {
			ByteBuffer.wrap("".getBytes());
			return null;
		}

		@Override
		public Object read(ByteBuffer binary) throws ClassNotFoundException, SerializerException {
			return null;
		}

		@Override
		public boolean equals(Object object, ByteBuffer binary) throws ClassNotFoundException, SerializerException {
			return object.equals(read(binary));
		}

	}
}
