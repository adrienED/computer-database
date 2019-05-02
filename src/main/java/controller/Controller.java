package controller;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import service.CompanyService;
import service.ComputerService;
import ui.UI;

public class Controller {
	public CompanyService companyService = CompanyService.getInstance();
	public ComputerService computerService = ComputerService.getInstance();
	public UI vue;

	Logger logger = LoggerFactory.getLogger(Controller.class);

	public Controller() {
		super();
	}

	public ComputerDTO inputsToComputerDTO(Map<String, String> inputsCreateComputer) {

		ComputerDTO computerDTOCreate = new ComputerDTO();
		computerDTOCreate.setName(inputsCreateComputer.get("name"));
		computerDTOCreate.setIntroduced(inputsCreateComputer.get("introduced"));
		computerDTOCreate.setDiscontinued(inputsCreateComputer.get("discontinued"));
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(inputsCreateComputer.get("idCompany"));
		computerDTOCreate.setCompanyDTO(companyDTO);
		return computerDTOCreate;

	}

	public void createComputer() {
		Map<String, String> inputsCreateComputer = vue.createComputer();

		ComputerDTO computerDTO = this.inputsToComputerDTO(inputsCreateComputer);

		try {
			long idCreate = computerService.create(computerDTO);
			System.out.println("Ordinateur creer avec l'id : " + idCreate);
		} catch (InvalidDateValueException | InvalidDateChronology | NumberFormatException e) {
			logger.error("erreur createComputer", e);
		}
	}

	public List<CompanyDTO> listCompany() {
		List<CompanyDTO> companyList;
		companyList = companyService.getAll(10, 10);
		return companyList;	}
}
