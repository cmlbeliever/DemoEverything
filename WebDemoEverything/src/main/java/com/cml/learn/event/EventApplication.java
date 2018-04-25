package com.cml.learn.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EventApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);

	}

	@Autowired
	private ApplicationContext applicationContext;

	public void testSend() throws Exception {
		System.out.println("send Success===>");
		applicationContext.publishEvent(new TestEvent(1, "name" + Thread.currentThread().getId()));
	}

	@Bean
	public ApplicationRunner runner(EventApplication app) {
		return (arg) -> {
			for (int i = 0; i < 10; i++) {
				app.testSend();
			}
		};
	}

	@EventListener
	public void hanleEvent(TestEvent e) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("=====>接收到event：" + e + ",threadId:" + Thread.currentThread().getId());
		throw new RuntimeException("测试event报错！！");
	}
}
