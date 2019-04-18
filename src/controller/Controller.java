package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Company;
import model.Computer;
import persistence.ComputerDAO;
import service.CompanyService;
import service.ComputerService;
import ui.AppMain;

public class Controller {
	
	public void FeaturesChoice(int choice) throws SQLException{
		
	switch (choice) {
	  case 1:
	    System.out.println("You choose to show the list of all computer");
	    listComputer();
	    break;
	  case 2:
	    System.out.println("You choose to show the list of all company");
	    listCompany();
	    break;
	  case 3:
	      
		 ShowComputerDetails();
	    
	    break;
	  case 4:
		  InsertComputer();
	    
	    break;
	  case 5:
		  UpdateComputer();
	    
	    break;
	  case 6:
		  
		  DeleteComputer();
		  break;
	  
	}
	}
	
	private void listComputer() throws SQLException {
		
		List<Computer> listComputer = new ArrayList<>();
		listComputer = ComputerService.ListComputer();
		
		for (Iterator iter = listComputer.iterator(); iter.hasNext();)
	      {
	       String  ch2= (String) iter.next().toString();
	        System.out.println(ch2)  ;
	      }

    }
 
    private void listCompany() throws SQLException {
    	List<Company> listCompany = new ArrayList<>();
		listCompany = CompanyService.ListComputer();
		
		for (Iterator iter = listCompany.iterator(); iter.hasNext();)
	      {
	       String  ch2= (String) iter.next().toString();
	        System.out.println(ch2)  ;
	      }
    }
    
    
    private void ShowComputerDetails() throws SQLException {
		 System.out.println("You choose to show computer details\n");
		 
		 
		 System.out.print("Enter Computer id  : ");
		 Scanner sc = new Scanner(System.in);
		 String company_id = sc.nextLine();
		 long id=Long.valueOf(company_id).longValue();
		 Computer computer = new Computer(id);
		 ComputerDAO cdao = new ComputerDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
		 System.out.println(cdao.ComputerIDCheck(id));
		 
		 Computer computera = cdao.getComputer(id);
		 System.out.println(computera);
		 
		 
		 
		 
		 
		 
	
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
		 
		 ComputerDAO cdao = new ComputerDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
		 System.out.println(cdao.CompanyIDCheck(company_idL));
		
    	
    	Computer computer = new Computer(name,introucedD,discontinuedD,company_idL);
    	
    	cdao.insertComputer(computer);
    	
    }
    
    private void DeleteComputer() throws SQLException {
    	String id =AppMain.DeleteComputer();
    	long idD=Long.valueOf(id).longValue();
    	
    	Computer computer = new Computer(idD);
    	ComputerDAO cdao = new ComputerDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
    	System.out.println(cdao.ComputerIDCheck(idD));
    	//cdao.deleteComputer(computer);
    	
    }
    
    private void UpdateComputer() throws SQLException {
    	 System.out.println("You choose to update a computer");
    	 
    	 System.out.print("id? : ");
		 Scanner sc = new Scanner(System.in);
		 String idC = sc.nextLine();
    	 
    	 
		 System.out.print("name ? : ");
		 
		 String name = sc.nextLine();
		 System.out.print("Introduced date ? : ");
		 String introduced = sc.nextLine();
		 System.out.print("Discountinued date ? : ");
		 String discontinued = sc.nextLine();
		 System.out.print("Company_id 	? : ");
		 String company_id = sc.nextLine();
		 
		 long id = Long.valueOf(idC).longValue();
		 Date introucedD = Date.valueOf(introduced);
		 Date discontinuedD = Date.valueOf(discontinued);
		 long company_idL=Long.valueOf(company_id).longValue();
		 
		
    	
    	Computer computer = new Computer(id,name,introucedD,discontinuedD,company_idL);
    	ComputerDAO cdao = new ComputerDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
    	System.out.println(cdao.CompanyIDCheck(company_idL));
    	cdao.updateComputer(computer);
    	
    }
    
   
    
    

}
