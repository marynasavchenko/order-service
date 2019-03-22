package com.onlinestore.orderservice.clients;

import com.onlinestore.orderservice.domain.Customer;

/**
 * {@link Client} defines an interface that supports operations for invoking customer service.
 */
public interface Client {
	Customer getCustomer(String customerId);
}
