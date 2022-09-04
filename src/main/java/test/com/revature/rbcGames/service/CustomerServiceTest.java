package test.com.revature.rbcGames.service;



import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revature.rbcGames.DAO.CustomerDAO;
import com.revature.rbcGames.Service.CustomerService;
import com.revature.rbcGames.models.Customer;



public class CustomerServiceTest {
	@Mock
	private CustomerDAO mockCustomerDAO = Mockito.mock(CustomerDAO.class);
	private ArrayList<Customer> mockCustomers = null;
	private CustomerService mockCustomerService = Mockito.mock(CustomerService.class);
	@InjectMocks
	private CustomerService customerService = new CustomerService(mockCustomerDAO, mockCustomers);
	
	@BeforeEach
	public void start() {
		//mockCustomerDAO = Mockito.mock(CustomerDAO.class);
		//customerService = ;
	}

	@Test
	public void GetAllCustomers_Pass() {
		String user = "user";
		int pass = 01234;
		Customer customer = new Customer();
		customer.setUserName(user);
		customer.setPassword(pass);
		ArrayList<Customer> customers = new ArrayList<>();
		customers.add(customer);
		
		when(mockCustomerDAO.GetAllInstances()).thenReturn(customers);
		
		ArrayList<Customer> actualCustomers = customerService.GetAllCustomers();
		System.out.println("actual " + actualCustomers.toString());
		System.out.println("expected " +customers.toString());
		
		Assertions.assertEquals(customers, actualCustomers);
		
	}
	
	@Test
	public void GetAllCustomers_Fail() {
		//mockCustomers = null;
		String user = "user";
		int pass = 01234;
		Customer customer = new Customer();
		customer.setUserName(user);
		customer.setPassword(pass);
		ArrayList<Customer> customers = new ArrayList<>(Arrays.asList(customer));
		ArrayList<Customer> otherCustomers = new ArrayList<>();
		
		when(mockCustomerDAO.GetAllInstances()).thenReturn(customers);
		
		ArrayList<Customer> actualCustomers = customerService.GetAllCustomers();
		//System.out.println(actualCustomers.toString());
		//System.out.println(customers.toString());
		
		Assertions.assertNotEquals(otherCustomers, actualCustomers);
		
	}
	
	@Test
	public void AddCustomer_Pass() {

		Customer customer = new Customer(1, "Json Borne", "Next Door", "On the Internet");
		
		when(mockCustomerDAO.AddInstance(customer)).thenReturn(customer);
		
		Customer actual = customerService.AddCustomer(customer, "01234");
		
		Assertions.assertEquals(customer, actual);

	}
	
	@Test
	public void GetCustomerByLogin_Pass() {
		Customer customer1 = new Customer();
		customer1.setUserName("user1");
		customer1.setPassword(01234);
		Customer customer2 = new Customer();
		customer2.setUserName("user2");
		String pass2 = "password2";
		customer2.setPassword(pass2.hashCode());
		ArrayList<Customer> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer2);
		
		when(mockCustomerDAO.GetAllInstances()).thenReturn(customers);
		Customer actual = customerService.GetCustomerByLogin(customer2, pass2);
		System.out.println("array cu " + customers.toString());
		System.out.println("a login " + actual.toString());
		System.out.println("c login "+customer2.toString());
		Assertions.assertEquals(customer2, actual);
	}
	
	@Test
	public void Update_Pass() {
		Customer customer2 = new Customer();
		customer2.setUserName("user2");
		String pass2 = "password2";
		customer2.setPassword(pass2.hashCode());
		
		when(mockCustomerDAO.UpdateInstance(customer2)).thenReturn(customer2);
		
		Customer actual = customerService.UpdateCustomer(customer2, pass2);
		
		Assertions.assertEquals(customer2, actual);
	}
}
