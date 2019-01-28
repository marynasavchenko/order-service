package com.onlinestore.orderservice.controllers;

import com.onlinestore.orderservice.model.Order;
import com.onlinestore.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/customers/{customerId}/orders")
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(value = "/")
	public List<Order> getOrdersWithCustomer(@PathVariable("customerId") String customerId) {
		return orderService.getOrdersByCustomerId(customerId);
	}

	@GetMapping(value = "/{orderId}/{clientType}")
	public Order getOrder(@PathVariable("customerId") String customerId,
	                      @PathVariable("orderId") String orderId,
	                      @PathVariable("clientType") String clientType) {
		return orderService.getOrder(customerId, orderId, clientType);
	}

	@PutMapping(value = "/{orderId}")
	public void updateOrder(@PathVariable("orderId") String orderId,
	                        @RequestBody Order order) {
		orderService.updateOrder(order);
	}

	@PostMapping(value = "/")
	public void saveOrder(@RequestBody Order order) {
		orderService.saveOrder(order);
	}

	@DeleteMapping(value = "/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") String orderId,
	                        @RequestBody Order order) {
		orderService.deleteOrder(order);
	}
}
