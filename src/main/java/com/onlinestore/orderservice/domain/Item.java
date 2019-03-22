package com.onlinestore.orderservice.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Item class. Specific item for sale. Part of {@link ShoppingPosition} class.
 */
@Embeddable
public class Item {
	/**
	 * Unique Id of the item.
	 */
	private String itemId;
	/**
	 * Name of the item.
	 */
	private String itemName;

	/**
	 * Constructs a new empty {@code Item} instance.
	 */
	public Item() {
	}

	/**
	 * Constructs a new {@code Item} instance.
	 *
	 * @param itemId   unique Id of the item
	 * @param itemName name of the item
	 */
	public Item(String itemId, String itemName) {
		this.itemId = itemId;
		this.itemName = itemName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return Objects.equals(itemId, item.itemId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId);
	}

	@Override
	public String toString() {
		return "Item{" +
				"itemId='" + itemId + '\'' +
				", itemName='" + itemName + '\'' +
				'}';
	}
}
