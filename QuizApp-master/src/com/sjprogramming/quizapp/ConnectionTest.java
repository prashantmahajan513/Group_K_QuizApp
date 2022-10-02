package com.sjprogramming.quizapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {
	//Creating connection
Connection connection=null;

	public Connection getConnectionDetails() {
		try {
			//Loading the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establishing the connection
	connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema","root","94035854");
		}catch(Exception e) {
			e.printStackTrace();	
		}
		//Returning connection to method ConnectionTest
		return connection;
	}
}

