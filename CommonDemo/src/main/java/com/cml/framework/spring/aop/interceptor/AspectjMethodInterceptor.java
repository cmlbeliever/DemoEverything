package com.cml.framework.spring.aop.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

import com.cml.framework.util.LogUtil;

public class AspectjMethodInterceptor {
	public Object log(ProceedingJoinPoint point) {
		LogUtil.log("AspectjMethodInterceptor.log : before");
		try {
			Object result = point.proceed();
			LogUtil.log("AspectjMethodInterceptor.log : after,result: " + result);
			return result+":changed";
		} catch (Throwable e) {
			e.printStackTrace();
			LogUtil.log("AspectjMethodInterceptor.log : exception");
		}
		return null;
	}
}
