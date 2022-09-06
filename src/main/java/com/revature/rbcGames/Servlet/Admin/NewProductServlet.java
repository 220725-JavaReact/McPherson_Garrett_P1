package com.revature.rbcGames.Servlet.Admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.Service.ProductService;
import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.util.HtmlFormater;

public class NewProductServlet extends HttpServlet {
	private static ProductService productService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		if(!customer.isAdmin()) {
			resp.sendRedirect("/McPherson_Garrett_P1/Redirect");
		}
		
		String body= "<a href=\"/McPherson_Garrett_P1/Admin\">Admin Menu</a><br>"
				+ "<form method=\"post\" action = \"/McPherson_Garrett_P1/ProductAdmin\">"
				+ "            <p>Product name</p>\r\n"
				+ "            <input type=\"text\" name=\"Pname\">"
				+ "            <p>Product Price</p>\r\n"
				+ "            <input type=\"number\" name=\"Pprice\" min =\"1\">"
				+ "            <p>Product Description</p>\r\n"
				+ "            <input type=\"text\" name=\"Pdesciption\">"
				+ "            <br><input type=\"submit\" value=\"Add to Database\">"
				+ "</form>";
		
		resp.getWriter().write(HtmlFormater.format("Products", customer.getUserName(), body));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Customer customer = (Customer)session.getAttribute("the-user");
		if(!customer.isAdmin()) {
			resp.sendRedirect("/McPherson_Garrett_P1/Redirect");
		}
		Product product = new Product();
		product.setName(req.getParameter("Pname"));
		product.setPrice(Integer.valueOf(req.getParameter("Pprice")));
		product.setDescription(req.getParameter("Pdesciption"));
		product = productService.AddProduct(product);
		String body= "<a href=\"/McPherson_Garrett_P1/Admin\">Admin Menu</a><br>";
		if(product != null) {
			body += "<h1>"+ product.getName() + " was added to the database</h1>";
		} else {
			body += "<h1>Failed to add to the database.</h1>";
		}
		resp.getWriter().write(HtmlFormater.format("Products", customer.getUserName(), body));
	}
}
