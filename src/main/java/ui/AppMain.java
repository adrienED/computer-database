package ui;


import controller.Controller;
import service.CompanyService;
import service.ComputerService;

public class AppMain {

	public static void main(final String[] args) {
		
		CompanyService companyService = CompanyService.getInstance();
		ComputerService computerService = ComputerService.getInstance();

		UI view = new UI();
		Controller controller = new Controller(companyService, computerService, view);

		controller.start();
		
		

	}
}