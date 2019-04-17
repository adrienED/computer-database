package ui;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controller.Controller;
import model.Company;
import model.Computer;
import persistence.CompanyDAO;
import persistence.ComputerDAO;

public class AppMain {
	
	static Controller controller = new Controller();

	public static void main(String[] args) throws SQLException, ParseException {
		
		System.out.println(testDate("2017-11-06", "2018-11-06"));
		
		
		
		System.out.println(testFormDate ("2018-11-06"));
		
		
		System.out.println("Welcome to the computer-database");
		System.out.println("List of function : \n" + 
				" 1)   List computers\n" + 
				" 2)   List companies\n" + 
				" 3)   Show computer details (the detailed information of only one computer)\n" + 
				" 4)   Create a computer\n" + 
				" 5)   Update a computer\n" + 
				" 6)   Delete a computer\n" );
		
		int choix = validateChoiceInput();
		
		controller.FeaturesChoice(choix);
		
		List<Company> test = new ArrayList<>();
/*

		CompanyDAO cdao = new CompanyDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
		test=cdao.listAllCompany();
		
		
		 for (Iterator iter = test.iterator(); iter.hasNext();)
	      {
	       String  ch2= (String) iter.next().toString();
	        System.out.println(ch2)  ;
	      }
	
	
	*/
	

}
		 
		 public static String ComputerDetails() {
			 System.out.println("You choose to show computer details\n");
			 System.out.print("Enter Computer id  : ");
			 Scanner sc = new Scanner(System.in);
			 String id = sc.nextLine();
			 return id;
		
		 }
		 
		 public static String DeleteComputer() {
			 System.out.println("You choose to delete a computer");
			 Scanner sc = new Scanner(System.in);
			 String id = sc.nextLine();
			 return id;
		 }
		 
		 private static int validateChoiceInput() {
		        Scanner scanner = new Scanner(System.in);

		        int number;
		        do {
		            System.out.print("Please enter number between 1 et 6: ");
		            while (!scanner.hasNextInt()) {
		                String input = scanner.next();
		                System.out.printf("\"%s\" is not a valid number.\n", input);
		                System.out.println("Please enter number between 1 et 6: ");
		                
		            }
		            number = scanner.nextInt();
		        } while (number > 7 || number <0);

		        return number;
		    


		 }
		 
		 
		 
		 private static int validateNameInput() {
		        Scanner scanner = new Scanner(System.in);

		        int number;
		        do {
		            System.out.print("Please enter a String ");
		            while (!scanner.hasNext()) {
		                String input = scanner.next();
		                System.out.printf("\"%s\" is not a valid number.\n", input);
		                System.out.println("Please enter number between 1 et 6: ");
		                
		            }
		            number = scanner.nextInt();
		        } while (number > 7 || number <0);

		        return number;
		    


		 }
		 
		 public static boolean testDate (String date1, String date2) throws ParseException {

			 boolean test = false;
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 Date dateUn = sdf.parse(date1);
			 Date dateDeux = sdf.parse(date2);

			 if (date1.compareTo(date2) < 0) test = true;
			 
			return test;

		 }
		 
		 
		 public static boolean testFormDate (String date)  {

			 boolean test = true;
			 
			 try {
			
			 java.sql.Date dateF = java.sql.Date.valueOf(date);

			 }
			 catch(IllegalArgumentException e) {
			 	test=false;
			 }
			return test;

		 }
		 
		 
		 

}
