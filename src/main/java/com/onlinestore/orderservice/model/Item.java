package com.onlinestore.orderservice.model;

import java.util.Objects;

public class Item {
	private String itemId;
	private String itemName;

	public Item() {
	}

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
