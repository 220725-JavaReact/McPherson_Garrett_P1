package com.revature.rbcGames.Servlet.Admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.util.HtmlFormater;

public class AdminOptionsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		String body ="<ul style=\"text-align: left; font-size: large;\">";
		if(!customer.isAdmin()) {
			return;
		}
		body+= "<li><a href=\"/McPherson_Garrett_P1/Restock\">Restock Items</a></li><br>";
		
		body +="<li><a href=\"/McPherson_Garrett_P1/Fullfillment\">Fullfill Orders</a></li><br>";
		
		resp.getWriter().write(HtmlFormater.format("Admin Menu", customer.getUserName(), body));
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		
	}
}
