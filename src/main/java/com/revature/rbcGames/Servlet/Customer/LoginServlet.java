package com.revature.rbcGames.Servlet.Customer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.rbcGames.Service.CustomerService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.util.HtmlFormater;

public class LoginServlet extends HttpServlet {
	private static CustomerService customerService = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		ArrayList<Customer> customers = customerService.GetAllCustomers();
		String list = "";
		for(Customer customer : customers) {
			list += ("<p>" + customer.getName() + "</p>");
		}
		
		resp.getWriter().write(HtmlFormater.head + "        <h1>Login</h1>\r\n"
				+ "        <form method=\"post\" action = \"/McPherson_Garrett_P1/Register\"> \r\n"
				+ "            <p>Username (unique)</p>\r\n"
				+ "            <input type=\"text\" name=\"username\">\r\n"
				+ "            <p>Password</p>\r\n"
				+ "            <input type=\"password\" name=\"password\">\r\n"
				+ "            <br>\r\n"
				+ "            <input type=\"submit\" value=\"Login\">\r\n"
				+ "        </form>" + list + HtmlFormater.tail);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
