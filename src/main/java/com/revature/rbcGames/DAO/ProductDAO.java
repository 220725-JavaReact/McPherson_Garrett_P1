package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.util.ConnectionFactory;

public class ProductDAO implements DAO<Product> {

	@Override
	public Product AddInstance(Product newInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> GetAllInstances() {
		ArrayList<Product> products = new ArrayList<>();
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from products p ";
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
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product UpdateInstance(Product instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean RemoveInstance(Product instance) {
		// TODO Auto-generated method stub
		return false;
	}

}
