package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import model.Computer;
import persistence.ComputerDAO;
import ui.AppMain;

public class Controller {
	
	public void FeaturesChoice(String choice) throws SQLException{
		
	switch (choice) {
	  case "1":
	    System.out.println("You choose to show the list of all computer");
	    listComputer();
	    break;
	  case "2":
	    System.out.println("You choose to show the list of all company");
	    break;
	  case "3":
	    
	    String id =AppMain.ComputerDetails();
	    
	    break;
	  case "4":
		  InsertComputer();
	    
	    break;
	  case "5":
	    System.out.println("You choose to update a computer");
	    
	    break;
	  case "6":
		  DeleteComputer();
		  break;
	  
	}
	}
	
	private void listComputer() {
		
        
    }
 
    
 
    private void listCompany() {
 
    }
    
    private void InsertComputer() throws SQLException {
    	System.out.println("You choose to insert a computer");
		 System.out.print("name ? : ");
		 Scanner sc = new Scanner(System.in);
		 String name = sc.nextLine();
		 System.out.print("Introduced date ? : ");
		 String introduced = sc.nextLine();
		 System.out.print("Discountinued date ? : ");
		 String discontinued = sc.nextLine();
		 System.out.print("Company_id? : ");
		 String company_id = sc.nextLine();
		 
		 Date introucedD = Date.valueOf(introduced);
		 Date discontinuedD = Date.valueOf(discontinued);
		 long company_idL=Long.valueOf(company_id).longValue();
    	
    	Computer computer = new Computer(name,introucedD,discontinuedD,company_idL);
    	ComputerDAO cdao = new ComputerDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
    	cdao.insertComputer(computer);
    	
    }
    
    private void DeleteComputer() throws SQLException {
    	String id =AppMain.DeleteComputer();
    	long idD=Long.valueOf(id).longValue();
    	Computer computer = new Computer(idD);
    	ComputerDAO cdao = new ComputerDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
    	cdao.deleteComputer(computer);
    	
    }
    
    

}
