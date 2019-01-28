package com.onlinestore.orderservice.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String orderId;

	private String customerId;

	private Date orderDate;

	private String orderStatus;

	private BigDecimal orderTotal;

	@Enumerated(EnumType.STRING)
	private OrderState orderState;

	@Embedded
	private ShoppingPositions shoppingPositions;

	public Order() {
	}

	public Order(String orderId, String customerId, Date orderDate, String orderStatus, BigDecimal orderTotal, OrderState orderState, ShoppingPositions shoppingPositions) {
		this.orderId = orderId;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
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
