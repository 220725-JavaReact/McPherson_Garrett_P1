package com.revature.rbcGames.Service;

import org.apache.logging.log4j.*;
import java.util.ArrayList;

import com.revature.rbcGames.DAO.CustomerDAO;
import com.revature.rbcGames.DAO.DAO;
import com.revature.rbcGames.models.Customer;

public class CustomerService {
	private static DAO<Customer> customerDAO = new CustomerDAO();
	private static ArrayList<Customer> customers = null;
	private static Logger logLogger = LogManager.getLogger(CustomerService.class.getName());
	
	
	public CustomerService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CustomerService(DAO<Customer> customerDAO, ArrayList<Customer> customers) {
		CustomerService.customerDAO = customerDAO;
		CustomerService.customers = customers;
	}

	private static int setToHash(String password) {
		return password.hashCode();
	}
	
	public Customer AddCustomer(Customer customer, String password) {
		logLogger.info(customer.getUserName() + " being added to the db");
		customer.setPassword(setToHash(password));
		Customer c = customerDAO.AddInstance(customer);
		if(customers != null) {
			customers = null;
			customers = customerDAO.GetAllInstances();
		}
		return c;
	}
	
	public ArrayList<Customer> GetAllCustomers(){
		logLogger.info("Retriving Customers");
		if(customers == null) {
			logLogger.info("Retriving Customers from Database");
			customers = customerDAO.GetAllInstances();
		}
		
		return customers;
	}
	
	public Customer GetCustomerByLogin(Customer customer, String password) {
		logLogger.info(customer.getUserName() + " is trying to log in");
		customer.setPassword(setToHash(password));
		ArrayList<Customer> customers = GetAllCustomers();

		for(Customer c : customers) {
			if(c.getUserName().equals(customer.getUserName())) {
				
				if(c.getPassword() == customer.getPassword()) {
					logLogger.info(customer.getUserName()+" name match, and password match");
					
					return c;
				}

				logLogger.error(customer.getUserName()+" name match, but password missmatch");
						
				break;
			}
		}
		return null;
	}
	
	public Customer UpdateCustomer(Customer customer, String password) {
		logLogger.info(customer.getUserName()+" is updating their personal info");
		if(!password.equals("")) {
			customer.setPassword(setToHash(password));
		}
		
		return customerDAO.UpdateInstance(customer);
	}
	

}
