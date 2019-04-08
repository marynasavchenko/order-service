package com.onlinestore.orderservice.controllers;

import com.onlinestore.orderservice.domain.Order;
import com.onlinestore.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for managing customers orders.
 */
@RestController
@RequestMapping(value = "/v1/customers/{customerId}/orders")
public class OrderController {
	/**
	 * Service that supports operations with orders.
	 */
	private OrderService orderService;

	/**
	 * Constructs a new {@code OrderController} instance.
	 *
	 * @param orderService service that supports operations with orders
	 */
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * GET  / : gets list of orders of the specified customer.
	 *
	 * @param customerId unique id of the customer
	 * @return list of orders of the specified customer
	 */
	@GetMapping(value = "/")
	public List<Order> getOrdersWithCustomer(@PathVariable("customerId") String customerId) {
		return orderService.getOrdersByCustomerId(customerId);
	}

	/**
	 * GET  /{orderId} : gets specified order.
	 *
	 * @param customerId unique id of the customer
	 * @param orderId    unique id of the order
	 * @return order
	 */
	@GetMapping(value = "/{orderId}")
	public Order getOrder(@PathVariable("customerId") String customerId,
	                      @PathVariable("orderId") String orderId) {
		return orderService.getOrder(customerId, orderId);
	}

	/**
	 * PUT /{orderId} : updates an existing order.
	 *
	 * @param orderId unique id of the order
	 * @param order
	 */
	@PutMapping(value = "/{orderId}")
	public void updateOrder(@PathVariable("orderId") String orderId,
	                        Order order) {
		orderService.saveOrder(order);
	}

	/**
	 * POST / : saves new order.
	 *
	 * @param order order to be saved
	 */
	@PostMapping(value = "/")
	public void saveOrder(Order order) {
		orderService.saveOrder(order);
	}

	/**
	 * DELETE /{orderId} : deletes existing order.
	 *
	 * @param orderId unique id of the order
	 */
	@DeleteMapping(value = "/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") String orderId) {
		orderService.deleteOrder(orderId);
	}
}
