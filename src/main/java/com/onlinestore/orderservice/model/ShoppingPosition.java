package com.onlinestore.orderservice.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.math.BigDecimal;

@Embeddable
public class ShoppingPosition {
	private String shoppingPositionId;

	@Embedded
	private Item item;
	private int quantity;
	private BigDecimal price;

	public ShoppingPosition() {
	}

	public ShoppingPosition(String shoppingPositionId, Item item, int quantity, BigDecimal price) {
		this.shoppingPositionId = shoppingPositionId;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}

	public String getShoppingPositionId() {
		return shoppingPositionId;
	}

	public void setShoppingPositionId(String shoppingPositionId) {
		this.shoppingPositionId = shoppingPositionId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ShoppingPosition{" +
				"shoppingPositionId='" + shoppingPositionId + '\'' +
				", item=" + item +
				", quantity=" + quantity +
				", price=" + price +
				'}';
	}
}
