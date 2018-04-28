package com.cml.framework.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.config.Config;

public class TopicTest {

	static String TOPIC = "testTopic";

	public static void main(String[] args) throws Exception {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://192.168.99.100:6379");
		RedissonClient redisson = Redisson.create(config);

		RTopic<String> topic = redisson.getTopic(TOPIC);
		topic.addListener(new MessageListener<String>() {

			@Override
			public void onMessage(String channel, String msg) {
				System.out.println("接收到消息：channel:" + channel + ",msg:" + msg);
			}
		});
		topic.addListener(new MessageListener<String>() {

			@Override
			public void onMessage(String channel, String msg) {
				System.out.println("接收到消息2：channel:" + channel + ",msg:" + msg);
			}
		});
		for (int i = 0; i < 10; i++) {
			topic.publish("test" + i);
		}

		Thread.sleep(20000);

		redisson.shutdown();
	}
}
