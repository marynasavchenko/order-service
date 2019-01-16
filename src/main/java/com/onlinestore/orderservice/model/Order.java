package com.onlinestore.orderservice.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO add fields for customer
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private String orderId;

	private String customerId;

	private Date orderDate;

	private String orderStatus;
	private List<ShoppingPosition> shoppingPositions = new ArrayList<>();
}
