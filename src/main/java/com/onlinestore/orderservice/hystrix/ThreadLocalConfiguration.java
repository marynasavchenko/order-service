package com.onlinestore.orderservice.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;

public class ThreadLocalConfiguration {

	private HystrixConcurrencyStrategy existingConcurrencyStrategy;

	@Autowired(required = false)
	public ThreadLocalConfiguration(HystrixConcurrencyStrategy existingConcurrencyStrategy) {
		this.existingConcurrencyStrategy = existingConcurrencyStrategy;
	}


}
