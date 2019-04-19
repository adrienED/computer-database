package ui;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import controller.Controller;
import model.Company;
import model.Computer;

public class AppMain {

	static Controller controller = new Controller();

	public static void main(String[] args) throws SQLException, ParseException {

		int choice;
		boolean fin = false;
		do {

			System.out.println("Welcome to the computer-database");
			System.out.println("List of function : \n" + " 1)   List computers\n" + " 2)   List companies\n"
					+ " 3)   Show computer details (the detailed information of only one computer)\n"
					+ " 4)   Create a computer\n" + " 5)   Update a computer\n" + " 6)   Delete a computer\n");

			Scanner scanner = new Scanner(System.in);

			do {
				System.out.print("Please enter number between 1 et 6: ");
				while (!scanner.hasNextInt()) {
					String input = scanner.next();
					System.out.printf("\"%s\" is not a valid number.\n", input);
					System.out.print("Please enter number between 1 et 6: ");

				}
				choice = scanner.nextInt();
			} while (choice > 7 || choice < 1);

			controller.FeaturesChoice(choice);
		} while (fin = false);

	}

	public static String DeleteComputer() {
		System.out.println("You choose to delete a computer");
		System.out.print("Computer id ?  : ");
		Scanner sc = new Scanner(System.in);
		String id = sc.nextLine();
		return id;
	}

	public static void ShowListComputer(List<Computer> listComputer) {
		
		Map<Integer, List<Computer>> HM = new HashMap<Integer, List<Computer>>();
		HM=pagination(listComputer);
		int page;
		do {
			
			System.out.print("Enter page number : ");
			Scanner sc = new Scanner (System.in);
			page = sc.nextInt();
			
			List<Computer> listPageComputer = new ArrayList<>();
			listPageComputer=HM.get(page);
			
			for (Iterator iter = listPageComputer.iterator(); iter.hasNext();) {
				String ch = (String) iter.next().toString();
				System.out.println(ch);
			
		}
		}
			while(page <HM.size());
	
	}

	public static void ShowListCompany(List<Company> listCompany) {
		
		

		for (Iterator iter = listCompany.iterator(); iter.hasNext();) {
			String ch = (String) iter.next().toString();
			System.out.println(ch);
		}
	}

	public static void ShowComputerDetails(Computer computer) {

		System.out.println(computer);
	}


	public static Map<Integer, List<Computer>> pagination(List<Computer> listcomputer) {

		Map<Integer, List<Computer>> HM = new HashMap<Integer, List<Computer>>();
		int i = 0;
		int max = listcomputer.size();
		int infIndex = 0;
		int supIndex;
		if (max % 10 != 0) {
			supIndex = max % 10;
		} else
			supIndex = 10;

		do {

			List<Computer> ListPage = new ArrayList<>();
			ListPage = listcomputer.subList(infIndex, supIndex);
			HM.put(i, ListPage);
			i++;
			infIndex+=10;
			supIndex+=10;
		} while (supIndex + 10 < max);
		
		infIndex+=10;
		supIndex+=max-supIndex;
		i++;
		List<Computer> ListPage = new ArrayList<>();
		ListPage = listcomputer.subList(infIndex, supIndex);
		HM.put(i, ListPage);
		
		return HM;
	}



}
