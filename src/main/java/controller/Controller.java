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

	public void deleteComputerById(String id) {
		try {

			ComputerDTO computerDTOtoDelete = computerService.findById(id);
			computerService.delete(computerDTOtoDelete);

		} catch (InvalidDateValueException | NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			logger.error("Error delete computer by id", e);
		}
	}
	
	public void deleteCompanyById(long id) {
		companyService.deleteCompanyById(id);
	}
	


}
