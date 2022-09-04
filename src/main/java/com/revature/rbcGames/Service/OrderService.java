package com.revature.rbcGames.Service;

import java.util.ArrayList;
import java.util.LinkedList;

import com.revature.rbcGames.DAO.DAO;
import com.revature.rbcGames.DAO.OrderDAO;
import com.revature.rbcGames.DAO.PurchasedItemDAO;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.PurchasedItem;

public class OrderService {
	private static DAO<Order> orderDAO = new OrderDAO();
	private static DAO<PurchasedItem> purchasedItemDAO = new PurchasedItemDAO();
	private static ArrayList<Order> orders = null;
	
	
	
	public OrderService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderService(OrderDAO orderDAO, PurchasedItemDAO purchasedItemDAO, ArrayList<Order> orders) {
		OrderService.orderDAO = orderDAO;
		OrderService.purchasedItemDAO = purchasedItemDAO;
		OrderService.orders = orders;
	}

	public LinkedList<PurchasedItem> AddAnOrder(LinkedList<PurchasedItem> purchasedItems){
		Order order = purchasedItems.get(0).getOrder();
		order = orderDAO.AddInstance(order);
		System.out.println(order.toString());
		purchasedItems.get(0).getOrder().setId(order.getId());
		purchasedItems = ((PurchasedItemDAO) purchasedItemDAO).AddInstances(purchasedItems, order);
		System.out.println(purchasedItems.toString());
		return purchasedItems;
	}
	
	public ArrayList<Order> GetAllOrders(){
		
		if(orders == null) {
			orders = orderDAO.GetAllInstances();
		}
		return orders;
	}
	
	public ArrayList<Order> GetAllUnFulfilledOrders(){
		ArrayList<Order> o = new ArrayList<>();
		ArrayList<Order> c = GetAllOrders();
		
		for(Order order : c) {
			if(!order.getReady()) {
				o.add(order);
			}
		}
		return o;
	}
	
	public ArrayList<Order> GetAllOrdersCustomer(Customer customer){
		ArrayList<Order> o = new ArrayList<>();
		ArrayList<Order> c = GetAllOrders();
		for(Order order : c) {
			if(order.getCustomer().getId()== customer.getId()) {
				o.add(order);
			}
		}
		return o;
	}
	public ArrayList<PurchasedItem> GetAllPurchasedItemsOrder(Order order){
		
		return ((PurchasedItemDAO) purchasedItemDAO).GetInstanceByOrderId(order);
		
	}
	
	public ArrayList<Order> UpdateOrders(ArrayList<Order> orders){
		return ((OrderDAO) orderDAO).UpdateInstances(orders);
	}
}
