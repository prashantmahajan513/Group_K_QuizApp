package com.sjprogramming.quizapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentID_Result {
	//To get the particular record by using student id only. 
	//If someone wants to retrieve theirs score from database.

	Connection con = null;
	
	public void getIdResult(int ID) {
		//logic for displaying result on the basis of studentID
		try {
			ConnectionTest connectionTest=new ConnectionTest();
        	con=connectionTest.getConnectionDetails();
			
        	//Query for reading data from database to display result on console as per studentID
			PreparedStatement ps = con.prepareStatement("SELECT StudentID,StudentName,StudentMarks,Result FROM studentinf WHERE StudentID=? ;");
			ps.setInt(1, ID);
	
			System.out.println("_________________STUDENT RESULT_________________");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("Student ID : "+ rs.getInt(1));
				System.out.println("Student Name : "+ rs.getString(2));
				System.out.println("Student Marks : "+ rs.getString(3));
				System.out.println("Class obtained : "+ rs.getString(4)+"\n");
			}
			con.close();
         	ps.close();
         	rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		//Taking user input studentID
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student ID>>");
		int ID =sc.nextInt();
		
		//Calling getIdResult() method in main method
		StudentID_Result studentID_Result = new StudentID_Result();
		studentID_Result.getIdResult(ID);
		sc.close();
	}
}
