package com.revature.rbcGames.Servlet.Customer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.Service.CustomerService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.util.HtmlFormater;

public class LoginServlet extends HttpServlet {
	private static CustomerService customerService = new CustomerService();
	private String body = "        <h1>Login</h1>\r\n"
			+ "        <form method=\"post\" action = \"/McPherson_Garrett_P1/Login\"> \r\n"
			+ "            <p>Username (unique)</p>\r\n"
			+ "            <input type=\"text\" name=\"username\">\r\n"
			+ "            <p>Password</p>\r\n"
			+ "            <input type=\"password\" name=\"password\">\r\n"
			+ "            <br>\r\n"
			+ "            <input type=\"submit\" value=\"Login\">\r\n"
			+ "        </form>";
	private String invalid = "<p style=\"color: red;\">Invalid Input</p>";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		

		
		resp.getWriter().write(HtmlFormater.format("Login" , body, true));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Customer customer = new Customer();
		customer.setUserName(username);
		customer = customerService.GetCustomerByLogin(customer, password);
		if(customer != null) {
			session.setAttribute("the-user", customer);
			resp.sendRedirect("/McPherson_Garrett_P1/Menu");
			
		}
		resp.getWriter().write(HtmlFormater.format("Login" , invalid + body, true ));
	}
}
