package com.cml.learn.retry;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class RetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetryApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(RetryTestService retryTestService) {
		return (args) -> {
			try {
				retryTestService.testRetry(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

}
