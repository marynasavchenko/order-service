package com.onlinestore.orderservice.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * An order entity class.
 */
@Entity
@Table(name = "orders")
public class Order {
	/**
	 * The unique id of the order.
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String orderId;
	/**
	 * The unique id of the customer.
	 */
	private String customerId;
	/**
	 * Name of the customer.
	 */
	@Transient
	private String customerName;
	/**
	 * Address of the customer.
	 */
	@Transient
	private String customerAddress;
	/**
	 * Date of the order placement.
	 */
	private LocalDate orderDate;
	/**
	 * Status of the order. Used for additional information about the order.
	 */
	private String orderStatus;
	/**
	 * Total sum of the order.
	 */
	private BigDecimal orderTotal;
	/**
	 * {@link OrderState}
	 */
	@Enumerated(EnumType.STRING)
	private OrderState orderState;
	/**
	 * Object that includes list of shopping positions.
	 */
	@Embedded
	private ShoppingPositions shoppingPositions;

	/**
	 * Constructs new empty {@code Order} instance.
	 */
	public Order() {
	}

	public Order(String customerId, LocalDate orderDate, String orderStatus, BigDecimal orderTotal, OrderState orderState, ShoppingPositions shoppingPositions) {
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderTotal = orderTotal;
		this.orderState = orderState;
		this.shoppingPositions = shoppingPositions;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public ShoppingPositions getShoppingPositions() {
		return shoppingPositions;
	}

	public void setShoppingPositions(ShoppingPositions shoppingPositions) {
		this.shoppingPositions = shoppingPositions;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public Order withCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}

	public Order withCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
		return this;
	}

	public Order withOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return Objects.equals(orderId, order.orderId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}

	@Override
	public String toString() {
		return "Order{" +
				"orderId='" + orderId + '\'' +
				", customerId='" + customerId + '\'' +
				", orderDate=" + orderDate +
				", orderStatus='" + orderStatus + '\'' +
				", orderTotal=" + orderTotal +
				", orderState=" + orderState +
				", shoppingPositions=" + shoppingPositions +
				'}';
	}
}
