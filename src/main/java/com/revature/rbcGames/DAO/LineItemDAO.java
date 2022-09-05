package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.ConnectionFactory;

/**
 * @author Garrett
 * lime items is special compared to other dao as it get methods include the product as well
 */
public class LineItemDAO implements DAO<LineItem> {
	private static Logger logLogger = LogManager.getLogger(LineItemDAO.class.getName());
	@Override
	public LineItem AddInstance(LineItem newInstance) {
		logLogger.warn("Method not implemented at AddInstnace");
		return null;
	}
	
	@Override
	public ArrayList<LineItem> AddInstances(ArrayList<LineItem> newInstances){
		ArrayList<LineItem> lineItems = null;
		try (Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into line_items (product, store, quantity)\r\n"
					+ "values";
			for(int i = 0; i< newInstances.size(); i++) {
				query += "(?,?,?)";
				if(i < newInstances.size()-1) {
					query += ",\r\n";
				}
			}
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			for(int i=0; i< newInstances.size(); i++) {
				pstmt.setInt(i*3+1,newInstances.get(i).getProduct().getId());
				pstmt.setInt(i*3+2, newInstances.get(i).getStoreFront().getId());
				pstmt.setInt(i*3+3, newInstances.get(i).getQuantity());
			}
			pstmt.execute();
			lineItems = newInstances;
		}
		catch (SQLException e) {
			logLogger.warn("Failed read/write database at method AddInstances (array): \n" + e.getStackTrace());
			e.printStackTrace();
		}
		return lineItems;
		
	}

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
			logLogger.warn("Failed read/write database at method GetAllInstances \n" + e.getStackTrace());
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
			logLogger.warn("Failed read/write database at method GetAllInstanceFromStoreFront: \n" + e.getStackTrace());
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
			logLogger.warn("Failed read/write database at method UpdateInstance: \n" + e.getStackTrace());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<LineItem> UpdateExistingInstances(ArrayList<LineItem> instances){
		System.out.println(instances.toString());
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
	}

	@Override
	public boolean RemoveInstance(LineItem instance) {
		logLogger.warn("Method not implemented at RemovedInstance");
		return false;
	}

}
