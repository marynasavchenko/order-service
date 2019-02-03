package com.onlinestore.orderservice.clients;

import com.onlinestore.orderservice.model.Customer;

public interface Client {
	Customer getCustomer(String customerId);
}
