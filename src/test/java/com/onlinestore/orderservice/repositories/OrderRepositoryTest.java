package com.onlinestore.orderservice.repositories;

import com.onlinestore.orderservice.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {
	private static final String CUSTOMER_ID_1 = "1234";
	private static final String CUSTOMER_ID_2 = "4321";

	@Autowired
	private OrderRepository orderRepository;

	private Order order1;
	private Order order2;
	private Order order3;
	private String orderStatus;

	@Before
	public void setUp() throws Exception {
		Item item1 = new Item("987", "laptop");
		BigDecimal price1 = new BigDecimal(1000);
		ShoppingPosition shoppingPosition1 = new ShoppingPosition("123", item1, 1, price1);

		List<ShoppingPosition> shoppingPositionList1 = new LinkedList<>();
		shoppingPositionList1.add(shoppingPosition1);
		ShoppingPositions shoppingPositions1 = new ShoppingPositions(shoppingPositionList1);

		List<ShoppingPosition> shoppingPositionList2 = new LinkedList<>();
		shoppingPositionList2.add(shoppingPosition1);
		ShoppingPositions shoppingPositions2 = new ShoppingPositions(shoppingPositionList2);

		List<ShoppingPosition> shoppingPositionList3 = new LinkedList<>();
		shoppingPositionList3.add(shoppingPosition1);
		ShoppingPositions shoppingPositions3 = new ShoppingPositions(shoppingPositionList3);

		LocalDate orderDate = LocalDate.of(2015, 2, 20);
		orderStatus = "";
		order1 = new Order(CUSTOMER_ID_1, orderDate, orderStatus, price1, OrderState.ACCEPTED, shoppingPositions1);
		order2 = new Order(CUSTOMER_ID_1, orderDate, orderStatus, price1, OrderState.ACCEPTED, shoppingPositions2);
		order3 = new Order(CUSTOMER_ID_2, orderDate, orderStatus, price1, OrderState.ACCEPTED, shoppingPositions3);
	}

	@After
	public void tearDown() throws Exception {
		orderRepository.deleteAll();
	}

	@Test
	public void shouldFindOrdersByCustomerId() throws Exception {
		orderRepository.save(order1);
		List<Order> orders = orderRepository.findByCustomerId(CUSTOMER_ID_1);
		Assertions.assertThat(orders.size()).isEqualTo(1);
		Assertions.assertThat(orders.iterator().next().getCustomerId()).isEqualTo(CUSTOMER_ID_1);
		assertEquals(orders.get(0), order1);
	}

	@Test
	public void shouldFindOrderByCustomerIdAndOrderId() throws Exception {
		orderRepository.save(order1);
		orderRepository.save(order2);
		orderRepository.save(order3);
		Optional<Order> optionalOrder = orderRepository.findByCustomerIdAndOrderId(CUSTOMER_ID_1, order2.getOrderId());
		Order foundOrder2 = optionalOrder.get();
		assertEquals(order2, foundOrder2);
	}

	@Test
	public void shouldDeleteOrderById() throws Exception {
		orderRepository.save(order1);
		orderRepository.deleteById(order1.getOrderId());
		Optional<Order> optionalOrder = orderRepository.findByCustomerIdAndOrderId(CUSTOMER_ID_1, order1.getOrderId());
		assertEquals(Optional.empty(), optionalOrder);
	}
}