package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import model.Company;
import persistence.CompanyDAO;
import persistence.ComputerDAO;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

public class Controller {
	private CompanyService companyService = CompanyService.getInstance();
	private ComputerService computerService = ComputerService.getInstance();
	private ComputerValidator computerValidator = ComputerValidator.getInstance();
	
	Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	public Controller() {
	}

	public ComputerDTO getComputerDTOById(String id) {
		ComputerDTO computerDTO = new ComputerDTO();
		try {

			computerDTO = computerService.findById(id);

		} catch (NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			logger.error("Erreur getComputerDTO", e);
		}
		return computerDTO;
	}

	public void deleteComputer(String id) {
		try {

			ComputerDTO computerDTOtoDelete = computerService.findById(id);
			computerService.delete(computerDTOtoDelete);

		} catch (InvalidDateValueException | NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("deleteComputer", e);
		}
	}

	public List<ComputerDTO> getComputerPage(int page,int nbComputerByPage) {
		List<ComputerDTO> computerDAOList = new ArrayList<ComputerDTO>();
		ComputerService computerService = ComputerService.getInstance();
		
		if (page != 1)
			page = page * 10 - 10;
		try {
			computerDAOList = computerService.getAll(nbComputerByPage, page);
		} catch (InvalidDateChronology e) {
			// TODO Auto-generated catch block
			logger.error("Date invalid controller", e);
		}
		return computerDAOList;

	}
	
	
	public List<ComputerDTO> getComputerPageOrder(int page,int nbComputerByPage,String orderParameter) {
		List<ComputerDTO> computerDAOList = new ArrayList<ComputerDTO>();
		ComputerService computerService = ComputerService.getInstance();
		
		if (page != 1)
			page = page * 10 - 10;
		try {
			computerDAOList = computerService.getAllOrderedBy(page,nbComputerByPage,orderParameter);
		} catch (InvalidDateChronology e) {
			// TODO Auto-generated catch block
			logger.error("Date invalid controller", e);
		}
		return computerDAOList;

	}

	public int getNbOfComputer() {

		return computerService.getNbOfComputer();
	}

	public List<CompanyDTO> getListCompany() {
		List<CompanyDTO> companyList;
		companyList = companyService.getAll(200, 1);
		return companyList;
	}

	public void updateComputer(ComputerDTO computerDTO) {
		if (computerValidator.validate(computerDTO) == true) {
		try {
			
			computerService.update(computerDTO);
		} catch (InvalidDateValueException | InvalidDateChronology e1) {

			logger.error("Date invalid", e1);
		}
		}
	}
	
	public void create (ComputerDTO computerDTO) {
		if (computerValidator.validate(computerDTO) == true) {
			
			try {
				long idCreate = computerService.create(computerDTO);
				logger.info("Ordinateur ajouter id = "+idCreate);
			} catch (InvalidDateValueException | InvalidDateChronology | NumberFormatException e) {
				logger.error(e.getMessage());
			}
			}
	}

	public List<ComputerDTO> search(String keyword) throws InvalidDateChronology {
		List<ComputerDTO> listComputerDTOs = computerService.search(keyword);

		return listComputerDTOs;

	}

	public void deleteComputerById(String id) {
		try {

			ComputerDTO computerDTOtoDelete = computerService.findById(id);
			computerService.delete(computerDTOtoDelete);

		} catch (InvalidDateValueException | NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			logger.error("Error delete computer by id", e);
		}
	}
		
	}


