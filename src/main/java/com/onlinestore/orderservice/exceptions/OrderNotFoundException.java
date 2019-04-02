package com.onlinestore.orderservice.exceptions;

/**
 * Exception thrown when order can not be found.
 */
public class OrderNotFoundException extends RuntimeException {
	/**
	 * Constructs a new {@code OrderNotFoundException} instance.
	 *
	 * @param orderId unique id of the order
	 */
	public OrderNotFoundException(String orderId) {
		super("Order not found" + orderId);
	}
}
