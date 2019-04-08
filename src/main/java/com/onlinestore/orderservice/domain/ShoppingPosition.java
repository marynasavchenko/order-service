package com.onlinestore.orderservice.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.math.BigDecimal;

/**
 * ShoppingPosition class defines {@code Item} with its quantity and price. Part of the {@link ShoppingPositions} class.
 */
@Embeddable
public class ShoppingPosition {
	/**
	 * Unique Id of the ShoppingPosition.
	 */
	private String shoppingPositionId;
	/**
	 * Specific {@code Item}.
	 */
	@Embedded
	private Item item;
	/**
	 * Quantity of the {@code Item}.
	 */
	private int quantity;
	/**
	 * Price of the {@code Item}.
	 */
	private BigDecimal price;

	/**
	 * Constructs new empty {@code ShoppingPosition} instance.
	 */
	public ShoppingPosition() {
	}

	/**
	 * Constructs new {@code ShoppingPosition} instance.
	 *
	 * @param shoppingPositionId unique Id of the ShoppingPosition
	 * @param item               specific {@code Item}
	 * @param quantity           quantity of the {@code Item}
	 * @param price              price of the {@code Item}
	 */
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
