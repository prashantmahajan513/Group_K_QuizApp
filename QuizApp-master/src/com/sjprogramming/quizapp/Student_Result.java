package com.sjprogramming.quizapp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student_Result {
	//To display result in ascending order
	Connection con = null;
	
	public static void main(String[] args) {
		//Calling DisplayResult() from Student_Result class
		Student_Result student_Result = new Student_Result();
		student_Result.DisplayResult();
	}
	  public void DisplayResult(){
		  //logic for displaying result
	 
	    	try {
	    		ConnectionTest connectionTest=new ConnectionTest();
	        	con=connectionTest.getConnectionDetails();
				
	        	//Query for reading data from database to display result on console in ascending order
				PreparedStatement ps = con.prepareStatement("SELECT * FROM studentinf ORDER BY StudentID ASC;");
	
				System.out.println("_________________STUDENT RESULT_________________");
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					System.out.println("Student ID : "+ rs.getInt(2));
					System.out.println("Student Name : "+ rs.getString(3));
					System.out.println("Student Marks : "+ rs.getString(4));
					System.out.println("Class obtained : "+ rs.getString(5)+"\n");
				}
				con.close();
	         	ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	    }
	}
