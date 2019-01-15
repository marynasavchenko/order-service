package com.onlinestore.orderservice.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
	private String orderId;
	private String customerId;
	private Date orderDate;
	private Date shippingDate;
	private String orderStatus;
	private BigDecimal orderTotal;

	public Order() {
	}

}
