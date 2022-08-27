package com.revature.rbcGames.Servlet.Ordering;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.rbcGames.Service.LineItemService;
import com.revature.rbcGames.Service.StoreFrontService;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.HtmlFormater;

/**
 * @author Garrett
 * This servlet will list the items the customer wishes to purchase, getting the store form the storefront servlet and the
 * list of line items related to that store.
 */
public class StoreFrontOrderServlet extends HttpServlet{
	private static StoreFrontService storeFrontService = new StoreFrontService();
	private static LineItemService lineItemService = new LineItemService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String body= "<ul style=\"text-align: left; font-size: large;\">";
		//get select store from list of store
		ArrayList<StoreFront> storeFronts = storeFrontService.GetAllStoreFronts();
		ArrayList<LineItem> lineItems = lineItemService.GetAllLineItemsFromStoreFront(storeFronts.get(0));
		for(LineItem lineItem : lineItems) {
			body += "<li>" + lineItem.getProduct().getName() + " " + lineItem.getProduct().getPrice() + "</li>";
		}
		body += "</ul>";
		resp.getWriter().write(HtmlFormater.head + body + HtmlFormater.tail);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
	
	}
}
