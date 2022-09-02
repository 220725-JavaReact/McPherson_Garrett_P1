package com.revature.rbcGames.Servlet.Admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.rbcGames.Service.OrderService;
import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.util.HtmlFormater;

public class FulfillServlet extends HttpServlet {
	private static OrderService orderService  = new OrderService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ArrayList<Order> orders = orderService.GetAllUnFulfilledOrders();
		String body = "";
		for(Order order : orders) {
			body = "<p>" + order.getTotalString() + " " + order.getCustomer().getName() + "<p>";
		}
		resp.getWriter().write(HtmlFormater.format("Fulfill",body ));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
	}
}
