package com.revature.rbcGames.Service;

import java.util.ArrayList;

import com.revature.rbcGames.DAO.DAO;
import com.revature.rbcGames.DAO.LineItemDAO;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.StoreFront;

public class LineItemService {
	private static DAO<LineItem> lineItemDAO = new LineItemDAO();
	private static ArrayList<LineItem> lineItems = null;
	//add line Item
	
	public ArrayList<LineItem> GetAllLineItemsFromStoreFront(StoreFront storeFront){
		ArrayList<LineItem> l = new ArrayList<>();
		
		if(lineItems == null) {
			lineItems = lineItemDAO.GetAllInstances();
		}
		
		for(LineItem lineItem : lineItems) {
			if(lineItem.getStoreFront().getId() == storeFront.getId() && lineItem.getQuantity() > 0) {
				l.add(lineItem);
			}
		}
		
		return l;
	}
	
	public ArrayList<LineItem> GetAllLineItemsAll(){
		if(lineItems == null) {
			lineItems = lineItemDAO.GetAllInstances();
		}
		return lineItems;
	}
	
	public ArrayList<LineItem> UpdateLineItems(ArrayList<LineItem> lineItems){
		System.out.println("Updating lineItems " + lineItems.toString());
		ArrayList<LineItem> items = ((LineItemDAO) lineItemDAO).UpdateExistingInstances(lineItems);
		lineItems = null;
		
		return GetAllLineItemsAll();
	}

}
