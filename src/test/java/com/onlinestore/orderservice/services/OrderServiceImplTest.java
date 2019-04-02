package com.onlinestore.orderservice.services;

import com.onlinestore.orderservice.clients.Client;
import com.onlinestore.orderservice.domain.Order;
import com.onlinestore.orderservice.repositories.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
	@Mock
	private OrderRepository orderRepository;

	@Mock
	private Client customerClient;

	@Mock
	private Order order;

	private OrderService orderService;

	@Before
	public void setUp() throws Exception {
		orderService = new OrderServiceImpl(orderRepository, customerClient);
	}

	@Test
	public void shouldSaveOrder() throws Exception {
		orderService.saveOrder(order);
		verify(orderRepository).save(order);
	}
}