package com.onlinestore.orderservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderTest {
	private static final String CUSTOMER_ID = "362";
	private BigDecimal price;
	private ShoppingPositions shoppingPositions;
	private LocalDate orderDate;
	private String orderStatus;
	private List<ShoppingPosition> shoppingPositionList;

	@BeforeEach
	public void setUp() throws Exception {
		Item item1 = new Item("987", "laptop");
		price = new BigDecimal(1000);
		ShoppingPosition shoppingPosition1 = new ShoppingPosition("123", item1, 1, price);

		shoppingPositionList = new LinkedList<>();
		shoppingPositionList.add(shoppingPosition1);
		shoppingPositions = new ShoppingPositions(shoppingPositionList);
		orderDate = LocalDate.of(2019, 2, 15);
		orderStatus = "";
	}

	@Test
	public void shouldCreateOrder() throws Exception {
		Order order = new Order(CUSTOMER_ID, orderDate, orderStatus, price, OrderState.ACCEPTED, shoppingPositions);
		assertEquals(CUSTOMER_ID, order.getCustomerId());
	}
}