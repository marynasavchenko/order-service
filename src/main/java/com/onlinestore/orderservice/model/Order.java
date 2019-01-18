package com.onlinestore.orderservice.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//TODO add fields for customer
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String orderId;

	private String customerId;

	private Date orderDate;

	private String orderStatus;

	private BigDecimal orderTotal;

	@Enumerated(EnumType.STRING)
	private OrderState orderState;

	@Embedded
	private ShoppingPositions shoppingPositions;
}
