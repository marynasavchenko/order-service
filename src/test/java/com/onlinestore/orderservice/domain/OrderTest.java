package com.onlinestore.orderservice.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderTest {
	private static final String CUSTOMER_ID = "362";
	private BigDecimal price;
	private ShoppingPositions shoppingPositions;
	private LocalDate orderDate;

	@Before
	public void setUp() throws Exception {
		Item item1 = new Item("987", "laptop");
		price = new BigDecimal(1000);
		ShoppingPosition shoppingPosition1 = new ShoppingPosition("123", item1, 1, price);

		List<ShoppingPosition> shoppingPositionList1 = new LinkedList<>();
		shoppingPositionList1.add(shoppingPosition1);
		shoppingPositions = new ShoppingPositions(shoppingPositionList1);
		LocalDate orderDate = LocalDate.of(2019, 02, 15);
	}

	@Test
	public void shouldCreateOrder() throws Exception {
		Order order = new Order(CUSTOMER_ID, orderDate, "", price, OrderState.ACCEPTED, shoppingPositions);
		assertEquals(CUSTOMER_ID, order.getCustomerId());
	}
}