package com.onlinestore.orderservice.clients;

import com.onlinestore.orderservice.domain.Customer;

public interface Client {
	Customer getCustomer(String customerId);
}
