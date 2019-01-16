package com.onlinestore.orderservice.services;

import com.onlinestore.orderservice.model.Order;

import java.util.List;

public interface OrderService {
	List<Order> getAllOrders();
	List<Order> getOrdersByCustomerId();
	void saveOrder(Order order);
	void deleteOrder(Order order);
}
