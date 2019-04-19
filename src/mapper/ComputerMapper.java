package mapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import model.Computer;
import persistence.ComputerDAO;

public class ComputerMapper {

	public static Computer Insert() throws SQLException {

		ComputerDAO cdao = new ComputerDAO();

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
		long company_idL = Long.valueOf(company_id).longValue();
		System.out.println(cdao.CompanyIDCheck(company_idL));
		Computer computer = new Computer(name, introucedD, discontinuedD, company_idL);
		return computer;
	}

	public static Computer Details() throws SQLException {
		ComputerDAO cdao = new ComputerDAO();
		Computer computer = new Computer();
		long id;
		do {
			System.out.print("Enter Computer id  : ");
			Scanner sc = new Scanner(System.in);
			String company_id = sc.nextLine();
			id = Long.valueOf(company_id).longValue();
			computer.setId(id);

			if (!cdao.ComputerIDCheck(id))
				System.out.println("id not found : ");

		} while (cdao.ComputerIDCheck(id) != true);
		return computer;
	}

	public static Computer UpdateComputer() throws SQLException {
		String name;
		String introduced;
		String discontinued;
		String idC;
		String company_id;
		do {

		System.out.print("id? : ");
		Scanner sc = new Scanner(System.in);
		idC = sc.nextLine();
		System.out.print("name ? : ");
		name = sc.nextLine();
		System.out.print("Introduced date ? : ");
		introduced = sc.nextLine();
		System.out.print("Discountinued date ? : ");
		discontinued = sc.nextLine();
		System.out.print("Company_id 	? : ");
		company_id = sc.nextLine();
		}
		while(testFormDate(introduced)==true && testFormDate(discontinued)==true
				&& testDate(introduced,discontinued)==true);

		long id = Long.valueOf(idC).longValue();
		Date introucedD = Date.valueOf(introduced);
		Date discontinuedD = Date.valueOf(discontinued);
		long company_idL = Long.valueOf(company_id).longValue();

		Computer computer = new Computer(id, name, introucedD, discontinuedD, company_idL);
		ComputerDAO cdao = new ComputerDAO();
		if(cdao.CompanyIDCheck(company_idL)==false)computer.setCompany_id(0); ;
		
		return computer;
	}

	public static boolean testDate(String date1, String date2) {
		boolean test = false;
		if (date1.compareTo(date2) < 0)
			test = true;

		return test;

	}

	public static boolean testFormDate(String date) {

		boolean test = true;

		try {

			java.sql.Date dateF = java.sql.Date.valueOf(date);

		} catch (IllegalArgumentException e) {
			test = false;
		}
		return test;

	}
}
