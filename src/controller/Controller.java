package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Company;
import model.Computer;
import persistence.ComputerDAO;
import service.CompanyService;
import service.ComputerService;
import ui.AppMain;

public class Controller {

	public boolean FeaturesChoice(int choice) throws SQLException {
		
		boolean exit = false;
		
		switch (choice) {
		case 1:		
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
		exit=true;
		
		return exit;
	}

	private void listComputer() throws SQLException {

		List<Computer> listComputer = new ArrayList<>();
		listComputer = ComputerService.ListComputer();

		for (Iterator iter = listComputer.iterator(); iter.hasNext();) {
			String ch = (String) iter.next().toString();
			System.out.println(ch);
		}
		
	}

	private void listCompany() throws SQLException {
		System.out.println("You choose to show the list of all computer");
		List<Company> listCompany = new ArrayList<>();
		listCompany = CompanyService.ListComputer();
		
		
		for (Iterator iter = listCompany.iterator(); iter.hasNext();) {
			String ch = (String) iter.next().toString();
			System.out.println(ch);
		}

		
	}

	private void ShowComputerDetails() throws SQLException {
		System.out.println("You choose to show computer details\n");
		ComputerDAO cdao = new ComputerDAO();
		long id;
		do {
		System.out.print("Enter Computer id  : ");
		Scanner sc = new Scanner(System.in);
		String company_id = sc.nextLine();
		id = Long.valueOf(company_id).longValue();
		Computer computer = new Computer(id);
		
		if (! cdao.ComputerIDCheck(id)) System.out.println("id not found : ");
		
		}
		while (cdao.ComputerIDCheck(id) != true);
		Computer computera = cdao.getComputer(id);
		System.out.println(computera);

	}

	private void InsertComputer() throws SQLException {
		System.out.println("You choose to insert a computer");
		
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

		cdao.insertComputer(computer);

	}

	private void DeleteComputer() throws SQLException {
		String id = AppMain.DeleteComputer();
		long idD = Long.valueOf(id).longValue();

		Computer computer = new Computer(idD);
		ComputerDAO cdao = new ComputerDAO();
		do {
			System.out.println(cdao.ComputerIDCheck(idD));
			
		}
		while(cdao.ComputerIDCheck(idD) != true);
			
		cdao.deleteComputer(computer);

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
		long company_idL = Long.valueOf(company_id).longValue();

		Computer computer = new Computer(id, name, introucedD, discontinuedD, company_idL);
		ComputerDAO cdao = new ComputerDAO();
		System.out.println(cdao.CompanyIDCheck(company_idL));
		cdao.updateComputer(computer);

	}
	
	public void pagination (List<Computer> listcomputer) {
		
		Map <Integer , List<Computer>> HM = new HashMap<Integer , List<Computer>>();
		
		int max = listcomputer.size();
		int infIndex=0;
		int supIndex;
		if (max%10 != 0) { supIndex=max%10;}
		else supIndex=10;
		
		do {
			
		List<Computer> ListPage = new ArrayList<>();
		ListPage = listcomputer.subList(infIndex, supIndex);
		
				
		}
		while(supIndex+10<max);
		
	}
	
	
	
	
	

}
