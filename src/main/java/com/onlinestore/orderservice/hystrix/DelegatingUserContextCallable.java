package com.onlinestore.orderservice.hystrix;

import java.util.concurrent.Callable;

public final class DelegatingUserContextCallable<V> implements Callable<V> {
	@Override
	public V call() throws Exception {
		return null;
	}
}
