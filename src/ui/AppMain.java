package ui;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public static void main(String[] args) throws SQLException {
		
		
		System.out.println("Welcome to the computer-database");
		System.out.println("List of function : \n" + 
				" 1)   List computers\n" + 
				" 2)   List companies\n" + 
				" 3)   Show computer details (the detailed information of only one computer)\n" + 
				" 4)   Create a computer\n" + 
				" 5)   Update a computer\n" + 
				" 6)   Delete a computer\n" );
		
		System.out.println("Please enter a number :");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		System.out.println("You enter " + choix);
		
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
	List<Computer> testa = new ArrayList<>();

	ComputerDAO cdaoa = new ComputerDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
	testa=cdaoa.listAllComputer();
	
	
	 for (Iterator iter = testa.iterator(); iter.hasNext();)
      {
       String  ch2= (String) iter.next().toString();
        System.out.println(ch2)  ;
      }

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
		 
		


	

}
