package com.onlinestore.orderservice.services;

import com.onlinestore.orderservice.domain.Order;

import java.util.List;

/**
 * {@link OrderService} defines an interface that supports operations for managing customers orders.
 */
public interface OrderService {
	/**
	 * Returns all orders of all customers.
	 *
	 * @return the list of orders of all customers
	 */
	List<Order> getAllOrders();

	/**
	 * Returns all orders of a customer, specified by customerId.
	 *
	 * @param customerId the unique id of the customer
	 * @return the list of orders of one customer
	 */
	List<Order> getOrdersByCustomerId(String customerId);

	Order getOrder(String customerId, String orderId);

	/**
	 * Modifies an existing order.
	 *
	 * @param order an order to be modified
	 */
	void updateOrder(Order order);

	/**
	 * Saves new order.
	 *
	 * @param order an order to be saved
	 */
	void saveOrder(Order order);

	/**
	 * Deletes an order.
	 *
	 * @param orderId the unique id of the order
	 */
	void deleteOrder(String orderId);
}
