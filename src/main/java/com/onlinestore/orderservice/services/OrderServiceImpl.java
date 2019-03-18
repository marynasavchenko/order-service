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

/**
 * Implementation of the {@link OrderService} interface.
 */
@Service
public class OrderServiceImpl implements OrderService {

	/**
	 * Spring Data repository for orders
	 */
	private Client customerClient;
	/**
	 * Client used to query for all the customer services
	 */
	private OrderRepository orderRepository;

	/**
	 * Constructs a new {@code OrderServiceImpl} instance.
	 *
	 * @param orderRepository Spring Data repository for orders
	 * @param customerClient  client used to query for all the customer services
	 */
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, Client customerClient) {
		this.orderRepository = orderRepository;
		this.customerClient = customerClient;
	}

	/**
	 * Helper method that retrieves customer from one of the customerservice instances.
	 *
	 * @param customerId the unique id of the customer
	 * @return the customer
	 */
	private Customer getCustomerInfo(String customerId) {
		return customerClient.getCustomer(customerId);
	}

	/**
	 * Gets all orders from database.
	 *
	 * @return list of orders
	 */
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	/**
	 * Gets all orders of the specific customer from database.
	 *
	 * @param customerId the unique id of the customer
	 * @return list of orders of the specific customer
	 */
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

	/**
	 * Gets specific order from database.
	 *
	 * @param customerId the unique id of the customer
	 * @param orderId    the unique id of the order
	 * @return
	 */
	@Override
	public Order getOrder(String customerId, String orderId) {
		Order order = orderRepository.findByCustomerIdAndOrderId(customerId, orderId);
		Customer customer = getCustomerInfo(customerId);
		order.withCustomerName(customer.getCustomerName()).withCustomerAddress(customer.getCustomerAddress());
		return order;
	}

	/**
	 * Modifies specific order in database.
	 *
	 * @param order an order to be modified
	 */
	@Override
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	/**
	 * Saves order to database.
	 *
	 * @param order an order to be saved
	 */
	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

	/**
	 * Delete order from database.
	 *
	 * @param orderId the unique id of the order
	 */
	@Override
	public void deleteOrder(String orderId) {
		orderRepository.deleteById(orderId);
	}
}
