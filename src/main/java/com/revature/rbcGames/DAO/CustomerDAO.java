package com.revature.rbcGames.DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.util.ConnectionFactory;


public class CustomerDAO implements DAO<Customer>{

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
			e.printStackTrace();
			
			return customer;
		}
		return customer;
	}
	/*@Override
	public Customer GetInstanceByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*public Customer GetInstanceByLogin(Customer newInstance) {
		Customer customer = null;
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from customers\r\n"
					+ "where username = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, newInstance.getUserName());
			pstmt.setInt(2, newInstance.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String address = rs.getString(3);
			String email = rs.getString(4);
			Boolean admin = rs.getBoolean(7);
			customer = new Customer(id, name, address, email, admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}*/
	@Override
	public ArrayList<Customer> GetAllInstances() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Customer UpdateInstance(Customer instance) {
		Customer customer = null;;
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "update customers \r\n"
					+ "set \"name\" = ?, address = ?, email = ?\r\n"
					+ "where id=1;";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, instance.getName());
			pstmt.setString(2, instance.getAddress());
			pstmt.setString(3, instance.getEmail());
			
			pstmt.execute();
			customer = instance;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return customer;
		}
		
		return customer;
	}

	@Override
	public boolean RemoveInstance(Customer instance) {
		// TODO Auto-generated method stub
		return false;
	}



}
