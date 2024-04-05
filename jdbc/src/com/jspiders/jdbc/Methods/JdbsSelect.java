package com.jspiders.jdbc.Methods;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbsSelect {
	private static Driver driver;
	private static Statement statement;
	private static Connection connection;
	private static String query;
	

	public static void main(String[] args) {
		try {
			openConnection();
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nishant?user=root&&password=root");

			Statement statement = connection.createStatement();
			
			
			query  = "INSERT INTO user VALUES(3, 'Suyash', '8754693210','password2')";
			
			System.out.println(statement.execute(query));
			
			System.out.println("data is inserted");
			
			
					
					
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}
	
	
	
	private static void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private static void closeConnection() throws SQLException {
		if(connection!= null) {
			connection.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (driver!= null) {
			DriverManager.deregisterDriver(driver);
		}
	}
	
}
