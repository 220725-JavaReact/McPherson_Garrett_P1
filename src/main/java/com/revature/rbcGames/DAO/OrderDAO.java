package com.revature.rbcGames.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.rbcGames.models.Customer;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.Order;
import com.revature.rbcGames.models.StoreFront;
import com.revature.rbcGames.util.ConnectionFactory;

public class OrderDAO implements DAO<Order> {
	private static Logger logLogger = LogManager.getLogger(OrderDAO.class.getName());

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
			logLogger.warn("Failed read/write database at method AddInstance: \n" + e.getStackTrace());
			e.printStackTrace();
		}
		return order;
	}


	@Override
	public ArrayList<Order> GetAllInstances() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try(Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "select orders.id, orders.total, orders.ready, st.id as sid, st.\"name\"  as sname, st.address,\r\n"
					+ "c.id as cid, c.\"name\" , c.address as caddress, c.email \r\n"
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
				order.setReady(rs.getBoolean("ready"));
				storeFront.setName(rs.getString("sname"));
				storeFront.setAddress(rs.getString("address"));
				customer.setId(rs.getInt("cid"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("caddress"));
				customer.setEmail(rs.getString("email"));
				order.setCustomer(customer);
				order.setStoreFront(storeFront);
				orders.add(order);
			}
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method GetAllInstances \n" + e.getStackTrace());
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
			logLogger.warn("Failed read/write database at method getAllCustomerStoreInstances: \n" + e.getStackTrace());
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
			logLogger.warn("Failed read/write database at method GetAllCustomerInstances: \n" + e.getStackTrace());
			e.printStackTrace();
		}
		return orders;
		
	}

	@Override
	public Order UpdateInstance(Order instance) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Order> UpdateInstances(ArrayList<Order> instances){
		
		try (Connection con = ConnectionFactory.getInstance().getConnection()){
			String query = "";
			for(Order instance : instances){
				query ="update orders \r\n"
						+ "set ready = ? where id = ?;";
			}
					
			PreparedStatement pstmt = con.prepareStatement(query);
			
			for(int i = 0; i < instances.size(); i++) {
				
				pstmt.setBoolean(i*2+1, instances.get(i).getReady());
				pstmt.setInt(i*2+2, instances.get(i).getId());
			}
			
			pstmt.execute();
			return instances;
		} catch (SQLException e) {
			logLogger.warn("Failed read/write database at method UpdateInstance: \n" + e.getStackTrace());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean RemoveInstance(Order instance) {
		logLogger.warn("Method not implemented at RemovedInstance");
		return false;
	}

	@Override
	public ArrayList<Order> AddInstances(ArrayList<Order> newInstances) {
		logLogger.warn("Method not implemented at method AddInstances (array)");
		return null;
	}

}
