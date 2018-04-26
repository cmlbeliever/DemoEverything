package com.cml.framework.netflix.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class MyHystrixCommand extends HystrixCommand<String> {

	private int index;

	protected MyHystrixCommand(Setter setter, int index) {
		super(setter);
		this.index = index;
	}

	@Override
	protected String run() throws Exception {
//		System.out.println("run--->" + index);
//		Thread.sleep(1000);
		// if (index > 20) {
		return "testData:"+ index;
		// }
		// throw new IllegalArgumentException("dd" + index);
	}

	@Override
	protected String getFallback() {
		return "this is my fallback! " + index;
	}

}
