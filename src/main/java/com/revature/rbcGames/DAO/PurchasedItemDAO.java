package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.models.PurchasedItem;
import com.revature.rbcGames.util.ConnectionFactory;

public class PurchasedItemDAO implements DAO<PurchasedItem> {
	private static Logger logLogger = LogManager.getLogger(PurchasedItemDAO.class.getName());
	@Override
	public PurchasedItem AddInstance(PurchasedItem newInstance) {
		// TODO Auto-generated method stub
		PurchasedItem purchasedItem = null;
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into purchased_items (purchase, product, quantity)\r\n"
					+ "values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, newInstance.getOrder().getId());
			pstmt.setInt(2, newInstance.getProduct().getId());
			pstmt.setInt(3, newInstance.getQuanity());
			pstmt.setDouble(3, newInstance.getItemCostTotal());
			pstmt.execute();
			
			purchasedItem = newInstance;
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method AddInstance: \n" + e.getStackTrace());
			e.printStackTrace();
		}
		return purchasedItem;
	}
	
	public LinkedList<PurchasedItem> AddInstances(LinkedList<PurchasedItem> newInstances, Order orderInstance){
		LinkedList<PurchasedItem> purchase = null;
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into purchased_items (purchase, product, quantity, cost)\r\n"
					+ "values";
			
			for(int i = 0; i < newInstances.size(); i++) {
				query += "(?,?,?,?)";
				if(i != newInstances.size()-1) {
					query+= ",\n";
				}
			}
			System.out.println(query);
			PreparedStatement pstmt = con.prepareStatement(query);
			for(int i = 0; i < newInstances.size(); i++) {
				System.out.println(orderInstance.getId() + " " + newInstances.get(i).getProduct().getId() + " " + newInstances.get(i).getQuanity());
				pstmt.setInt(i*4 +1, orderInstance.getId());
				pstmt.setInt(i*4 + 2, newInstances.get(i).getProduct().getId());
				pstmt.setInt(i*4 + 3, newInstances.get(i).getQuanity());
				pstmt.setDouble(i*4 + 4, newInstances.get(i).getItemCost());
			}
			pstmt.execute();
			purchase = newInstances;
			return purchase;
					
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method AddInstances (array): \n" + e.getStackTrace());
			e.printStackTrace();
		}
		return null;
	}

	
	public ArrayList<PurchasedItem> GetInstanceByOrderId(Order order){
		ArrayList<PurchasedItem> purchases = new ArrayList<>();
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select products.\"name\", products.description, products.price, purchased_items.quantity, purchased_items.\"cost\"  \r\n"
					+ "from purchased_items\r\n"
					+ "join products on products.id = product\r\n"
					+ "where purchase = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, order.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PurchasedItem purchasedItem = new PurchasedItem();
				Product product = new Product();
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				purchasedItem.setQuanity(rs.getInt("quantity"));
				purchasedItem.setItemCost(rs.getDouble("cost"));
				purchasedItem.setProduct(product);
				
				purchases.add(purchasedItem);
			}
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method GetInstnaceByOrder: \n" + e.getStackTrace());
			e.printStackTrace();
		}
		return purchases;
		
		
	}

	@Override
	public ArrayList<PurchasedItem> GetAllInstances() {
		logLogger.warn("Method not implemented at GetAllInstances");
		return null;
	}

	@Override
	public PurchasedItem UpdateInstance(PurchasedItem instance) {
		logLogger.warn("Method not implemented at UpdatedInstnace");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean RemoveInstance(PurchasedItem instance) {
		logLogger.warn("Method not implemented at RemovedInstance");

		return false;
	}

	@Override
	public ArrayList<PurchasedItem> AddInstances(ArrayList<PurchasedItem> newInstances) {
		logLogger.warn("Method not implemented at method AddInstances (array)");

		return null;
	}

}
