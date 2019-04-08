package com.onlinestore.orderservice.hystrix;

import com.onlinestore.orderservice.contextinfoutils.UserContext;
import com.onlinestore.orderservice.contextinfoutils.UserContextHolder;

import java.util.concurrent.Callable;

/**
 * Implementation of {@code Callable} interface.
 * <p>
 * Propagate thread context of the parent thread to Hystrix command.
 *
 * @param <V>  result type of the method call
 */
public final class DelegatingUserContextCallable<V> implements Callable<V> {
	/**
	 * Callable that would be invoked by a thread managed by Hystrix command pool.
	 */
	private final Callable<V> delegatedCallable;

	/**
	 * User context coming from parent thread that initiated the call.
	 */
	private UserContext originalUserContext;

	/**
	 * Constructs a new {@code DelegatingUserContextCallable} instance.
	 *
	 * @param delegatedCallable Callable that would be invoked by a thread
	 * @param userContext       user context coming from parent thread
	 */
	public DelegatingUserContextCallable(Callable<V> delegatedCallable, UserContext userContext) {
		this.delegatedCallable = delegatedCallable;
		this.originalUserContext = userContext;
	}

	/**
	 * Invokes {@code call} method of the delegated Callable. This call invokes method protected
	 * by @HystrixCommand annotation.
	 *
	 * @return result
	 * @throws Exception
	 */
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
