package com.onlinestore.orderservice.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

public class CustomHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
	private HystrixConcurrencyStrategy existingConcurrencyStrategy;

	public CustomHystrixConcurrencyStrategy(HystrixConcurrencyStrategy existingConcurrencyStrategy) {
		this.existingConcurrencyStrategy = existingConcurrencyStrategy;
	}

}
