package test.com.revature.rbcGames.service;

import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import com.revature.rbcGames.DAO.OrderDAO;
import com.revature.rbcGames.DAO.PurchasedItemDAO;
import com.revature.rbcGames.Service.OrderService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.PurchasedItem;
import com.revature.rbcGames.models.StoreFront;

public class OrderServiceTest {

	private OrderDAO mockOrderDAO = Mockito.mock(OrderDAO.class);
	private PurchasedItemDAO mockPurchasedItemDAO = Mockito.mock(PurchasedItemDAO.class);
	private ArrayList<Order> mockOrders = null;
	private OrderService orderService = new OrderService(mockOrderDAO, mockPurchasedItemDAO, mockOrders);

	@Test
	public void GetAllOrder_Pass() {
		Customer customer = new Customer();
		StoreFront storeFront = new StoreFront();
		Order order1 = new Order();
		order1.addTotal(35.0);
		order1.setId(0);
		order1.setReady(false);
		order1.setCustomer(customer);
		order1.setStoreFront(storeFront);
		ArrayList<Order> orders = new ArrayList<>(Arrays.asList(order1));
		
		when(mockOrderDAO.GetAllInstances()).thenReturn(orders);
		
		ArrayList<Order> actual = orderService.GetAllOrders();
		
		Assertions.assertEquals(orders, actual);
	}
	
	@Test
	public void AddOrder_Pass() {
		Customer customer = new Customer();
		StoreFront storeFront = new StoreFront();
		Order order1 = new Order();
		order1.addTotal(35.0);
		order1.setId(0);
		order1.setReady(false);
		order1.setCustomer(customer);
		order1.setStoreFront(storeFront);
		PurchasedItem item1 = new PurchasedItem();
		item1.setId(0);
		item1.setOrder(order1);
		LinkedList<PurchasedItem> items = new LinkedList<>(Arrays.asList(item1));
		
		when(mockOrderDAO.AddInstance(order1)).thenReturn(order1);
		when(mockPurchasedItemDAO.AddInstances(items, order1)).thenReturn(items);
		
		LinkedList<PurchasedItem> actual = orderService.AddAnOrder(items);
		
		Assertions.assertEquals(items, actual);
	}
	
	@Test
	public void GetUnfulfilled_Pass() {
		Customer customer = new Customer();
		StoreFront storeFront = new StoreFront();
		Order order1 = new Order();
		order1.addTotal(35.0);
		order1.setId(0);
		order1.setReady(false);
		order1.setCustomer(customer);
		order1.setStoreFront(storeFront);
		Order order2 = new Order();
		order2.addTotal(100.0);
		order2.setId(1);
		order2.setReady(true);
		order2.setCustomer(customer);
		order2.setStoreFront(storeFront);
		ArrayList<Order> orders1 = new ArrayList<>(Arrays.asList(order1,order2));
		ArrayList<Order> expected = new ArrayList<>(Arrays.asList(order1));
		
		when(mockOrderDAO.GetAllInstances()).thenReturn(orders1);
		
		ArrayList<Order> actual = orderService.GetAllUnFulfilledOrders();
		
		Assertions.assertEquals(expected, actual);
	}
	
	//get allOrders customer
	
	@Test
	public void GetAllOrdersCustomer_pass() {
		Customer customer1 = new Customer();
		customer1.setId(1);
		Customer customer2 = new Customer();
		customer2.setId(2);
		StoreFront storeFront = new StoreFront();
		Order order1 = new Order();
		order1.addTotal(35.0);
		order1.setId(0);
		order1.setReady(false);
		order1.setCustomer(customer1);
		order1.setStoreFront(storeFront);
		Order order2 = new Order();
		order2.addTotal(100.0);
		order2.setId(1);
		order2.setReady(true);
		order2.setCustomer(customer2);
		order2.setStoreFront(storeFront);
		
		ArrayList<Order> orders1 = new ArrayList<>(Arrays.asList(order1,order2));
		ArrayList<Order> expected = new ArrayList<>(Arrays.asList(order1));
		when(mockOrderDAO.GetAllInstances()).thenReturn(orders1);
		
		ArrayList<Order> actual = orderService.GetAllOrdersCustomer(customer1);
		
		Assertions.assertEquals(expected, actual);
	}
	
	// not going to test the last 2 because they literally just would return the mock value.
}
