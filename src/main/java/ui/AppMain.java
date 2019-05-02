package ui;


import controller.Controller;
import service.CompanyService;
import service.ComputerService;

public class AppMain {

	public static void main(final String[] args) {
		
		CompanyService companyService = CompanyService.getInstance();
		ComputerService computerService = ComputerService.getInstance();
	}
}