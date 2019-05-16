package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import mapper.ComputerMapper;
import model.Computer;
import persistence.CompanyDAO;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	static ComputerService computerService = (ComputerService) ctx.getBean("ComputerService");
	static CompanyDAO companyDAO = (CompanyDAO) ctx.getBean("CompanyDAO");
	static ComputerMapper computerMapper = (ComputerMapper) ctx.getBean("ComputerMapper");
	static ComputerValidator computerValidator = (ComputerValidator) ctx.getBean("ComputerValidator");
	static CompanyService companyService = (CompanyService) ctx.getBean("CompanyService");

	static Logger logger = LoggerFactory.getLogger(EditComputerServlet.class);

	public EditComputerServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComputerDTO computerDTO = new ComputerDTO();
		

		try {

			computerDTO = computerService.findById(request.getParameter("id"));
			
		} catch (NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			logger.error("Erreur getComputerDTO", e);
		}

		List<CompanyDTO> listCompanyDTO = companyService.getAll(100, 0);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/editComputer.jsp");
		request.setAttribute("computer", computerDTO);
		request.setAttribute("listCompanies", listCompanyDTO);
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Computer computer;

		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(request.getParameter("id"));
		computerDTO.setName(request.getParameter("computerName"));
		if (request.getParameter("introduced") ==null)computerDTO.setIntroduced(null);
		else {
			try {
				computerDTO.setIntroduced(LocalDate.parse(request.getParameter("introduced")));
			}
			catch (Exception e) {
				logger.error("erreur parse introduced");
			}
		}
		
		if (request.getParameter("discontinued") ==null)computerDTO.setDiscontinued(null);
		else {
			try {
				computerDTO.setDiscontinued(LocalDate.parse(request.getParameter("discontinued")));
			}
			catch (Exception e) {
				logger.error("erreur parse discontinued");
			}
		}
		computerDTO.setCompanyName(request.getParameter("companyName"));

		
			try {
				if (computerValidator.validate(computerDTO)) {
				computer = computerMapper.dtoToModel(computerDTO);
				computerService.update(computer);
				}
			} catch (InvalidDateValueException | InvalidDateChronology e1) {
				logger.error("Date invalid", e1);
			}
		}

	}

