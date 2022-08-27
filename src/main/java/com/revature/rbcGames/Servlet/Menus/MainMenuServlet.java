package com.revature.rbcGames.Servlet.Menus;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		body += "<li> <a href=\"/McPherson_Garrett_P1/Stores\">Start your order</a> </li><br>";
		body += "<li> <a href=\"/McPherson_Garrett_P1/Login\">Update your profile</a> </li>";
		body += "</ul>";
		resp.getWriter().write(HtmlFormater.head + body + HtmlFormater.tail);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
	
	}
}
