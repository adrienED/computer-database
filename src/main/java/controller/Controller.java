package controller;

import java.util.List;
import java.util.Map;
import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import service.CompanyService;
import service.ComputerService;
import ui.UI;

public class Controller {
	private CompanyService companyService;
	private ComputerService computerService;
	private UI vue;

	public Controller(CompanyService companyService, ComputerService computerService, UI vue) {
		super();
		this.companyService = companyService;
		this.computerService = computerService;
		this.vue = vue;
	}

	public void start() {

		while (true) {
			vue.showActions();
			int choix = vue.readInputInt();
			if (choix <1 || choix >6) {
				System.out.println("Veuillez enter un chiffre entre 1 et 6");
				break;
			}
			runChoice(choix);
		}
	}

	public void runChoice(int choix) {

		switch (choix) {

		// List Computers
		case 1:
			listComputers();
			break;

		// List Companies
		case 2:

			List<CompanyDTO> companyList;
			companyList = companyService.getAll(10, 10);
			companyList.forEach(System.out::println);

			break;

		// show Computer
		case 3:
			showComputer();
			break;

		// create Computer
		case 4:
			createComputer();
			break;

		// update Computer
		case 5:
			updateComputer();
			break;

		// delete Computer
		case 6:
			deleteComputer();
			break;
		default:

		}

	}

	private ComputerDTO inputsToComputerDTO(Map<String, String> inputsCreateComputer) {

		ComputerDTO computerDTOCreate = new ComputerDTO();
		computerDTOCreate.setName(inputsCreateComputer.get("name"));
		computerDTOCreate.setIntroducedDate(inputsCreateComputer.get("introducedDate"));
		computerDTOCreate.setDiscontinuedDate(inputsCreateComputer.get("discontinuedDate"));
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(inputsCreateComputer.get("idCompany"));
		computerDTOCreate.setCompanyDTO(companyDTO);
		return computerDTOCreate;

	}

	private void updateComputer()  {
		try {
			vue.updateComputer();
			String idUpdate = vue.readInputs();
			ComputerDTO computerDTOtoUpdate = computerService.findById(idUpdate);
			System.out.println("Ordiateur a modifier : " + computerDTOtoUpdate.toString());
			Map<String, String> inputsNewComputer = vue.createComputer();
			ComputerDTO computerDTOUpdate = this.inputsToComputerDTO(inputsNewComputer);
			computerDTOUpdate.setId(idUpdate);
			computerService.update(computerDTOUpdate);
		} catch (InvalidDateValueException | NotFoundException | InvalidDateChronology | ComputerNotFoundException e1) {
			
			System.out.println(e1.getMessage());
		}
	}

	private void showComputer() {

		try {
			vue.showComputer();
			String id = vue.readInputs();
			ComputerDTO computerDTO;
			computerDTO = computerService.findById(id);
			System.out.println(computerDTO);
		} catch (NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createComputer() {
		Map<String, String> inputsCreateComputer = vue.createComputer();
		ComputerDTO computerDTOCreate = this.inputsToComputerDTO(inputsCreateComputer);

		try {
			long idCreate = computerService.create(computerDTOCreate);
			System.out.println("Ordinateur creer avec l'id : " + idCreate);
		} catch (InvalidDateValueException | InvalidDateChronology e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private void deleteComputer() {
		try {
			vue.deleteComputer();
			String idDelete = vue.readInputs();
			ComputerDTO computerDTOtoDelete = computerService.findById(idDelete);
			computerService.delete(computerDTOtoDelete);
			
		} catch (InvalidDateValueException | NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private void listComputers() {
		try {
			boolean returnMenu = false;
			int n = 1;
			while (!returnMenu) {
				List<ComputerDTO> computerDaoList;
				computerDaoList = computerService.getAll(10, n);
				computerDaoList.forEach(System.out::println);
				vue.menuNextPage();
				String choix2 = vue.readInputs();
				switch (choix2) {
				// retour
				case "0":
					returnMenu = true;
					break;
				// page suivante
				case "1":
					n += 10;
					break;
				// page precedente
				case "2":
					n -= 10;
					break;
				}
			}

		} catch (InvalidDateChronology e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
