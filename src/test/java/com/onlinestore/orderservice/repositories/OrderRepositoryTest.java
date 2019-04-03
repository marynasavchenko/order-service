package com.onlinestore.orderservice.repositories;

import com.onlinestore.orderservice.domain.*;
import org.junit.After;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {
	private static final String CUSTOMER_ID_1 = "1234";

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	private TestEntityManager entityManager;

	private Order order;

	@Before
	public void setUp() throws Exception {
		Item item1 = new Item("987", "laptop");
		BigDecimal price1 = new BigDecimal(1000);
		ShoppingPosition shoppingPosition1 = new ShoppingPosition("123", item1, 1, price1);
		List<ShoppingPosition> shoppingPositionList = new LinkedList<>();
		shoppingPositionList.add(shoppingPosition1);
		ShoppingPositions shoppingPositions = new ShoppingPositions(shoppingPositionList);
		LocalDate orderDate = LocalDate.of(2015, 02, 20);
		order = new Order(CUSTOMER_ID_1, orderDate, "", price1, OrderState.ACCEPTED, shoppingPositions);
	}

	@After
	public void tearDown() throws Exception {
		orderRepository.deleteAll();
	}

	@Test
	public void shouldFindOrdersByCustomerId() throws Exception {
		orderRepository.save(order);
		List<Order> orders = orderRepository.findByCustomerId(CUSTOMER_ID_1);
		assertEquals(orders.get(0), order);
	}
}