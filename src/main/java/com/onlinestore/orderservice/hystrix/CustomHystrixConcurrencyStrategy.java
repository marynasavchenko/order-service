package com.onlinestore.orderservice.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;

import java.util.concurrent.BlockingQueue;

public class CustomHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
	private HystrixConcurrencyStrategy existingConcurrencyStrategy;

	public CustomHystrixConcurrencyStrategy(HystrixConcurrencyStrategy existingConcurrencyStrategy) {
		this.existingConcurrencyStrategy = existingConcurrencyStrategy;
	}

	@Override
	public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
		if (existingConcurrencyStrategy != null) return existingConcurrencyStrategy.getBlockingQueue(maxQueueSize);
		return super.getBlockingQueue(maxQueueSize);
	}

	@Override
	public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> requestVariableLifecycle) {
		if (existingConcurrencyStrategy != null) return existingConcurrencyStrategy.getRequestVariable(requestVariableLifecycle);
		return super.getRequestVariable(requestVariableLifecycle);
	}

}
