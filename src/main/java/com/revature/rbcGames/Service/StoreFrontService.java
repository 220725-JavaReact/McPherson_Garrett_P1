package com.revature.rbcGames.Service;

import java.util.ArrayList;

import com.revature.rbcGames.DAO.DAO;
import com.revature.rbcGames.DAO.StoreFrontDAO;
import com.revature.rbcGames.models.StoreFront;

public class StoreFrontService {
	private static DAO<StoreFront> storeFrontDAO = new StoreFrontDAO();
	private static ArrayList<StoreFront> storeFronts = null;
	
	public StoreFront AddStoreFront(StoreFront StoreFront) {
		StoreFront s = storeFrontDAO.AddInstance(StoreFront);
		if(storeFronts != null) {
			storeFronts = null;
		}
		return s;
	}
	
	public ArrayList<StoreFront> GetAllStoreFronts(){
		if(storeFronts == null) {
			System.out.println("Retrived StoreFront from the Dao");
			storeFronts = storeFrontDAO.GetAllInstances();
		} else {
			System.out.println("Retrived StoreFront from the Cache");
		}
		
		return storeFronts;
	}
}
