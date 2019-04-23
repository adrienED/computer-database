package ui;


import controller.Controller;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import persistence.CompanyDAO;
import persistence.ComputerDAO;
import service.CompanyService;
import service.ComputerService;

public class AppMain {

	public static void main(final String[] args) {
		CompanyMapper companyMapper = new CompanyMapper();
		CompanyDAO companyDAO = new CompanyDAO();
		CompanyService companyService = new CompanyService(companyMapper, companyDAO);

		
		ComputerMapper computerMapper = new ComputerMapper(companyDAO);
		ComputerDAO computerDAO = new ComputerDAO ();
		ComputerService computerService = new ComputerService(computerMapper, computerDAO);
		
		
		UI view = new UI();
		Controller controller = new Controller(companyService, computerService, view);

		controller.start();

	}
}