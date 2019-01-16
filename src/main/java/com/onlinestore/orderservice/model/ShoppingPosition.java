package com.onlinestore.orderservice.model;

import lombok.Data;

@Data
public class ShoppingPosition {
	private String shoppingPositionId;
	private Item item;
	private int amount;

}
