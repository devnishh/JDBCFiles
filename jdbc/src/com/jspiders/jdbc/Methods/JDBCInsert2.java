package com.jspiders.jdbc.Methods;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCInsert2 {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nishant?user=root&&password=root");
			
			
			Statement statement = connection.createStatement();
			
			boolean res = statement.execute("INSERT INTO user VALUES(1, 'NISHANT', '9876543210','PASSWORD')");
			
			System.out.println(res);
			System.out.println("Data Inserted");
			
			statement.close();
			connection.close();
			
			
			DriverManager.deregisterDriver(driver);
			
	
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
