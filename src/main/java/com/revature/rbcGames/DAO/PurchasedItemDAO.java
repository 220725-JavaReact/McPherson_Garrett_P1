package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.models.PurchasedItem;
import com.revature.rbcGames.util.ConnectionFactory;

public class PurchasedItemDAO implements DAO<PurchasedItem> {

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
			e.printStackTrace();
		}
		return null;
	}

	/*@Override
	public PurchasedItems GetInstanceByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
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
			e.printStackTrace();
		}
		return purchases;
		
		
	}

	@Override
	public ArrayList<PurchasedItem> GetAllInstances() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchasedItem UpdateInstance(PurchasedItem instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean RemoveInstance(PurchasedItem instance) {
		// TODO Auto-generated method stub
		return false;
	}

}
