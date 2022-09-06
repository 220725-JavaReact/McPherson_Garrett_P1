package com.revature.rbcGames.Servlet.Menus;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.util.HtmlFormater;

public class RedirectServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String body = "<h1>Your session is invalid</h1>";
		resp.getWriter().write(HtmlFormater.format("Menu", "invalid", body));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
	
	}
	
}
