package com.onlinestore.orderservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.onlinestore.orderservice.clients.Client;
import com.onlinestore.orderservice.domain.Customer;
import com.onlinestore.orderservice.domain.Order;
import com.onlinestore.orderservice.exceptions.OrderNotFoundException;
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
	 * Client used to query for all the customer services
	 */
	private Client customerClient;

	/**
	 * Spring Data repository for orders
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
	 * <p>
	 * Implements simple fallback strategy. Fallback method is called if call from Hystrix fail.
	 * <p>
	 * Implements bulkhead strategy. Defines segregated thread pool with number of threads and queue size
	 * for the number of requests that can queue if the individual threads are busy.
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

	/**
	 * Helper method that builds order list for fallback strategy.
	 *
	 * @param customerId the unique id of the customer
	 * @return fallback list of orders.
	 */
	private List<Order> buildFallbackOrderList(String customerId) {
		List<Order> fallbackList = new ArrayList<>();
		Order order = new Order().withOrderStatus("No orders available");
		fallbackList.add(order);
		return fallbackList;
	}

	/**
	 * Gets specific order from database. Additional customer information added from customer service.
	 *
	 * @param customerId the unique id of the customer
	 * @param orderId    the unique id of the order
	 * @return an order
	 * @throws OrderNotFoundException if {@code orderRepository} returns empty Optional
	 */
	@Override
	public Order getOrder(String customerId, String orderId) {
		Order order = orderRepository.findByCustomerIdAndOrderId(customerId, orderId)
				.orElseThrow(() -> new OrderNotFoundException(customerId, orderId));
		Customer customer = getCustomerInfo(customerId);
		order.withCustomerName(customer.getCustomerName()).withCustomerAddress(customer.getCustomerAddress());
		return order;
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
