package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UI {


	public void showActions() {

		System.out.println("Welcome to the computer-database");
		System.out.println("List of function : \n" + " 1) "
				+ "  List computers\n"
				+ " 2)   List companies\n"
				+ " 3)   Show computer details (the detailed information of only one computer)\n"
				+ " 4)   Create a computer\n" 
				+ " 5)   Update a computer\n" 
				+ " 6)   Delete a computer\n");
	}

	public String readInputs() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);

		return str;
	}
	public int readInputInt() {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		System.out.println("Vous avez saisi : " + input);

		return input;
	}
	public void showComputer() {

		System.out.println("Actions : ShowComputer"
				+ "\n" + "ID ? :");
				
	}
	
	public void deleteComputer() {

		System.out.println("Actions : deleteComputer"
				+ "\n" + "ID ? : ");
	
	}
	

	public Map<String, String> createComputer() {
		Map<String, String> inputsCreateComputer = new HashMap<String, String>();
		
		//get the name
		System.out.println("Actions : CreateComputer"
				+ "\n" + "Entrez le nom de l'ordinateur : ");
		String name = readInputs();
		inputsCreateComputer.put("name", name);
		
		//get the introducedDate
		System.out.println("Introduced Date (format yyyy-mm-dd) : ");
		String introduced = readInputs();
		inputsCreateComputer.put("introduced", introduced);

		
		System.out.println("Discountinued Date (format yyyy-mm-dd) : ");
		String discontinued = readInputs();
		inputsCreateComputer.put("discontinued", discontinued);
		
		//get the idCompany
		System.out.println("Entrez l'id du constructeur de l'ordinateur : ");
		String IdCompany = readInputs();
		inputsCreateComputer.put("idCompany", IdCompany);
		
		return inputsCreateComputer;
	}
	public void updateComputer() {

		System.out.println("Actions : updateComputer"
				+ "\n" + "Id ?");
	}
	
	public void menuNextPage() {
		System.out.println(" "
				+ "\n" + "Pagination"
				+ "\n" + "0 : back to menu"
				+ "\n" + "1 : next page"
				+ "\n" + "2 : previous page");
}

	
}