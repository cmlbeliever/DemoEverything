package com.cml.framework.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) throws Exception {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		User user = new User();
		user.setUsername("testusername");
		user.setAge(2);
		jedis.set("User:Object:Text:2".getBytes(), serialize(user));
		System.out.println(unserizlize(jedis.get("User:Object:Text:2").getBytes()));
		// jedis.set("testkey", "1");
		// jedis.expire("testkey", 5);
		// System.out.println(jedis.get("testkey"));

		// Transaction transaction = jedis.multi();
		// transaction.set("user", "testUser");
		// transaction.setnx("testNX", "1");
		// transaction.setnx("testNX", "2");
		// transaction.sadd("setTest", "1", "3", "4", "5", "6");
		//
		// User user = new User();
		// user.setUsername("testusername");
		// user.setAge(1);
		// transaction.set("User:Object:Text:1".getBytes(), serialize(user));
		// transaction.exec();
		//
		// Thread.sleep(1000);
		jedis.close();

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
		}

		return null;
	}
}
