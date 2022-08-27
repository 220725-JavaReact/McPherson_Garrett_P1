package com.revature.rbcGames.Servlet.Ordering;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.rbcGames.Service.StoreFrontService;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.HtmlFormater;

/**
 * @author Garrett
 * This servlet is where the user will select the store from which they wish to order from.
 */
public class StoreFrontServlet extends HttpServlet {
	private static StoreFrontService storeFrontService = new StoreFrontService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String body= "<form style=\"text-align: left; font-size: large;\">";
		ArrayList<StoreFront> storeFronts = storeFrontService.GetAllStoreFronts();
		for(StoreFront store : storeFronts) { //TODO change to regular for loop to get index instead of primary key id
			body+= "<input type=\"radio\" id=\"storefront\" name=\"store\" value=\""+ store.getId() +"\">\r\n"
					+ "<label for=\"a\">"+store.getName() + " " + store.getAddress()+"</label><br><br>";
			//body += "<li>" + store.getName() + " " + store.getAddress() + "</li>";
		}
		body += "<input type=\"submit\" value=\"Select Store\"></form>";
		resp.getWriter().write(HtmlFormater.head + body + HtmlFormater.tail);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
	
	}
}
