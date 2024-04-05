package com.jspiders.jdbc.Methods;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class JDBCDelete {
	private static Driver driver;
	private static Connection connection;
	private static Statement statement;
	private static String query;
	
	public static void main(String[] args) {
		try {
			openConnection();
			statement = connection.createStatement();
			query = "DELETE FROM user ";
			int res = statement.executeUpdate(query);
			System.out.println(res + "row(s) affected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				closeConnection();
			} catch (Exception e2) {
          e2.printStackTrace();			}
		}
	}
	private static void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/nishant?user=root&&password=root");
			
		} catch (ClassNotFoundException | SQLException e) {
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
