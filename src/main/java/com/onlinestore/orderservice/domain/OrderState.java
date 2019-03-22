package com.onlinestore.orderservice.domain;

/**
 * Order states that can be used
 * <li>{@link #ACCEPTANCE_PENDING}</li>
 * <li>{@link #ACCEPTED}</li>
 * <li>{@link #REJECTED}</li>
 * <li>{@link #CANCEL_PENDING}</li>
 * <li>{@link #CANCELLED}</li>
 */
public enum OrderState {
	/**
	 * State for order waiting to be accepted.
	 */
	ACCEPTANCE_PENDING,
	/**
	 * State for accepted order.
	 */
	ACCEPTED,
	/**
	 * State for rejected order.
	 */
	REJECTED,
	/**
	 * State for order waiting to be canceled.
	 */
	CANCEL_PENDING,
	/**
	 * State for canceled order.
	 */
	CANCELLED
}
