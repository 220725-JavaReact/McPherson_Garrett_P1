package com.revature.rbcGames.models;

public class PurchasedItems {
	private int id;
	private Order order;
	private Product product;
	private int quanity;
	
	
	
	
	public PurchasedItems() {
		super();
		this.quanity = 1;
		
	}



	public PurchasedItems(int id, Order order, Product product, int quanity) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.quanity = quanity;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	
	public void addQuanity(int quanity) {
		this.quanity+=quanity;
	}



	@Override
	public String toString() {
		return "PurchasedItems [id=" + id + ", order=" + order + ", product=" + product + ", quanity=" + quanity + "]";
	}


	
}
