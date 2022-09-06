package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.util.ConnectionFactory;

public class ProductDAO implements DAO<Product> {
	private static Logger logLogger = LogManager.getLogger(ProductDAO.class.getName());
	@Override
	public Product AddInstance(Product newInstance) {
		Product product = null;
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into products (name, price, description)\r\n"
					+ "values (?, ?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, newInstance.getName());
			pstmt.setDouble(2, newInstance.getPrice());
			pstmt.setString(3, newInstance.getDescription());
			pstmt.execute();
			product = newInstance;
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method AddInstance \n" + e.getStackTrace());
			e.printStackTrace();
		}
		
		logLogger.warn("Method not implemented at AddInstance");
		return product;
	}

	@Override
	public ArrayList<Product> GetAllInstances() {
		ArrayList<Product> products = new ArrayList<>();
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from products p "
					+ "order by id asc";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				products.add(product);
				
			}
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method GetAllInstances \n" + e.getStackTrace());
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product UpdateInstance(Product instance) {
		logLogger.warn("Method not implemented at UpdateInstance");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean RemoveInstance(Product instance) {
		logLogger.warn("Method not implemented at RemoveInstance");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Product> AddInstances(ArrayList<Product> newInstances) {
		logLogger.warn("Method not implemented at AddInstances (Array)");
		// TODO Auto-generated method stub
		return null;
	}

}
