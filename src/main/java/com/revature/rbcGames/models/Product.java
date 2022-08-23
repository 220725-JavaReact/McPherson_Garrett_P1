package com.revature.rbcGames.models;

import java.util.Objects;

/**
 * @author Garrett
 *
 */
public class Product {
	private int id;
	private String name;
	private double price;
	private String description;
	//enum for categories?
	

	
	public Product(String name, double price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}


	public Product() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}
	
	public String getPriceString() {
		return "$"+ String.format("%.2f", this.price);
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", description=" + description + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, name, price);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}
	
	
}
