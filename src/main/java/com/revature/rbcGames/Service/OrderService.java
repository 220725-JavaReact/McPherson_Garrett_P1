package com.revature.rbcGames.Service;

import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static Logger logLogger = LogManager.getLogger(OrderService.class.getName());
	
	
	
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
		logLogger.info("adding these purchased items to the order " + order.getId() + " " + order.getCustomer().getUserName() + " items are " + purchasedItems.toString());
		purchasedItems.get(0).getOrder().setId(order.getId());
		purchasedItems = ((PurchasedItemDAO) purchasedItemDAO).AddInstances(purchasedItems, order);

		return purchasedItems;
	}
	
	public ArrayList<Order> GetAllOrders(){
		
		if(orders == null) {
			logLogger.info("Getting all order from the db");
			orders = orderDAO.GetAllInstances();
		} else {
			logLogger.info("Getting all order from the cache");
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
		logLogger.info("Getting all orders from the customer "+ customer.getUserName());
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
		logLogger.info("Getting all item from the order" + order.getId() + " " + order.getCustomer().getUserName());
		return ((PurchasedItemDAO) purchasedItemDAO).GetInstanceByOrderId(order);
		
	}
	
	public ArrayList<Order> UpdateOrders(ArrayList<Order> orders){
		logLogger.info("Updating orders");
		return ((OrderDAO) orderDAO).UpdateInstances(orders);
	}
	
}
