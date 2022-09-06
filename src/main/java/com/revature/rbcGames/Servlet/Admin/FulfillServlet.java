package com.revature.rbcGames.Servlet.Admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.Service.OrderService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.PurchasedItem;
import com.revature.rbcGames.util.HtmlFormater;

public class FulfillServlet extends HttpServlet {
	private static OrderService orderService  = new OrderService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		if(!customer.isAdmin()) {
			resp.sendRedirect("/McPherson_Garrett_P1/Redirect");
		}
		ArrayList<Order> orders = orderService.GetAllUnFulfilledOrders();
		String body = "<a href=\"/McPherson_Garrett_P1/Admin\">Admin Options</a></li><br>"
				+ "<form method=\"post\" action = \"/McPherson_Garrett_P1/Fulfill\">";
		for(Order order : orders) {
			body += "        <input type=\"checkbox\" name=order-" + order.getId() +" value=\"Fulfill\">\r\n"
					+ "        <label for=\"name\" >"+order.getTotalString() + " "+ order.getStoreFront().getAddress() +" for " + order.getCustomer().getName()+"</label>"
							+ "<ul>";
			ArrayList<PurchasedItem> purchasedItems = orderService.GetAllPurchasedItemsOrder(order);
			for(PurchasedItem item : purchasedItems) {
				body+="<li>" + item.getItemCostTotalString() + " " + item.getQuanity() + "x " + item.getProduct().getName() + " " 
			+ item.getProduct().getDescription() + "</li>";
						
			}
		}
		body+="</ul><br><input type=\"submit\" value=\"Confirm fulfillment\">\r\n"
				+ "    </form>";
		resp.getWriter().write(HtmlFormater.format("Fulfill",customer.getUserName(),body ));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		if(!customer.isAdmin()) {
			resp.sendRedirect("/McPherson_Garrett_P1/Redirect");
		}
		ArrayList<Order> orders = orderService.GetAllUnFulfilledOrders();
		ArrayList<Order> updatedOrders = new ArrayList<>();
		for(Order order : orders) {
			
			String[] ready = null;
			Boolean isReady = false;
			try{
				ready = req.getParameterValues("order-" + order.getId());
				isReady = true;
			} catch (Exception e) {
				
				isReady = false;
			}
			System.out.println("ready: " +isReady);
			order.setReady(isReady);
			if(isReady) {
				updatedOrders.add(order);
			}
		}
		orderService.UpdateOrders(updatedOrders);
		resp.sendRedirect("/McPherson_Garrett_P1/Fulfill");
		
			
	}
}
