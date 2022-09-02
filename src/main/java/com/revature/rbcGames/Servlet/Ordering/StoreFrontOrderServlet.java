package com.revature.rbcGames.Servlet.Ordering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.revature.rbcGames.Service.LineItemService;
import com.revature.rbcGames.Service.StoreFrontService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.PurchasedItem;
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
		HttpSession session = req.getSession();
		StoreFront storeFront = (StoreFront)session.getAttribute("your-store");
		String body= "<form method=\"post\" action = \"/McPherson_Garrett_P1/Order\" style=\"text-align: left; font-size: large;\">";
		//get select store from list of store
		//ArrayList<StoreFront> storeFronts = storeFrontService.GetAllStoreFronts();
		ArrayList<LineItem> lineItems = lineItemService.GetAllLineItemsFromStoreFront(storeFront);
		body += "<input type=\"submit\" value=\"To Cart\"><br><br>";
		for(LineItem lineItem : lineItems) {
			body += "<input type=\"number\" class=\"quantity\" name=" + "item-" + lineItem.getId()  +" min=\"0\" max=\""+ lineItem.getQuantity() +"\" placeholder=\"0\">";
			body += lineItem.getProduct().getName() + " " + lineItem.getProduct().getPriceString() + "</label><br><br>";
		}
		body += "</form>";
		resp.getWriter().write(HtmlFormater.format("Store", body));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		//shopping cart
		HttpSession session = req.getSession();
		StoreFront storeFront = (StoreFront)session.getAttribute("your-store");
		Customer customer = (Customer)session.getAttribute("the-user");
		System.out.println(customer.toString());
		ArrayList<LineItem> lineItems = lineItemService.GetAllLineItemsFromStoreFront(storeFront);
		Order order = new Order();
		order.setCustomer(customer);
		order.setStoreFront(storeFront);
		LinkedList<PurchasedItem> purchases = new LinkedList<>();
		for(LineItem lineItem : lineItems) {
			
			int quantity;
			try{
				quantity = Integer.valueOf(req.getParameter("item-" + lineItem.getId()));
			} catch (NumberFormatException e) {
				quantity = 0;
			}
			if(quantity > 0) {
				PurchasedItem purchaseItem = new PurchasedItem();
				purchaseItem.setOrder(order);
				purchaseItem.setProduct(lineItem.getProduct());
				purchaseItem.setQuanity(quantity);
				purchaseItem.setItemCost(lineItem.getProduct().getPrice());
				purchases.add(purchaseItem);
				order.addTotal(purchaseItem.getItemCostTotal());
				System.out.println(purchaseItem.toString());
			}
		}
		String body = "<ul style=\"text-align: left; font-size: large;\">";
		for(PurchasedItem item : purchases) {
			body+= "<li>" + item.getQuanity()+ "x " + item.getItemCostTotalString() + " " + item.getProduct().getName()
					+" " + item.getProduct().getDescription()+ "</li>";
		}
		body+= "</ul>";
		body+="    <form method=\"post\" action = \"/McPherson_Garrett_P1/Checkout\">\r\n"
				+ "        <input type=\"submit\" value=\"Confirm Purchase\">\r\n"
				+ "    </form>";
		
		session.setAttribute("your-order", purchases);
		
		resp.getWriter().write(HtmlFormater.format("cart", body));
	}
}
