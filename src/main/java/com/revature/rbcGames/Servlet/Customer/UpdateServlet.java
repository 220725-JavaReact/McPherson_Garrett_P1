package com.revature.rbcGames.Servlet.Customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.Service.CustomerService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.util.HtmlFormater;

public class UpdateServlet extends HttpServlet {
	private static CustomerService customerService = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		System.out.println(customer.toString());
		//exit is customer is null
		String body = "<a href=\"/McPherson_Garrett_P1/Menu\">Main Menu</a></li><br>" +
				"<p> Leave blank if data is to be left the same </p>"
				+ "         <form method=\"post\" action = \"/McPherson_Garrett_P1/Update\"> \r\n"
				+ "            <p>Name</p>\r\n"
				+ "            <input type=\"text\" name=\"name\">\r\n"
				+ "            <p>Address</p>\r\n"
				+ "            <input type=\"text\" name=\"address\">\r\n"
				+ "            <p>Email</p>\r\n"
				+ "            <input type=\"email\" name=\"email\">\r\n"
				+ "            <p>Old Password</p>\r\n"
				+ "            <input type=\"password\" name=\"old-password\">\r\n"
				+ "            <br>\r\n"
				+ "            <p>New Password</p>\r\n"
				+ "            <input type=\"password\" name=\"new-password\">\r\n"
				+ "            <br>\r\n"
				+ "            <p>New Password (repeat)</p>\r\n"
				+ "            <input type=\"password\" name=\"new-password2\">\r\n"
				+ "            <br>\r\n"
				+ "            <input type=\"submit\" value=\"Update\">\r\n"
				+ "        </form>";
		resp.getWriter().write(HtmlFormater.format("Fulfill",customer.getUserName(),body ));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		//exit is customer is null
		Customer uCustomer = new Customer();
		uCustomer.setName(req.getParameter("name"));
		uCustomer.setAddress(req.getParameter("address"));
		uCustomer.setEmail(req.getParameter("email"));
		String oPassword = req.getParameter("old-password");
		String nPassword = req.getParameter("new-password");
		String n2Password = req.getParameter("new-password2");
		if(!uCustomer.getName().equals("")){
			customer.setName(uCustomer.getName());
		}
		
		if(!uCustomer.getAddress().equals("")){
			customer.setAddress(uCustomer.getAddress());
		}
		
		if(!uCustomer.getEmail().equals("")){
			customer.setEmail(uCustomer.getEmail());
		}
		String pass = "";
		if(!oPassword.equals("")){
			if(nPassword.equals(n2Password) && !nPassword.equals("")) {
				//update with password
				pass = nPassword;
			}
		} 
		//update without password
		customer = customerService.UpdateCustomer(customer, pass);
		if(customer != null) {
			System.out.println(customer.toString());
			session.setAttribute("the-user", customer);
			resp.sendRedirect("/McPherson_Garrett_P1/Menu");
			
		}
		
		
	}
}
