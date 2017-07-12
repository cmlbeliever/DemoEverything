package com.cml.framework.spring.rabbitmq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:com/cml/framework/spring/rabbitmq/application-rabbitmq-tag.xml");
		MailService service = context.getBean(MailService.class);
		System.out.println("before send");
		service.sendMsg("from", "to", "sendMsg");
		System.out.println("send success");
	}
}
