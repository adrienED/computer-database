package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import model.Company;
import persistence.ComputerDAO;
import service.CompanyService;
import service.ComputerService;
import ui.UI;

public class Controller {
	private CompanyService companyService = CompanyService.getInstance();
	private ComputerService computerService = ComputerService.getInstance();

	public Controller() {
		super();
	
	}

	private ComputerDTO inputsToComputerDTO(Map<String, String> inputsCreateComputer) {

		ComputerDTO computerDTOCreate = new ComputerDTO();
		computerDTOCreate.setName(inputsCreateComputer.get("name"));
		computerDTOCreate.setIntroduced(inputsCreateComputer.get("introduced"));
		computerDTOCreate.setDiscontinued(inputsCreateComputer.get("discontinued"));
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(inputsCreateComputer.get("idCompany"));
		computerDTOCreate.setCompanyName(inputsCreateComputer.get("CompanyName"));
		return computerDTOCreate;

	}

	

	public ComputerDTO showComputerDetails(long id) {

		try {
			ComputerDTO computerDTO;
			computerDTO = computerService.findById(id);
			System.out.println(computerDTO);
		} catch (NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createComputer() {
		Map<String, String> inputsCreateComputer = vue.createComputer();

		ComputerDTO computerDTO = this.inputsToComputerDTO(inputsCreateComputer);
		
		
		try {
			long idCreate = computerService.create(computerDTO);
			System.out.println("Ordinateur creer avec l'id : " + idCreate);
		} catch (InvalidDateValueException | InvalidDateChronology |NumberFormatException e) {
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

	
	public List<ComputerDTO> getComputerPage(int page) {
		List<ComputerDTO> computerDAOList = new ArrayList<ComputerDTO>();
		ComputerService computerService = ComputerService.getInstance();
		try {
			computerDAOList = computerService.getAll(10, page);
		} catch (InvalidDateChronology e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computerDAOList;
		
	}
	
	public int getNbOfComputer() {
		
		return computerService.getNbOfComputer();
	}
	
	public List<CompanyDTO> getListCompany (){
		List<CompanyDTO> companyList;
		companyList = companyService.getAll(10, 10);
		return companyList;
	}
	
	public void updateComputer(ComputerDTO computerDTO)  {
		try {
			
			ComputerDTO computerDTOtoUpdate = computerService.findById(idUpdate);
			System.out.println("Ordiateur a modifier : " + computerDTOtoUpdate.toString());
			Map<String, String> inputsNewComputer = vue.createComputer();
			ComputerDTO computerDTOUpdate = this.inputsToComputerDTO(inputsNewComputer);
			computerDTOUpdate.setId(idUpdate);
			computerService.update(computerDTOUpdate);
		} catch (InvalidDateValueException | NotFoundException | InvalidDateChronology | ComputerNotFoundException e1) {
			
			Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, e1);
		}
	}
	
	
	
	
}
