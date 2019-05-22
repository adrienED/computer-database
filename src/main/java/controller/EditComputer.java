package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import model.Computer;
import persistence.CompanyDAO;
import service.CompanyService;
import service.ComputerService;


@Controller
@RequestMapping(value = "/editComputer")
public class EditComputer{
	

	private final ComputerService computerService;
	
	private final CompanyDAO companyDAO ;
	
	private final ComputerMapper computerMapper ;
	
	private final CompanyService companyService ;
	
	private final CompanyMapper companyMapper;
	
	static Logger logger = LoggerFactory.getLogger(EditComputer.class);
	
	
	

	public EditComputer(ComputerService computerService, CompanyDAO companyDAO, ComputerMapper computerMapper,
			CompanyService companyService, CompanyMapper companyMapper) {
		super();
		this.computerService = computerService;
		this.companyDAO = companyDAO;
		this.computerMapper = computerMapper;
		this.companyService = companyService;
		this.companyMapper = companyMapper;
	}

	@GetMapping
	public ModelAndView doGet(HttpServletRequest request)
			throws ServletException, IOException {
		
        ModelAndView mv = new ModelAndView();
        
        
			
		ComputerDTO computerDTO = new ComputerDTO();

		try {

			computerDTO = computerMapper.modelToDto(computerService.findById(request.getParameter("id")));

		} catch (NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			logger.error("Erreur getComputerDTO", e);
		}
		List<CompanyDTO> companyList = this.companyService.getAll(100, 0).stream().map(this.companyMapper::modelToDto).collect(Collectors.toList());
				
		mv.getModel().put("computer", computerDTO);
	
		mv.getModel().put("listCompanies", companyList);
		
		
		return mv;

	}

	@PostMapping
	protected void doPost(HttpServletRequest request)
			throws ServletException, IOException {

		Computer computer;

		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(request.getParameter("id"));
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
		System.out.println(request.getParameter("companyName"));
		System.out.println(computerDTO);

		try {
		
				computer = computerMapper.dtoToModel(computerDTO);
				System.out.println(computer);
				computerService.update(computer);
			
		} catch (InvalidDateValueException | InvalidDateChronology | SQLException | ComputerNotFoundException e1) {
			logger.error("Date invalid", e1);
		}
	}
	

}
