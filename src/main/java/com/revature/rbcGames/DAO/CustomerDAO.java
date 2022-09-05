package com.revature.rbcGames.DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.util.ConnectionFactory;


public class CustomerDAO implements DAO<Customer>{
	private static Logger logLogger = LogManager.getLogger(CustomerDAO.class.getName());
	@Override
	public Customer AddInstance(Customer newInstance) {
		// TODO Auto-generated method stub
		Customer customer = null;
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into customers (name, address, email, username, \"password\") " + 
		"values (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, newInstance.getName());
			pstmt.setString(2, newInstance.getAddress());
			pstmt.setString(3, newInstance.getEmail());
			pstmt.setString(4, newInstance.getUserName());
			pstmt.setInt(5, newInstance.getPassword());
			pstmt.execute();
			customer = newInstance;
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method AddInstance: \n" + e.getStackTrace());
			e.printStackTrace();
			
			return customer;
		}
		return customer;
	}

	@Override
	public ArrayList<Customer> GetAllInstances() {
		ArrayList<Customer> customers = new ArrayList<>();
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from customers\r\n";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				customer.setUserName(rs.getString("username"));
				customer.setPassword(rs.getInt("password"));
				customer.setAdmin(rs.getBoolean("is_admin"));
				
				customers.add(customer);
			}
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method GetAllInstances \n" + e.getStackTrace());
			e.printStackTrace();
		}
		return customers;

	}

	@Override
	public Customer UpdateInstance(Customer instance) {
		Customer customer = null;;
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "update customers \r\n"
					+ "set \"name\" = ?, address = ?, email = ?, password = ?, is_admin = ?\r\n"
					+ "where id=?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, instance.getName());
			pstmt.setString(2, instance.getAddress());
			pstmt.setString(3, instance.getEmail());
			pstmt.setInt(4, instance.getPassword());
			pstmt.setBoolean(5, instance.isAdmin());
			pstmt.setInt(6, instance.getId());
			
			pstmt.execute();
			customer = instance;
			
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method UpdateInstance: \n" + e.getStackTrace());
			e.printStackTrace();
		}
		
		return customer;
	}

	@Override
	public boolean RemoveInstance(Customer instance) {
		logLogger.warn("Method not implemented at RemovedInstance");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Customer> AddInstances(ArrayList<Customer> newInstances) {
		logLogger.warn("Method not implemented at method AddInstances (array)");
		// TODO Auto-generated method stub
		return null;
	}



}
