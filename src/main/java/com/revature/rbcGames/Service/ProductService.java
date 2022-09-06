package com.revature.rbcGames.Service;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.DAO.ProductDAO;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.*;

public class ProductService {
	private static ProductDAO productDAO = new ProductDAO();
	private static ArrayList<Product> products = null;
	private static LineItemService lineItemService = new LineItemService();
	private static Logger logLogger = LogManager.getLogger(ProductService.class.getName());

	public ProductService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductService(ProductDAO productDAO, LineItemService lineItemService, ArrayList<Product> products) {
		super();
		ProductService.productDAO = productDAO;
		ProductService.lineItemService = lineItemService;
		ProductService.products = products;
	}
	
	public Product AddProduct(Product product) {
		logLogger.info("Adding product " + product.getName() +" to the db");
		if(product.getPrice() > 0 || !(product.getName() == "") || !(product.getDescription() == "")) {
			Product newProduct = productDAO.AddInstance(product);
			return newProduct;
		} else {
			logLogger.warn("The price cannot be less than 0");
		}
		return null;
		
	}
	
	public ArrayList<Product> GetAllProducts(){
		
		if(products == null) {
			products = productDAO.GetAllInstances();
			logLogger.info("Getting all products from the database");
		} else {
			logLogger.info("Getting all products from the cache");
		}
		return products;
		
	}

	public ArrayList<Product> GetStoreUnlistedProducts(StoreFront store){
		logLogger.info("Getting all prodcted not already listed from StoreFront "+ store.toString());
		ArrayList<LineItem> storeItems = lineItemService.GetAllLineItemsFromStoreFront(store);
		ArrayList<Product> pdts = GetAllProducts();
		ArrayList<Product> unlisted = new ArrayList<>();
		Collections.sort(storeItems, new LineItemSortByProductId());

		int lastFound = 0;
		for(int i = 0; i < pdts.size(); i++) {
			Boolean add = false;
			for(int j = lastFound; j < storeItems.size(); j++) {
				if(pdts.get(i).getId() == storeItems.get(j).getProduct().getId()) {
					lastFound = j;
					add = true;
					break;
					
				} else if(storeItems.get(j).getProduct().getId() > pdts.get(i).getId()) {
					lastFound =j-1;
					if(lastFound < 0) {
						lastFound =0;
					}
					break;
				}
				

			}
			if(add) {
				unlisted.add(pdts.get(i));
			}
		}
		
		return unlisted;
		
	}
}
