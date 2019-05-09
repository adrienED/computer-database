package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import mapper.ComputerMapper;
import model.Computer;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controller controller = new Controller();
	ComputerValidator computerValidator=ComputerValidator.getInstance();
	ComputerService computerService = ComputerService.getInstance();
	ComputerMapper computerMapper = ComputerMapper.getInstance();
	CompanyService companyService = CompanyService.getInstance();
	
	static Logger logger = LoggerFactory.getLogger(EditComputerServlet.class);
	
	public EditComputerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComputerDTO computerDTO = new ComputerDTO();

		try {

			computerDTO = computerService.findById(request.getParameter("id"));

		} catch (NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			logger.error("Erreur getComputerDTO", e);
		}


		List<CompanyDTO> listCompanyDTO = companyService.getAll(100,1);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/editComputer.jsp");
		request.setAttribute("computer", computerDTO);
		System.out.println(computerDTO.getName());
		request.setAttribute("listCompanies", listCompanyDTO);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Computer computer = new Computer();

		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(request.getParameter("id"));
		computerDTO.setName(request.getParameter("computerName").toString());
		computerDTO.setIntroduced(request.getParameter("Introduced").toString());
		computerDTO.setDiscontinued(request.getParameter("Discontinued").toString());
		computerDTO.setCompanyName(request.getParameter("companyName").toString());
		
		
		if (computerValidator.validate(computerDTO) == true)
		{ 
			try {
				computer = computerMapper.dtoToModel(computerDTO);
				computerService.update(computer);
			} catch (InvalidDateValueException | InvalidDateChronology e1) {
				logger.error("Date invalid", e1);
			}
		}

	}
}
