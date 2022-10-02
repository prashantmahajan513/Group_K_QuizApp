package com.sjprogramming.quizapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	//Creating Main class
	
    public static void main(String[] args) {
    	//Creating object of Quiz class for executing logic() method
        Quiz q=new Quiz();
        q.logic();
    }
}

class Quiz{
	//Writing logic for printing question and options
	//Creating global variables 
	//created object of Connection, PreparedStatement interface, and Scanner class.
	Connection con = null;
    Scanner sc=new Scanner(System.in);
    PreparedStatement prs = null;
    
    //Initialized global variable correctAnsCount and wrongAnsCount for counting the correct and incorrect answers
    int correctAnsCount=0;
    int wrongAnsCount=0;
    
    
    public void logic(){
    	// creating object of question 
        Questions q1 = new Questions(" Which statement is true about Java?", "A. Java is a sequence-dependent programming language ", "B. Java is a code dependent programming language ", "C. Java is a platform-dependent programming language ", "D. Java is a platform-independent programming language ");
        Questions q2 = new Questions("What is the extension of java code files?", " A. .txt", "B. .pdf", "C. .sql", "D. .java");
        Questions q3 = new Questions("Who invented Java Programming? ", "A. Guido van Rossum", "B. James Gosling", "C. Dennis Ritchie", "D. Bjarne Stroustrup");
        Questions q4 = new Questions("Which one of the following is not a Java feature?", "A. Object-oriented", "B. Use of pointers", "C. Portable", "D. Dynamic and Extensible");
        Questions q5 = new Questions("Which of these cannot be used for a variable name in Java?", "A. identifier & keyword", "B. identifier", "C. Keyword", "D. none of the mentioned");
        Questions q6 = new Questions("Number of primitive data types in Java are?","A. 6","B. 7","C. 8","D. 9"); 
        Questions q7 = new Questions("In which of the following is toString() method defined?","A. java.lang.Object","B. java.lang.String","C.java.lang.util","D. None");
        Questions q8 = new Questions("Where does the system stores parameters and local variables whenever a method is invoked?","A. Heap", "B. Stack", "C. Array", "D. Tree");
        Questions q9 = new Questions("Identify the modifier which cannot be used for constructor.","A. Public","B. Protected","C. Private","D. Static"); 
        Questions q10 = new Questions("What is the implicit return type of constructor?","A. No return type","B. A class object in which it is defined","C. Void","D. None");
        
        //Storing question and respective correct answers in HashMap
        Map<Questions,Character> hmap=new HashMap<>(); 
        hmap.put(q1,'D');
        hmap.put(q2,'D');
        hmap.put(q3,'B');
        hmap.put(q4,'B');
        hmap.put(q5,'C');
        hmap.put(q6,'C');
        hmap.put(q7,'A');
        hmap.put(q8,'B');
        hmap.put(q9,'D');
        hmap.put(q10,'B');
        
      //Taking user input for student ID and name
        System.out.println("Student ID >>");
        int id = sc.nextInt();
        System.out.println("Student Name >>");
        String StudentName = sc.next();
        
        //Iterating for question and option using for each loop
        for(Map.Entry<Questions,Character> map:hmap.entrySet()){
            System.out.println(map.getKey().getQuestion());
            System.out.println(map.getKey().getOption1());
            System.out.println(map.getKey().getOption2());
            System.out.println(map.getKey().getOption3());
            System.out.println(map.getKey().getOption4());
            
            //Taking user input for option
            System.out.println("Enter Your Answer: ");
            Character ans=sc.next().charAt(0);
            
            //Comparing user input option with correct option which is stored in HashMap
            int cans=Character.compare(ans,map.getValue());
            
            //Counting the correct and incorrect answers
            if(cans==0){
                System.out.println("Correct");
                correctAnsCount++;
            }
            else{
                System.out.println("Incorrect");
                wrongAnsCount++;
            }

        }
        
        //Printing the final result
        System.out.println();
        System.out.println("--------Result---------");
        System.out.println("Total Questions: "+hmap.size());
        System.out.println("Correct Answered Questions : "+correctAnsCount);
        System.out.println("Wrong Answered Questions : "+wrongAnsCount);
        int percentage=(100*correctAnsCount)/hmap.size();
        
        System.out.println("Percentage : "+percentage);
        
        //Calling getGrade() from Quiz class and storing value of grade into variable studentGrade
        String studentGrade = getGrade(); 
        
        try {
        	//Calling the getConnectionDetails() from ConnectionTest class
        	ConnectionTest connectionTest=new ConnectionTest();
        	con=connectionTest.getConnectionDetails();
        	
        	//Storing studentID, Student Name, marks, and result into database
			prs=con.prepareStatement("INSERT INTO studentinf(StudentID,StudentName,StudentMarks,Result) values(?,?,?,?)");
			prs.setInt(1, id);
        	prs.setString(2, StudentName);
			prs.setLong(3, correctAnsCount);
			prs.setString(4, studentGrade);
			
			prs.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
    }
    
    public String getGrade() {
    	//Calculating grades on the basis of correct answers
    	String grade = null;
    	
    	  if(correctAnsCount>= 8){
    		  grade = "Class A";
              System.out.println("Class A");
          }
          else if(correctAnsCount>=6 && correctAnsCount<=7){
        	  grade = "Class B";
              System.out.println("Class B");
          }
          else if(correctAnsCount==5){
        	  grade = "Class C";
              System.out.println("Class C");
          }else{
        	  grade = "Class D";
        	  System.out.println("Fail");
          }
    	  return grade;
    }
}

