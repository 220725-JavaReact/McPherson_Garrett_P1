package com.revature.rbcGames.models;

import java.util.Comparator;
import java.util.Objects;

/**
 * @author Garrett
 *
 */
public class LineItem {
	private int id;
	private Product product;
	private int quantity;
	private StoreFront storeFront;
	
	
	public LineItem() {
		super();
	}

	public LineItem(Product product, int quantity, StoreFront storeFront) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.storeFront = storeFront;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void subtrackQuantity(int quantity) {
		this.quantity -= quantity;
	}
	
	

	public StoreFront getStoreFront() {
		return storeFront;
	}

	public void setStoreFront(StoreFront storeFront) {
		this.storeFront = storeFront;
	}



	@Override
	public String toString() {
		return "LineItem [product=" + product + ", quantity=" + quantity + ", storeFront=" + storeFront.getName() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, quantity, storeFront);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItem other = (LineItem) obj;
		return Objects.equals(product, other.product) && quantity == other.quantity && Objects.equals(storeFront, other.storeFront);
	}
	
	
}

