package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import mapper.ComputerMapper;
import model.Company;
import model.Computer;
import service.CompanyService;
import service.ComputerService;

@Controller
@RequestMapping(value = "/addComputer")
public class addComputer  {

	static Logger logger = LoggerFactory.getLogger(addComputer.class);


	@Autowired
	ComputerMapper computerMapper ;
	@Autowired
	CompanyService companyService ;
	@Autowired
	ComputerService computerService;


	public addComputer() {
	}

	
	@GetMapping
	public ModelAndView listCompanies() {
        ModelAndView mv = new ModelAndView();
        List<Company> listCompanies = new ArrayList<Company>();
		listCompanies = companyService.getAll(100, 0);

        mv.setViewName("addComputer");
        mv.getModel().put("ListCompanies", listCompanies);
         
        return mv;
	}
	
	
	@PostMapping
	public void insertion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComputerDTO computerDTO = new ComputerDTO();
		Computer computer;

		computerDTO.setName(request.getParameter("computerName"));
		if (request.getParameter("introduced") == null)
			computerDTO.setIntroduced(null);
		else {
			try {
				computerDTO.setIntroduced(request.getParameter("introduced"));
			} catch (Exception e) {
				logger.error("erreur parse introduced");
			}
		}

		if (request.getParameter("discontinued") == null)
			computerDTO.setDiscontinued(null);
		else {
			try {
				computerDTO.setDiscontinued(request.getParameter("discontinued"));
			} catch (Exception e) {
				logger.error("erreur parse discontinued");
			}
		}

		computerDTO.setCompanyName(request.getParameter("companyName"));

		System.out.println(computerDTO);

		try {
	
				computer = computerMapper.dtoToModel(computerDTO);
				System.out.println(computer);
				long idCreate = computerService.create(computer);
				logger.info("Ordinateur ajouter id = " + idCreate);
			
		} catch (InvalidDateValueException | InvalidDateChronology | NumberFormatException | SQLException e) {
			logger.error(e.getMessage());
		}
		


	}
	
}
