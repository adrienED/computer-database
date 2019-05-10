package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import mapper.ComputerMapper;
import model.Computer;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

@WebServlet("/addComputer")
public class addComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = LoggerFactory.getLogger(addComputer.class);

	ComputerValidator computerValidator = ComputerValidator.getInstance();
	ComputerMapper computerMapper = ComputerMapper.getInstance();

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
		Computer computer = new Computer();	

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

		if (computerValidator.validate(computerDTO) == true) {
			try {
				computer = computerMapper.dtoToModel(computerDTO);
				long idCreate = computerService.create(computer);
				logger.info("Ordinateur ajouter id = " + idCreate);
			} catch (InvalidDateValueException | InvalidDateChronology | NumberFormatException e) {
				logger.error(e.getMessage());
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/addComputer.jsp");

		dispatcher.forward(request, response);

	}
}
