package com.revature.rbcGames.Service;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.DAO.DAO;
import com.revature.rbcGames.DAO.StoreFrontDAO;
import com.revature.rbcGames.models.StoreFront;

public class StoreFrontService {
	private static DAO<StoreFront> storeFrontDAO = new StoreFrontDAO();
	private static ArrayList<StoreFront> storeFronts = null;
	private static StoreFront cachedStore = null;
	private static Logger logLogger = LogManager.getLogger(StoreFrontService.class.getName());
	
	
	public StoreFrontService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StoreFrontService(StoreFrontDAO storeFrontDAO, ArrayList<StoreFront> storeFronts, StoreFront cachedStore) {
		super();
		StoreFrontService.storeFrontDAO = storeFrontDAO;
		StoreFrontService.storeFronts = storeFronts;
		StoreFrontService.cachedStore = cachedStore;

	}

	public StoreFront AddStoreFront(StoreFront StoreFront) {
		logLogger.info("Adding the store " + StoreFront.toString() + " to the db");
		StoreFront s = storeFrontDAO.AddInstance(StoreFront);
		if(storeFronts != null) {
			storeFronts = null;
		}
		return s;
	}
	
	public ArrayList<StoreFront> GetAllStoreFronts(){
		
		if(storeFronts == null) {

			logLogger.info("Getting storeFronts from the db");
			storeFronts = storeFrontDAO.GetAllInstances();
		} else {
			logLogger.info("Getting storeFronts from the cache");

		}
		
		return storeFronts;
	}
	
	public StoreFront GetAStoreFront(String sId) {
		logLogger.info("Getting a storeFront based on its id");
		int id = Integer.parseInt(sId);
		
		if(cachedStore == null || cachedStore.getId() != id) {
			for(StoreFront storeFront : GetAllStoreFronts()) {
				if(storeFront.getId() == id) {
					cachedStore = storeFront;
					return cachedStore;
				}
			}
		}
		return cachedStore;
	}
}
