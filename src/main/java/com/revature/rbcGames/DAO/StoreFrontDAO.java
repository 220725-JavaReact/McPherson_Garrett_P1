package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.ConnectionFactory;

/**
 * @author Garrett
 * get instances for StoreFronts and adds them to the temp storage arraylist. NOT TESTED
 */
public class StoreFrontDAO implements DAO<StoreFront> {
	private static Logger logLogger = LogManager.getLogger(StoreFrontDAO.class.getName());
	@Override
	public StoreFront AddInstance(StoreFront newInstance) {
		logLogger.warn("Method not implemented at method Add instance");
		//TODO implement
		return newInstance;
	}
	


	@Override
	public ArrayList<StoreFront> GetAllInstances() {
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			ArrayList<StoreFront> storeFronts = new ArrayList<>();
			
			String query = "select id, name, address from storefront s "; //update to get other foreign key values with a join statement
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StoreFront store = new StoreFront();
				store.setId(rs.getInt("id"));
				store.setName(rs.getString("name"));
				store.setAddress(rs.getString("address"));
				
				storeFronts.add(store);
				
			}
			return storeFronts;
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method GetAllInstances \n" + e.getStackTrace());
			e.printStackTrace();
			
		}
		return null;
		
	}

	@Override
	public StoreFront UpdateInstance(StoreFront instance) {
		logLogger.warn("Method not implemented at method UpdateInstance");
		return null;
	}

	@Override
	public boolean RemoveInstance(StoreFront instance) {
		// TODO Auto-generated method stub
		logLogger.warn("Method not implemented at RemovedInstance");
		return false;
	}

	@Override
	public ArrayList<StoreFront> AddInstances(ArrayList<StoreFront> newInstances) {
		// TODO Auto-generated method stub
		logLogger.warn("Method not implemented at method AddInstances (array)");
		return null;
	}



}
