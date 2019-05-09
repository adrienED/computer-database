package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Company;
import persistence.ConnectionDAO;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

/**
 * Servlet implementation class addComputer
 */
@WebServlet("/addComputer")
public class addComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = LoggerFactory.getLogger(addComputer.class);

	ComputerValidator computerValidator = ComputerValidator.getInstance();

	public addComputer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CompanyService companyService = CompanyService.getInstance();

		List<CompanyDTO> ListCompanies = new ArrayList<CompanyDTO>();
		ListCompanies = companyService.getAll(100, 1);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/addComputer.jsp");
		request.setAttribute("ListCompanies", ListCompanies);
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComputerService computerService = ComputerService.getInstance();
		ComputerDTO computerDTO = new ComputerDTO();

		computerDTO.setName(request.getParameter("computerName"));
		computerDTO.setIntroduced(request.getParameter("introduced"));
		computerDTO.setDiscontinued(request.getParameter("discontinued"));
		computerDTO.setCompanyName(request.getParameter("companyName"));

		if (computerValidator.validate(computerDTO) == true) {

			try {
				long idCreate = computerService.create(computerDTO);
				logger.info("Ordinateur ajouter id = " + idCreate);
			} catch (InvalidDateValueException | InvalidDateChronology | NumberFormatException e) {
				logger.error(e.getMessage());
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/addComputer.jsp");

		dispatcher.forward(request, response);

	}
}
