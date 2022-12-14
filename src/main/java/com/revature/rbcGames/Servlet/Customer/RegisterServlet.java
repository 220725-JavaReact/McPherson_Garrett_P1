package com.revature.rbcGames.Servlet.Customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.Service.CustomerService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.util.HtmlFormater;

public class RegisterServlet extends HttpServlet {
	private static CustomerService customerService = new CustomerService();
	private static Logger logLogger = LogManager.getLogger(CustomerService.class.getName());
	private String body = "<h1>Register User</h1>\r\n"
			+ "        <form method=\"post\" action = \"/McPherson_Garrett_P1/Register\"> \r\n"
			+ "            <p>Name</p>\r\n"
			+ "            <input type=\"text\" name=\"name\">\r\n"
			+ "            <p>Address</p>\r\n"
			+ "            <input type=\"text\" name=\"address\">\r\n"
			+ "            <p>Email</p>\r\n"
			+ "            <input type=\"email\" name=\"email\">\r\n"
			+ "            <p>Username (unique)</p>\r\n"
			+ "            <input type=\"text\" name=\"username\">\r\n"
			+ "            <p>Password</p>\r\n"
			+ "            <input type=\"password\" name=\"password\">\r\n"
			+ "            <br>\r\n"
			+ "            <input type=\"submit\" value=\"Register\">\r\n"
			+ "        </form>";
	private String invalid = "<p style=\"color: red;\">Invalid Input</p>";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		resp.getWriter().write(HtmlFormater.format("Register", body));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		Customer customer = new Customer();
		customer.setName(req.getParameter("name"));
		customer.setAddress(req.getParameter("address"));
		customer.setEmail(req.getParameter("email"));
		customer.setUserName(req.getParameter("username"));
		String myPassword = req.getParameter("password");
		if(customer.getName() == "" || customer.getAddress()== "" || customer.getEmail() == "" 
				|| customer.getUserName() == "" || myPassword == "") {
			//redirect back
			logLogger.error("missing creditials in register");
			resp.getWriter().write(HtmlFormater.format("Register",invalid + body));
		} else {
			customerService.AddCustomer(customer, myPassword);
			resp.sendRedirect("/McPherson_Garrett_P1/Login");
		}
		
		
		
	}
}
