package com.revature.rbcGames.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.DAO.DAO;
import com.revature.rbcGames.DAO.LineItemDAO;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.PurchasedItem;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.LineItemSortByProductId;
import com.revature.rbcGames.util.PurchasedItemSortById;

public class LineItemService {
	private static DAO<LineItem> lineItemDAO = new LineItemDAO();
	private static ArrayList<LineItem> lineItems = null;
	private static Logger logLogger = LogManager.getLogger(LineItemService.class.getName());
	
	public LineItemService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LineItemService(LineItemDAO lineItemDAO, ArrayList<LineItem> lineItems) {
		super();
		LineItemService.lineItemDAO = lineItemDAO;
		LineItemService.lineItems = lineItems;
	}
	

	public ArrayList<LineItem> AddLineItems(ArrayList<LineItem> lineItems){
		logLogger.info("Line items are being add to the db, listed as "+ lineItems.toString());
		ArrayList<LineItem> li = lineItemDAO.AddInstances(lineItems);
		lineItems = null;
		lineItems = GetAllLineItemsAll();
		return li;
	}
		

	public ArrayList<LineItem> GetAllLineItemsFromStoreFront(StoreFront storeFront){
		logLogger.info("Line items are being pulled to match store" + storeFront.toString());
		ArrayList<LineItem> l = new ArrayList<>();
		
		if(lineItems == null) {
			lineItems = GetAllLineItemsAll();
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
			logLogger.info("Getting lineItems from DAO");

			lineItems = lineItemDAO.GetAllInstances();
		} else {
			logLogger.info("Getting lineItems from cache");
			
		}
		return lineItems;
	}
	
	public ArrayList<LineItem> UpdateLineItems(ArrayList<LineItem> lineItems){
		logLogger.info("Updating line item to be " + lineItems.toString());
		ArrayList<LineItem> items = ((LineItemDAO) lineItemDAO).UpdateExistingInstances(lineItems);
		if(items == null) {
			logLogger.error("line item are null, returning null");
			return items;
		}
		lineItems = null;
		
		return GetAllLineItemsAll();
	}
	
	private ArrayList<LineItem> GetFreshLineItems(){
		logLogger.info("Clearing lineItem cache");
		lineItems = null;
		GetAllLineItemsAll();
		return lineItems;
	}
	
	public LinkedList<PurchasedItem> UpdateLineItemFromOrder(LinkedList<PurchasedItem> purchasedItems){
		logLogger.info("updating line item in respect to these purchased Items " + purchasedItems.toString());
		ArrayList<LineItem> toUpdate = new ArrayList<LineItem>();
		ArrayList<LineItem> existing = GetFreshLineItems();
		Collections.sort(existing, new LineItemSortByProductId());
		Collections.sort(purchasedItems, new PurchasedItemSortById());
		for(PurchasedItem p : purchasedItems) {
			for(LineItem li : existing) {
				if(p.getProduct().getId() == li.getProduct().getId() && p.getOrder().getStoreFront().getId() == li.getStoreFront().getId()) {
					li.setQuantity(li.getQuantity()-p.getQuanity());
					if(li.getQuantity() < 0) {
						p.setQuanity(p.getQuanity() + li.getQuantity());
						li.setQuantity(0);
					}
					toUpdate.add(li);
				}
			}
		}
		toUpdate = UpdateLineItems(toUpdate);
		if(toUpdate == null) {
			logLogger.error("updated line itmes came back null, returning null");
			return null;
		}
		return purchasedItems;
	}

}
