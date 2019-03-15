package com.onlinestore.orderservice.hystrix;

import com.onlinestore.orderservice.contextinfoutils.UserContext;
import com.onlinestore.orderservice.contextinfoutils.UserContextHolder;

import java.util.concurrent.Callable;

public final class DelegatingUserContextCallable<V> implements Callable<V> {

	private final Callable<V> delegatedCallable;
	private UserContext originalUserContext;

	public DelegatingUserContextCallable(Callable<V> delegatedCallable, UserContext userContext) {
		this.delegatedCallable = delegatedCallable;
		this.originalUserContext = userContext;
	}

	@Override
	public V call() throws Exception {
		UserContextHolder.setContext(originalUserContext);
		try {
			return delegatedCallable.call();
		} finally {
			this.originalUserContext = null;
		}
	}

	public static <V> Callable<V> create(Callable<V> delegatedCallable, UserContext userContext) {
		return new DelegatingUserContextCallable<>(delegatedCallable, userContext);
	}
}
