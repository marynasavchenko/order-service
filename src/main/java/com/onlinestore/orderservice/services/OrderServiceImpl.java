package com.onlinestore.orderservice.services;

import com.onlinestore.orderservice.model.Order;
import com.onlinestore.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> getOrdersByCustomerId(String customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

	@Override
	public Order getOrder(String customerId, String orderId) {
		return orderRepository.findByCustomerIdAndOrderId(customerId, orderId);
	}

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);

	}
}
