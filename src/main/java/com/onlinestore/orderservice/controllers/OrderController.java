package com.onlinestore.orderservice.controllers;

import com.onlinestore.orderservice.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="v1/customers/{customerId}/orders")
public class OrderController {

	@GetMapping("/{customerId}")
	public Order getOrders(@PathVariable("customerId") String customerId) {
		return new Order();
				//orderService.getOrder(customerId);
	}
}
