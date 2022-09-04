package com.revature.rbcGames.models;

import java.util.ArrayList;
import java.util.Objects;
;

/**
 * @author Garrett
 * All information regarding a customer
 */
public class Customer {
	private int id;
	private String name;
	private String address;
	private String email;
	private String userName;
	private int password;
	private boolean isAdmin = false;
	
	
	
	
	public Customer() {
		super();
		this.name = "";
		this.address = "";
		this.email = "";
		//Logger.log(LogLevel.verbose , "Created: " + this.toString());
	}
	
	


	public Customer(int id, String name, String address, String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
	}

	public Customer(int id, String name, String address, String email, Boolean isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.isAdmin = isAdmin;
	}



	public Customer(int id, String name, String address, String emailPhone, String userName,
			int password, boolean isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = emailPhone;
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getPassword() {
		return password;
	}


	public void setPassword(int password) {
		this.password = password;
	}


	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}




	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", userName="
				+ userName + ", password=" + password + ", isAdmin=" + isAdmin + "]";
	}




	@Override
	public int hashCode() {
		return Objects.hash(address, email, id, isAdmin, name, password, userName);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email) && id == other.id
				&& isAdmin == other.isAdmin && Objects.equals(name, other.name) && password == other.password
				&& Objects.equals(userName, other.userName);
	}
	
	



	
	
}
