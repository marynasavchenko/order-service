package com.onlinestore.orderservice.services;

import com.onlinestore.orderservice.model.Order;

import java.util.List;

public interface OrderService {
	List<Order> getAllOrders();
	List<Order> getOrdersByCustomerId(String customerId);
	Order getOrder(String customerId, String orderId);
	void saveOrder(Order order);
	void deleteOrder(Order order);
}
