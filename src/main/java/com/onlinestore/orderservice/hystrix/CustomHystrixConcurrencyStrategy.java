package com.onlinestore.orderservice.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import java.util.concurrent.BlockingQueue;

public class CustomHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
	private HystrixConcurrencyStrategy existingConcurrencyStrategy;

	public CustomHystrixConcurrencyStrategy(HystrixConcurrencyStrategy existingConcurrencyStrategy) {
		this.existingConcurrencyStrategy = existingConcurrencyStrategy;
	}

	@Override
	public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize){
		BlockingQueue<Runnable> blockingQueue;
		if(existingConcurrencyStrategy!=null) blockingQueue=existingConcurrencyStrategy.getBlockingQueue(maxQueueSize);
		else blockingQueue=super.getBlockingQueue(maxQueueSize);
		return blockingQueue;
	}

}
