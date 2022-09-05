package com.revature.rbcGames.Servlet.Admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.Service.LineItemService;
import com.revature.rbcGames.Service.ProductService;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.HtmlFormater;

public class LineItemAdminServlet extends HttpServlet {
	private static ProductService productService = new ProductService();
	private static LineItemService lineItemService = new LineItemService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		StoreFront storeFront = (StoreFront)session.getAttribute("your-store");
		ArrayList<Product> products = productService.GetStoreUnlistedProducts(storeFront);
		session.setAttribute("add-products", products);
		String body= "<a href=\"/McPherson_Garrett_P1/Admin\">Admin Menu</a></li><br>" 
				+"<form method=\"post\" action = \"/McPherson_Garrett_P1/ItemAdmin\" style=\"text-align: left; font-size: large;\">";


		body += "<input type=\"submit\" value=\"Add items to stock\"><br><br>";
		for(Product product : products) {
			body += "<input type=\"number\" class=\"quantity\" name=" + "item-" + product.getId()  +" min=\"0\" placeholder=\"0\">";
			body += product.getName() + " " + product.getPriceString() + " " + product.getDescription()+ "</label><br><br>";
		}
		body += "</form>";
		resp.getWriter().write(HtmlFormater.format("Item",body));
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		StoreFront storeFront = (StoreFront)session.getAttribute("your-store");
		ArrayList<Product> products = (ArrayList<Product>)session.getAttribute("add-products");
		ArrayList<LineItem> lineItems = new ArrayList<>();
		for(Product p: products) {
			int quantity;
			try{
				quantity = Integer.valueOf(req.getParameter("item-" + p.getId()));
			} catch (NumberFormatException e) {
				quantity = 0;
			}
			
			if(quantity > 0) {
				LineItem item = new LineItem();
				item.setQuantity(quantity);
				item.setStoreFront(storeFront);
				item.setProduct(p);
				lineItems.add(item);
			}
		}

		
		ArrayList<LineItem> newItems = lineItemService.AddLineItems(lineItems);
		String body = "<a href=\"/McPherson_Garrett_P1/Admin\">Admin Menu</a></li><br>";
			if(newItems == null) {
				body += "<h1>Items failed to be added</h1>";
			} else {
				body += 	"<h1>Items succesfully added</h1>";
			}
	
		
		resp.getWriter().write(HtmlFormater.format("Item",body));

	}
}
