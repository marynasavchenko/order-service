package com.onlinestore.orderservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
	private String orderId;
	private String customerId;
	private Date orderDate;
	private Date shippingDate;
	private String orderStatus;
	private boolean paid;

}
