package com.crimsonlogic.cms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	private static final String URL = "jdbc:postgresql://localhost:5432/CargoManagmentSystem";
	private static final String USER = "postgres";
	private static final String PASSWORD = "crimson@123";
	 
	 
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}


}
