package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.ConnectionFactory;

/**
 * @author Garrett
 * lime items is special compared to other dao as it get methods include the product as well
 */
public class LineItemDAO implements DAO<LineItem> {

	@Override
	public LineItem AddInstance(LineItem newInstance) { //used by admins
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public LineItem GetInstanceByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public ArrayList<LineItem> GetAllInstances() { //should only be used by admins
		// TODO Auto-generated method stub
		ArrayList<LineItem> allItems = new ArrayList<>();
		
		try (Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select li.id, products.id as pid ,products.name, products.price, li.quantity, products.description,\r\n"
					+ "storefront.id as sid, storefront.\"name\" as sname, storefront.address \r\n"
					+ "from line_items li \r\n"
					+ "inner join products on li.product=products.id \r\n"
					+ "inner join storefront on li.store=storefront.id ";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LineItem lineItem = new LineItem();
				Product product = new Product();
				StoreFront storeFront = new StoreFront();
				lineItem.setId(rs.getInt("id"));
				product.setId(rs.getInt("pid"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				lineItem.setQuantity(rs.getInt("quantity"));
				product.setDescription(rs.getString("description"));
				storeFront.setId(rs.getInt("sid"));
				storeFront.setName(rs.getString("sname"));
				storeFront.setAddress(rs.getString("address"));
				
				lineItem.setProduct(product);
				lineItem.setStoreFront(storeFront);
				
				allItems.add(lineItem);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allItems;
	}
	
	public ArrayList<LineItem> GetAllInstancesFromStoreFront(int storeId) {
		
		
		try (Connection con = ConnectionFactory.getInstance().getConnection()) {
			ArrayList<LineItem> allItems = new ArrayList<>();
			String query= "select li.id, products.id as pid ,products.name, products.price, li.quantity, products.description  \r\n"
					+ "from line_items li \r\n"
					+ "inner join products on li.product=products.id \r\n"
					+ "where li.store = ?;";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, storeId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LineItem line = new LineItem();
				Product item = new Product();
				line.setId(rs.getInt("id"));
				item.setId(rs.getInt("pid"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				line.setQuantity(rs.getInt("quantity"));
				item.setDescription(rs.getString("description"));
				
				line.setProduct(item);
				
				allItems.add(line);			
			}
			
			return allItems;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LineItem UpdateInstance(LineItem instance) {
		// TODO Auto-generated method stub
		try (Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "update line_items\r\n"
					+ "set quantity = ?\r\n"
					+ "where id=?";
			PreparedStatement pstmt = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*public ArrayList<LineItem> UpdateExistingInstances(ArrayList<LineItem> instances){
		
		try (Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "";
			for(LineItem instance : instances) {
				query += "update line_items\r\n"
						+ "set quantity = ? where id=?;\n";
			}
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			for(int i = 0; i < instances.size(); i++) {
				
				pstmt.setInt(i*2+1, instances.get(i).getQuantity());
				pstmt.setInt(i*2+2, instances.get(i).getId());
			}
			
			pstmt.execute();
			return instances;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}*/

	@Override
	public boolean RemoveInstance(LineItem instance) {
		// TODO Auto-generated method stub
		return false;
	}

}
