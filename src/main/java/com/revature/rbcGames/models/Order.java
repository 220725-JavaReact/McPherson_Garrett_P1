package com.revature.rbcGames.models;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * @author Garrett
 *
 */
public class Order {
	private int id;
	private Double total = 0.0;
	private StoreFront storeFront;
	private Customer customer;
	//to-do update model with proper constructors, set get, and override methods
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int id, Double total, StoreFront storeFront, Customer customer) {
		super();
		this.id = id;
		this.total = total;
		this.storeFront = storeFront;
		this.customer = customer;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}
	public String getTotalString() {
		return "$"+ String.format("%.2f", this.total);
	}

	public void addTotal(Double total) {
		this.total += total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}

	public StoreFront getStoreFront() {
		return storeFront;
	}

	public void setStoreFront(StoreFront storeFront) {
		this.storeFront = storeFront;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", total=" + total + ", storeFront=" + storeFront + ", customer=" + customer + "]";
	}


	
	//look into Hash and equals methods
}
