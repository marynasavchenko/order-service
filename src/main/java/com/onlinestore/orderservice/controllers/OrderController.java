package com.onlinestore.orderservice.controllers;

import com.onlinestore.orderservice.model.Order;
import com.onlinestore.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO add methods update, delete, save
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

	@GetMapping(value = "/{orderId}")
	public Order getOrder(@PathVariable("customerId") String customerId,
	                                  @PathVariable("orderId") String orderId) {
		return orderService.getOrder(customerId, orderId);
	}


}
