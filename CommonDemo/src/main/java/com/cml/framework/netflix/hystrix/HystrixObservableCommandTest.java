package com.cml.framework.netflix.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;

public class HystrixObservableCommandTest extends HystrixObservableCommand<String> {

	protected HystrixObservableCommandTest(HystrixCommandGroupKey group) {
		super(group);
	}

	@Override
	protected Observable<String> construct() {
		return Observable.just("testValue");
	}

}
