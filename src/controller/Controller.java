package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.ComputerMapper;
import model.Company;
import model.Computer;
import service.CompanyService;
import service.ComputerService;
import ui.AppMain;

public class Controller {

	public boolean FeaturesChoice(int choice) throws SQLException {

		boolean exit = false;

		switch (choice) {
		case 1:
			System.out.println(" list of all computer");
			listComputer();
			break;
		case 2:
			System.out.println(" list of all company");
			listCompany();
			break;
		case 3:
			System.out.println(" Computer details");
			ShowComputerDetails();
			break;
		case 4:
			System.out.println("insert computer");
			InsertComputer();
			break;
		case 5:
			System.out.println("update computer");
			UpdateComputer();
			break;
		case 6:
			System.out.println("delete computer");
			DeleteComputer();
			break;
		}
		exit = true;

		return exit;
	}

	private void listComputer() throws SQLException {

		List<Computer> listComputer = new ArrayList<>();
		listComputer = ComputerService.ListComputer();
		AppMain.ShowListComputer(listComputer);
	}

	private void listCompany() throws SQLException {

		List<Company> listCompany = new ArrayList<>();
		listCompany = CompanyService.ListComputer();
		AppMain.ShowListCompany(listCompany);

	}

	private void ShowComputerDetails() throws SQLException {
		Computer computer = new Computer();
		computer = ComputerMapper.Details();
		ComputerService.DetailsComputer(computer);
		AppMain.ShowComputerDetails(computer);

	}

	private void InsertComputer() throws SQLException {

		Computer computer = new Computer();
		computer = ComputerMapper.Insert();
		ComputerService.InsertComputer(computer);

	}

	private void DeleteComputer() throws SQLException {

		ComputerService.DeleteComputer();
	}

	private void UpdateComputer() throws SQLException {

		Computer computer = new Computer();
		computer = ComputerMapper.UpdateComputer();
		ComputerService.UpdateComputer(computer);
	}

}
