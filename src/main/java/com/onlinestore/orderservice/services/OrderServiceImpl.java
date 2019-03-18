package com.onlinestore.orderservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.onlinestore.orderservice.clients.Client;
import com.onlinestore.orderservice.domain.Customer;
import com.onlinestore.orderservice.domain.Order;
import com.onlinestore.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
	@HystrixCommand(fallbackMethod = "buildFallbackOrderList",
			threadPoolKey = "ordersByCustomerThreadPool",
			threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "30"),
									@HystrixProperty(name = "maxQueueSize", value = "10")},
			commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
					value = "7000")})
	public List<Order> getOrdersByCustomerId(String customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

	private List<Order> buildFallbackOrderList(String customerId) {
		List<Order> fallbackList = new ArrayList<>();
		Order order = new Order().withOrderStatus("No orders available");
		fallbackList.add(order);
		return fallbackList;
	}

	@Override
	public Order getOrder(String customerId, String orderId) {
		Order order = orderRepository.findByCustomerIdAndOrderId(customerId, orderId);
		Customer customer = getCustomerInfo(customerId);
		order.withCustomerName(customer.getCustomerName())
				.withCustomerAddress(customer.getCustomerAddress());
		return order;
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
