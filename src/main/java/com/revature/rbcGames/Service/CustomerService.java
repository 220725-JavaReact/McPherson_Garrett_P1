package com.revature.rbcGames.Service;

import java.util.ArrayList;

import com.revature.rbcGames.DAO.CustomerDAO;
import com.revature.rbcGames.DAO.DAO;
import com.revature.rbcGames.models.Customer;

public class CustomerService {
	private static DAO<Customer> customerDAO = new CustomerDAO();
	private static ArrayList<Customer> customers = null;
	
	private static int setToHash(String password) {
		return password.hashCode();
	}
	
	public Customer AddCustomer(Customer customer, String password) {
		customer.setPassword(setToHash(password));
		Customer c = customerDAO.AddInstance(customer);
		if(customers != null) {
			customers = null;
			customers = customerDAO.GetAllInstances();
		}
		return c;
	}
	
	public ArrayList<Customer> GetAllCustomers(){
		if(customers == null) {
			customers = customerDAO.GetAllInstances();
		}
		
		return customers;
	}
	
	public Customer GetCustomerByLogin(Customer customer, String password) {
		customer.setPassword(setToHash(password));

		ArrayList<Customer> customers = GetAllCustomers();

		for(Customer c : customers) {
			if(c.getUserName().equals(c.getUserName())) {
				
				if(c.getPassword() == customer.getPassword()) {
					
					return c;
				}
				break;
			}
		}
		return null;
	}
	

}
