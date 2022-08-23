package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.models.PurchasedItems;
import com.revature.rbcGames.util.ConnectionFactory;

public class PurchasedItemsDAO implements DAO<PurchasedItems> {

	@Override
	public PurchasedItems AddInstance(PurchasedItems newInstance) {
		// TODO Auto-generated method stub
		PurchasedItems purchasedItem = null;
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into purchased_items (purchase, product, quantity)\r\n"
					+ "values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, newInstance.getOrder().getId());
			pstmt.setInt(2, newInstance.getProduct().getId());
			pstmt.setInt(3, newInstance.getQuanity());
			pstmt.execute();
			
			purchasedItem = newInstance;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return purchasedItem;
	}
	
	public LinkedList<PurchasedItems> AddInstances(LinkedList<PurchasedItems> newInstances, Order orderInstance){
		LinkedList<PurchasedItems> purchase = null;
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into purchased_items (purchase, product, quantity)\r\n";
			
			for(int i = 0; i < newInstances.size(); i++) {
				query += "values(?,?,?)";
				if(i != newInstances.size()-1) {
					query+= ",\n";
				}
			}
			System.out.println(query);
			PreparedStatement pstmt = con.prepareStatement(query);
			for(int i = 0; i < newInstances.size(); i++) {
				System.out.println(orderInstance.getId() + " " + newInstances.get(i).getProduct().getId() + " " + newInstances.get(i).getQuanity());
				pstmt.setInt(i*3 +1, orderInstance.getId());
				pstmt.setInt(i*3 + 2, newInstances.get(i).getProduct().getId());
				pstmt.setInt(i*3 + 3, newInstances.get(i).getQuanity());
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
	
	public ArrayList<PurchasedItems> GetInstanceByOrderId(Order order){
		ArrayList<PurchasedItems> purchases = new ArrayList<>();
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select products.\"name\", products.description, products.price, purchased_items.quantity \r\n"
					+ "from purchased_items\r\n"
					+ "join products on products.id = product\r\n"
					+ "where purchase = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, order.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PurchasedItems purchasedItem = new PurchasedItems();
				Product product = new Product();
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				purchasedItem.setQuanity(rs.getInt("quantity"));
				purchasedItem.setProduct(product);
				
				purchases.add(purchasedItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return purchases;
		
		
	}

	@Override
	public ArrayList<PurchasedItems> GetAllInstances() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchasedItems UpdateInstance(PurchasedItems instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean RemoveInstance(PurchasedItems instance) {
		// TODO Auto-generated method stub
		return false;
	}

}
