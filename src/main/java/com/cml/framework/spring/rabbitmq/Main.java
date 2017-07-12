package com.cml.framework.spring.rabbitmq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:com/cml/framework/spring/rabbitmq/application-rabbitmq-tag.xml");
		MailService service = context.getBean(MailService.class);
		System.out.println("before send");
		for (int i = 0; i < 10; i++) {
			service.sendMsg("from", "to", "sendMsgXXXX" + i);
			Thread.sleep(2000);
		}
		System.out.println("send success");
		context.close();
	}
}
