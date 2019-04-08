package com.onlinestore.orderservice.domain;

import java.util.Objects;

/**
 * Customer class for retrieving and storing customer information from customer service.
 */
public class Customer {
	/**
	 * The unique id of the customer.
	 */
	private String customerId;
	/**
	 * Name of the customer.
	 */
	private String customerName;
	/**
	 * Address of the customer.
	 */
	private String customerAddress;

	/**
	 * Constructs new empty {@code Customer} instance.
	 */
	public Customer() {
	}

	/**
	 * Constructs new {@code Customer} instance.
	 *
	 * @param customerId      unique id of the customer
	 * @param customerName    name of the customer
	 * @param customerAddress address of the customer
	 */

	public Customer(String customerId, String customerName, String customerAddress) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return Objects.equals(customerId, customer.customerId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}
}
