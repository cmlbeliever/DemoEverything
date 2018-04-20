package com.cml.framework.spring.retry;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class RetryEntance {
	public static void main(String[] args) throws Exception {
		RetryTemplate template = new RetryTemplate();

		// TimeoutRetryPolicy policy = new TimeoutRetryPolicy();
		// policy.setTimeout(3000L);

		SimpleRetryPolicy timesRetryPolicy = new SimpleRetryPolicy(4);
		FixedBackOffPolicy f = new FixedBackOffPolicy();
		f.setBackOffPeriod(1500);
		template.setBackOffPolicy(f);
		// CompositeRetryPolicy finalPolicy = new CompositeRetryPolicy();
		// finalPolicy.setPolicies(new RetryPolicy[] { timesRetryPolicy, policy
		// });

		template.setRetryPolicy(timesRetryPolicy);
		template.setThrowLastExceptionOnExhausted(true);

		String result = template.execute(new RetryCallback<String, Exception>() {

			public String doWithRetry(RetryContext context) {
				// Do stuff that might fail, e.g. webservice operation
				if (context.getRetryCount() < 3) {
					System.out.println("次数不满足！！！" + context.getRetryCount());
					try {
						// Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					throw new RuntimeException("exception!!!" + context.getRetryCount());
				}
				return "this is result:" + context.getRetryCount();
			}

		});
		System.out.println("result==>" + result);
	}
}
