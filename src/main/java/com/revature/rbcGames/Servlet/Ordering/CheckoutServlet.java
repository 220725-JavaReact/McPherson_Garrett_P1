package com.revature.rbcGames.Servlet.Ordering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.Service.OrderService;
import com.revature.rbcGames.models.PurchasedItem;
import com.revature.rbcGames.util.HtmlFormater;

public class CheckoutServlet extends HttpServlet {
	private static OrderService orderService = new OrderService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String body = "Thank you";
		resp.getWriter().write(HtmlFormater.format("Checkout",body ));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		//add customer check
		HttpSession session = req.getSession();
		LinkedList<PurchasedItem> purchasedItems = (LinkedList<PurchasedItem>)session.getAttribute("your-order");
		if(purchasedItems.size() == 0) {
			//display there is nothing to display and go back.
			return;
		}
		System.out.println("Items"+purchasedItems.toString());
		purchasedItems = orderService.AddAnOrder(purchasedItems);
		String body = "<h1>Thank you for your purchase</h1>\r\n"
				+ "        <p>Check back to see the status of your order</p>\r\n"
				+ "        <div>\r\n"
				+ "            <p>Ordered from: " + purchasedItems.get(0).getOrder().getStoreFront().getAddress() + " "
				+ "</p>\r\n"
				+ "            <p>For: " + purchasedItems.get(0).getOrder().getCustomer().getName() 
				+ "</p>\r\n"
				+ "            <h3>Products: </h3>\r\n";

		for(PurchasedItem item : purchasedItems) {
			body+= "<p>" + item.getQuanity()+ "x " + item.getItemCostTotalString() + " " + item.getProduct().getName()
					+" " + item.getProduct().getDescription()+ "</p>";
		}
		
		body+="            <p></p>\r\n"
				+ "            <h3>Total: " + purchasedItems.get(0).getOrder().getTotalString()
				+ "</h3>\r\n"
				+ "        </div>";
		
		resp.getWriter().write(HtmlFormater.format("Checkout",body ));
	}
}
