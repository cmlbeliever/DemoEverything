package com.cml.framework.spring.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.cml.framework.util.LogUtil;

public class MyMethodInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		LogUtil.log("MyMethodInterceptor:before");
		Object result = invocation.proceed();
		LogUtil.log("MyMethodInterceptor:after,result:" + result);
		return result;
	}

}
