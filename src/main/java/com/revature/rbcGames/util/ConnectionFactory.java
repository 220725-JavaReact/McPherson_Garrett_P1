package com.revature.rbcGames.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
 //video 55:56
	private static ConnectionFactory connectionFactory;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private ConnectionFactory() {
		
	}
	
	public static ConnectionFactory getInstance() {
		// create when needed for lazy loading
		
		if(connectionFactory == null) connectionFactory = new ConnectionFactory();
		return connectionFactory;
	} 
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(SecretClass.url, SecretClass.username, SecretClass.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	
}
