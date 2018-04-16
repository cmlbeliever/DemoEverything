package com.cml.framework.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest2 {
	public static void main(String[] args) throws Exception {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		final JedisPool pool = new JedisPool(config, "192.168.99.100");
		pool.getResource().del("userTokens");
		ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 1);
		long time = System.currentTimeMillis();
		// jedis.set("testIncr", "0");
		for (int i = 0; i < 4_0000; i++) {
			ex.execute(new Runnable() {

				@Override
				public void run() {
					Jedis jedis = pool.getResource();
					jedis.sadd("userTokens", UUID.randomUUID().toString());
					jedis.close();
				}
			});
			// Long value = jedis.incr("testIncr");
			// System.out.println(i + ":" + value);
		}

		ex.shutdown();
		while (!ex.awaitTermination(10, TimeUnit.MICROSECONDS)) {

		}
		System.out.println("end:" + (System.currentTimeMillis() - time));
		pool.close();
	}

	public static class User implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6290798927716738291L;
		private String username;
		private int age;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "User [username=" + username + ", age=" + age + "]";
		}

	}

	// 序列化
	public static byte[] serialize(Object obj) {
		ObjectOutputStream obi = null;
		ByteArrayOutputStream bai = null;
		try {
			bai = new ByteArrayOutputStream();
			obi = new ObjectOutputStream(bai);
			obi.writeObject(obj);
			byte[] byt = bai.toByteArray();
			return byt;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				obi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bai.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 反序列化
	public static Object unserizlize(byte[] byt) {
		ObjectInputStream oii = null;
		ByteArrayInputStream bis = null;
		bis = new ByteArrayInputStream(byt);
		try {
			oii = new ObjectInputStream(bis);
			Object obj = oii.readObject();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				oii.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
