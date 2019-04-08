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
	 * @return list of orders of all customers
	 */
	List<Order> getAllOrders();

	/**
	 * Returns all orders of the customer, specified by customerId.
	 *
	 * @param customerId unique id of the customer
	 * @return list of orders of one customer
	 */
	List<Order> getOrdersByCustomerId(String customerId);

	/**
	 * Returns specific order of the specific customer.
	 *
	 * @param customerId unique id of the customer
	 * @param orderId    unique id of the order
	 * @return specific order
	 */
	Order getOrder(String customerId, String orderId);

	/**
	 * Saves new order.
	 *
	 * @param order an order to be saved
	 */
	void saveOrder(Order order);

	/**
	 * Deletes an order.
	 *
	 * @param orderId unique id of the order
	 */
	void deleteOrder(String orderId);
}
