package ui;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import controller.Controller;

public class AppMain {
	
	static Controller controller = new Controller();

	public static void main(String[] args) throws SQLException, ParseException {

		int choice;
		boolean fin = false;
		do {

		System.out.println("Welcome to the computer-database");
		System.out.println("List of function : \n" + 
				" 1)   List computers\n" + 
				" 2)   List companies\n" + 
				" 3)   Show computer details (the detailed information of only one computer)\n" + 
				" 4)   Create a computer\n" + 
				" 5)   Update a computer\n" + 
				" 6)   Delete a computer\n" );
		
	      Scanner scanner = new Scanner(System.in);

	        
	        do {
	            System.out.print("Please enter number between 1 et 6: ");
	            while (!scanner.hasNextInt()) {
	                String input = scanner.next();
	                System.out.printf("\"%s\" is not a valid number.\n", input);
	                System.out.print("Please enter number between 1 et 6: ");
	                
	            }
	            choice = scanner.nextInt();
	        } while (choice > 7 || choice <1);

	        controller.FeaturesChoice(choice);
		}
		while (fin = false);

	 }
	
	
	
	public static String DeleteComputer() {
			 System.out.println("You choose to delete a computer");
			 Scanner sc = new Scanner(System.in);
			 String id = sc.nextLine();
			 return id;
		 }
		 
	
	 /*
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
		 */
		 
		
		
		
}
