package com.onlinestore.orderservice.domain;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;

/**
 * ShoppingPositions class. Part of the {@link Order} class.
 */
@Embeddable
public class ShoppingPositions {
	/**
	 * List of {@code ShoppingPosition}.
	 */
	@ElementCollection
	@CollectionTable(name = "shopping_items")
	private List<ShoppingPosition> shoppingPositions;

	/**
	 * Constructs new empty {@code ShoppingPositions} instance.
	 */
	public ShoppingPositions() {
	}

	/**
	 * Constructs new {@code ShoppingPositions} instance.
	 *
	 * @param shoppingPositions list of {@code ShoppingPosition}
	 */
	public ShoppingPositions(List<ShoppingPosition> shoppingPositions) {
		this.shoppingPositions = shoppingPositions;
	}

	public List<ShoppingPosition> getShoppingPositions() {
		return shoppingPositions;
	}

	public void setShoppingPositions(List<ShoppingPosition> shoppingPositions) {
		this.shoppingPositions = shoppingPositions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ShoppingPositions that = (ShoppingPositions) o;
		return Objects.equals(shoppingPositions, that.shoppingPositions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(shoppingPositions);
	}

	@Override
	public String toString() {
		return "ShoppingPositions{" +
				"shoppingPositions=" + shoppingPositions +
				'}';
	}
}
