package com.revature.rbcGames.Servlet.Admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.Service.LineItemService;
import com.revature.rbcGames.Service.StoreFrontService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.HtmlFormater;

public class RestockServlet extends HttpServlet {
	private static StoreFrontService storeFrontService = new StoreFrontService();
	private static LineItemService lineItemService = new LineItemService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		if(!customer.isAdmin()) {
			resp.sendRedirect("/McPherson_Garrett_P1/Redirect");
		}
		String body= "<h1>Select new Stock</h1>"
				+ "<p>Input is additive, use negative number to subtract stock</p><br>"
				+ "<a href=\"/McPherson_Garrett_P1/Admin\">Admin Options</a><br>"
				+ "<form method=\"post\" action = \"/McPherson_Garrett_P1/Restock\" style=\"text-align: left; font-size: large;\">";
		ArrayList<StoreFront> storeFronts = storeFrontService.GetAllStoreFronts();
		
		for(StoreFront storeFront : storeFronts) { //TODO change to regular for loop to get index instead of primary key id
			//body+= "<input type=\"radio\" class=\"storefront\" name=\"store\" value=\""+ store.getId() +"\">\r\n"
					//+ "<label for=\"a\">"+store.getName() + " " + store.getAddress()+"</label><br><br>";
			body += "<li>"  + storeFront.getName() + " " + storeFront.getAddress() + "</li>";
			body+="<ul>";
			StoreFront storeFront1 = storeFront;
			ArrayList<LineItem> lineItems = lineItemService.GetAllLineItemsFromStoreFront(storeFront1);
			
			for(LineItem lineItem : lineItems) {
				body += "<li>" + lineItem.getQuantity() + "x " + lineItem.getProduct().getName() + " " + lineItem.getProduct().getDescription() +
						"<input type=\"number\" class=\"quantity\" name=" + "item-" + lineItem.getId() +" placeholder=\"0\">" + "</li>"; 
			}
			body+="</ul>";
		}
		body += "<input type=\"submit\" value=\"Submit Restock\"></form>";
		resp.getWriter().write(HtmlFormater.format("Store", customer.getUserName(),body ));
		 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		if(!customer.isAdmin()) {
			resp.sendRedirect("/McPherson_Garrett_P1/Redirect");
		}
		ArrayList<LineItem> lineItems = lineItemService.GetAllLineItemsAll();
		ArrayList<LineItem> updatedItems = new ArrayList<>();
		int i = 0;
		for(LineItem lineItem : lineItems) {
			
			int quantity;
			try{
				quantity = Integer.valueOf(req.getParameter("item-" + lineItem.getId()));
			} catch (NumberFormatException e) {
				quantity = 0;
			}
			if(quantity == 0) {

				continue;
			}
			
			updatedItems.add(lineItem);
			updatedItems.get(i).subtrackQuantity(-1 * quantity);
			i++;
		}
		if(lineItems.size() == 0) {
			resp.sendRedirect("/McPherson_Garrett_P1/Restock");
		}
		
		updatedItems = lineItemService.UpdateLineItems(updatedItems);
		
		resp.sendRedirect("/McPherson_Garrett_P1/Restock");
			 
	}
}
