package com.onlinestore.orderservice.exceptions;

/**
 * Exception thrown when order can not be found.
 */
public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(String orderId) {
		super("Order not found" + orderId);
	}
}
