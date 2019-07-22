package com.onlinestore.orderservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderJpaTest {

	@Autowired
	private TestEntityManager entityManager;

	private static final String CUSTOMER_ID = "362";
	private BigDecimal price;
	private ShoppingPositions shoppingPositions;
	private LocalDate orderDate;
	private String orderStatus;

	@BeforeEach
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
		Order orderToSave = new Order(CUSTOMER_ID, orderDate, orderStatus, price, OrderState.ACCEPTED, shoppingPositions);
		Order foundOrder = this.entityManager.persistAndFlush(orderToSave);
		assertThat(foundOrder.getOrderId()).isNotNull();
	}
}
