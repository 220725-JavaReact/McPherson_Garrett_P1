package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.ConnectionFactory;

public class OrderDAO implements DAO<Order> {

	@Override
	public Order AddInstance(Order newInstance) {
		Order order = null;
		try (Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into orders (total, store, customer)\r\n"
					+ "values (?,?,?)"
					+ "returning id";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setDouble(1, newInstance.getTotal());
			pstmt.setInt(2, newInstance.getStoreFront().getId());
			pstmt.setInt(3, newInstance.getCustomer().getId());
			/*pstmt.execute();
			query = "select max(id)\r\n"
					+ "from orders\r\n"
					+ "where customer = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, newInstance.getCustomer().getId());*/
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			newInstance.setId(rs.getInt("id"));
			order = newInstance; //hoping this works*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	/*@Override
	public Order GetInstanceByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public ArrayList<Order> GetAllInstances() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select orders.id, orders.total, st.id as sid, st.\"name\"  as sname, st.address,\r\n"
					+ "c.id as cid, c.\"name\" , c.address, c.email \r\n"
					+ "from orders\r\n"
					+ "join storefront st on orders.store = st.id\r\n"
					+ "join customers c on orders.customer = c.id ";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			//pstmt.setInt(1, customer.getId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				StoreFront storeFront = new StoreFront();
				Customer customer = new Customer();
				order.setId(rs.getInt("id"));
				order.setTotal(rs.getDouble("total"));
				storeFront.setName(rs.getString("name"));
				storeFront.setAddress(rs.getString("address"));
				order.setStoreFront(storeFront);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
		
	}
	
	public ArrayList<Order> GetAllCustomerStoreInstances(Customer customer, StoreFront store){
		ArrayList<Order> orders = new ArrayList<>();
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select orders.id, orders.total, st.\"name\", st.address  \r\n"
					+ "from orders\r\n"
					+ "join storefront st on orders.store = st.id\r\n"
					+ "where customer=? and store =?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, customer.getId());
			pstmt.setInt(2, store.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				StoreFront newStore = new StoreFront();
				order.setId(rs.getInt("id"));
				order.setTotal(rs.getDouble("total"));
				newStore.setName(rs.getString("name"));
				newStore.setAddress(rs.getString("address"));
				order.setStoreFront(newStore);
				orders.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public ArrayList<Order> GetAllCustomerInstances(Customer customer){
		ArrayList<Order> orders = new ArrayList<Order>();
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select orders.id, orders.total, st.\"name\", st.address  \r\n"
					+ "from orders\r\n"
					+ "join storefront st on orders.store = st.id\r\n"
					+ "where customer=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, customer.getId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				StoreFront newStore = new StoreFront();
				order.setId(rs.getInt("id"));
				order.setTotal(rs.getDouble("total"));
				order.setReady(rs.getBoolean("ready"));
				newStore.setName(rs.getString("name"));
				newStore.setAddress(rs.getString("address"));
				order.setStoreFront(newStore);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
		
	}

	@Override
	public Order UpdateInstance(Order instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean RemoveInstance(Order instance) {
		// TODO Auto-generated method stub
		return false;
	}

}
