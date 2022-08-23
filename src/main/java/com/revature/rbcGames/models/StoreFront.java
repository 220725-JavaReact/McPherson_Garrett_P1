package com.revature.rbcGames.models;
import java.util.ArrayList;

/**
 * @author Garrett
 *
 */
public class StoreFront {
	private int id;
	private String name;
	private String address;

	
	public StoreFront() {
		super();
	}
	



	public StoreFront(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}




	@Override
	public String toString() {
		return "StoreFront [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	
}
