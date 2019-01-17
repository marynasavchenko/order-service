package com.onlinestore.orderservice.controllers;

import com.onlinestore.orderservice.services.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO getOrder, updateOrder, saveOrder, deleteOrder
@RunWith(SpringRunner.class)
@WebMvcTest
public class OrderControllerTest {

	private static final String ANY_CUSTOMER_ID = "26";
	private static final String ANY_ORDER_ID = "3";
	private static final String MAIN_URI ="v1/customers/"+ANY_CUSTOMER_ID+"/orders";

	@MockBean
	private OrderService orderService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldGetOrdersWithCustomer() throws Exception {
		mockMvc.perform(get(MAIN_URI+"/")).andExpect(status().isOk());
		verify(orderService).getOrdersByCustomerId(ANY_CUSTOMER_ID);
	}

	@Test
	public void shouldGetOrder() throws Exception {
		mockMvc.perform(get(MAIN_URI+"/" + ANY_ORDER_ID)).andExpect(status().isOk());
		verify(orderService).getOrder(ANY_CUSTOMER_ID, ANY_ORDER_ID);
	}
}
