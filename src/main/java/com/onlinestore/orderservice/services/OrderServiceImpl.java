package com.onlinestore.orderservice.services;

import com.onlinestore.orderservice.clients.Client;
import com.onlinestore.orderservice.model.Customer;
import com.onlinestore.orderservice.model.Order;
import com.onlinestore.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO add @Qualifier for clientType?
@Service
public class OrderServiceImpl implements OrderService {

	private Client customerClient;

	private OrderRepository orderRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, Client customerClient) {
		this.orderRepository = orderRepository;
		this.customerClient = customerClient;
	}

	private Customer getCustomerInfo(String customerId) {
		return customerClient.getCustomer(customerId);
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
	public Order getOrder(String customerId, String orderId, String clientType) {
		return orderRepository.findByCustomerIdAndOrderId(customerId, orderId);
	}

	@Override
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void deleteOrder(String orderId) {
		orderRepository.deleteById(orderId);

	}
}
