package com.revature.rbcGames.Servlet.Customer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.Service.OrderService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.PurchasedItem;
import com.revature.rbcGames.util.HtmlFormater;

public class HistoryServlet extends HttpServlet {
	private static OrderService orderService = new OrderService();
	//private static PurchaseItemService purchaseItemService = new PurchaseItemService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		ArrayList<Order> orders = orderService.GetAllOrdersCustomer(customer);
		String body = "<ul>";
		
		for(Order order : orders) {
			
			ArrayList<PurchasedItem> purchasedItems = orderService.GetAllPurchasedItemsOrder(order);
			for(PurchasedItem item: purchasedItems) {
				//finish this
			}
		}
		resp.getWriter().write(HtmlFormater.format("Register", body));
	}

}
