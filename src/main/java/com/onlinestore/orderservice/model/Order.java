package com.onlinestore.orderservice.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
	private String orderId;
	private String customerId;
	private Date orderDate;
	private String orderStatus;
	private List<ShoppingPosition> shoppingPositions;
}
