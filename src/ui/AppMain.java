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
		for (Iterator iter = listComputer.iterator(); iter.hasNext();) {
			String ch = (String) iter.next().toString();
			System.out.println(ch);
		}
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

	public void pagination(List<Computer> listcomputer) {

		Map<Integer, List<Computer>> HM = new HashMap<Integer, List<Computer>>();

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
		} while (supIndex + 10 < max);
	}

	/*
	 * private static int validateNameInput() { Scanner scanner = new
	 * Scanner(System.in);
	 * 
	 * int number; do { System.out.print("Please enter a String "); while
	 * (!scanner.hasNext()) { String input = scanner.next();
	 * System.out.printf("\"%s\" is not a valid number.\n", input);
	 * System.out.println("Please enter number between 1 et 6: ");
	 * 
	 * } number = scanner.nextInt(); } while (number > 7 || number <0);
	 * 
	 * return number;
	 * 
	 * }
	 */

}
