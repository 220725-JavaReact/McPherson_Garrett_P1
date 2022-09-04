package com.revature.rbcGames.Servlet.Menus;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.util.HtmlFormater;

public class MainMenuServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String body = "<ul style=\"text-align: left; font-size: large;\">";
		//check if customer session is saved, redirect if not
		
		/*options: 
		 * 1. check if customer is admind, display admin options redirect link
		 * 2. Option for customer to begin a purchase, go to storefront servlet
		 * 3. Option for customer to update their own options
		*/
		//if(customer is admin, show menu options
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		if(customer.isAdmin()) {
			body += "<li> <a href=\"/McPherson_Garrett_P1/Admin\">Admin Options</a> </li><br>";
		}
		//go to redirect if the user is not logged in
		body += "<li> <a href=\"/McPherson_Garrett_P1/Stores\">Start your order</a> </li><br>";
		body += "<li> <a href=\"/McPherson_Garrett_P1/History\">Review Previous Orders</a> </li><br>";
		body += "<li> <a href=\"/McPherson_Garrett_P1/Update\">Update your profile</a> </li>";
		body += "</ul>";
		resp.getWriter().write(HtmlFormater.format("Menu", customer.getUserName(), body));
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
	
	}
}
