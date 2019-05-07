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

	

	public ComputerDTO getComputerDTOById(String id) {
		ComputerDTO computerDTO = new ComputerDTO();
		try {
			
			computerDTO = computerService.findById(id);
			
		} catch (NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return computerDTO;
	}


	public void deleteComputer(String id) {
		try {
	
			ComputerDTO computerDTOtoDelete = computerService.findById(id);
			computerService.delete(computerDTOtoDelete);
			
		} catch (InvalidDateValueException | NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	
	public List<ComputerDTO> getComputerPage(int page) {
		List<ComputerDTO> computerDAOList = new ArrayList<ComputerDTO>();
		ComputerService computerService = ComputerService.getInstance();
		
		if (page != 1) page = page*10-10;
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
		companyList = companyService.getAll(200, 1);
		return companyList;
	}
	
	public void updateComputer(ComputerDTO computerDTO)  {
		try {
			
			computerService.update(computerDTO);
		} catch (InvalidDateValueException | InvalidDateChronology e1) {
			
			Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, e1);
		}
	}
	
	public List<ComputerDTO> search(String keyword) throws InvalidDateChronology{
		List<ComputerDTO> listComputerDTOs =computerService.search(keyword);
		
		
		return listComputerDTOs;
		
	}
	
	public void deleteComputerById(String id) {
		try {
	
			ComputerDTO computerDTOtoDelete = computerService.findById(id);
			computerService.delete(computerDTOtoDelete);
			
		} catch (InvalidDateValueException | NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
}
