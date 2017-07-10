package com.cml.framework.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		System.out.println("=====测试AOP=====");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/cml/framework/spring/aop/application-aop.xml");
		AopService aopService = context.getBean(AopService.class);
		String result = aopService.testAop("key");
		System.out.println("使用Spring方式，执行AopService返回：" + result);

		System.out.println("\n\n\n");
		
		AopService2 aopService2 = context.getBean(AopService2.class);
		result = aopService2.testAop("key");
		System.out.println("使用Aspectj方式，执行AopService2返回：" + result);
	}
}
