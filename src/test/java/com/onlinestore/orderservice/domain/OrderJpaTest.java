package com.onlinestore.orderservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class OrderJpaTest {

	@Autowired
	private TestEntityManager entityManager;

	private static final String CUSTOMER_ID = "362";
	private BigDecimal price;
	private ShoppingPositions shoppingPositions;
	private LocalDate orderDate;
	private String orderStatus;

	@Before
	public void setUp() throws Exception {
		Item item1 = new Item("987", "laptop");
		price = new BigDecimal(1000);
		ShoppingPosition shoppingPosition1 = new ShoppingPosition("123", item1, 1, price);

		List<ShoppingPosition> shoppingPositionList1 = new LinkedList<>();
		shoppingPositionList1.add(shoppingPosition1);
		shoppingPositions = new ShoppingPositions(shoppingPositionList1);
		orderDate = LocalDate.of(2019, 2, 15);
		orderStatus = "";
	}

	@Test
	public void shouldMapOrderEntity() throws Exception {
		Order order = this.entityManager.persistAndFlush(new Order (CUSTOMER_ID, orderDate, orderStatus, price, OrderState.ACCEPTED, shoppingPositions));
		Assertions.assertThat(order.getOrderId()).isNotNull();
		Assertions.assertThat(order.getOrderId()).inUnicode();
	}
}
