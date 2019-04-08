package com.onlinestore.orderservice.exceptions;

/**
 * Exception thrown when order can not be found.
 */
public class OrderNotFoundException extends RuntimeException {
	/**
	 * Constructs a new {@code OrderNotFoundException} instance.
	 *
	 * @param customerId unique id of the customer
	 * @param orderId    unique id of the order
	 */
	public OrderNotFoundException(String customerId, String orderId) {
		super("Order " + orderId + "of customer " + customerId + "not found");
	}
}
