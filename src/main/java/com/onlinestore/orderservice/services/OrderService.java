package com.onlinestore.orderservice.services;

import com.onlinestore.orderservice.domain.Order;

import java.util.List;

public interface OrderService {
	List<Order> getAllOrders();
	List<Order> getOrdersByCustomerId(String customerId);
	Order getOrder(String customerId, String orderId, String clientType);
	void updateOrder(Order order);
	void saveOrder(Order order);
	void deleteOrder(String orderId);
}
