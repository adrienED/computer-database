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

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Company;
import service.CompanyService;
import service.ComputerService;

/**
 * Servlet implementation class addComputer
 */
@WebServlet("/addComputer")
public class addComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		CompanyService companyService = CompanyService.getInstance();

	List<CompanyDTO> ListCompanies = new ArrayList<CompanyDTO>();
	ListCompanies = companyService.getAll(10, 1);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/addComputer.jsp");
		request.setAttribute("ListCompanies", ListCompanies);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> inputsCreateComputer = new HashMap<String, String>();
		ComputerService computerService = ComputerService.getInstance();

		// get name
		String computerName = request.getParameter("computerName");
		inputsCreateComputer.put("computerName", computerName);
		System.out.println(computerName);

		// get introducedDate

		String introduced = request.getParameter("introduced");
		inputsCreateComputer.put("introduced", introduced);

		// get discontinued

		String discontinued = request.getParameter("discontinued");
		inputsCreateComputer.put("discontinued", discontinued);

		// get idCompany

		String companyName = request.getParameter("companyName");
		inputsCreateComputer.put("companyName", companyName);
		ComputerDTO computerDTO = new ComputerDTO();

		computerDTO.setName(inputsCreateComputer.get("computerName"));
		computerDTO.setIntroduced(inputsCreateComputer.get("introduced"));
		computerDTO.setDiscontinued(inputsCreateComputer.get("discontinued"));
		computerDTO.setCompanyName(inputsCreateComputer.get("companyName"));

		System.out.println(computerDTO);

		try {
			long idCreate = computerService.create(computerDTO);
			System.out.println("Ordinateur creer avec l'id : " + idCreate);
		} catch (InvalidDateValueException | InvalidDateChronology | NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/DashboardServlet");

		dispatcher.forward(request, response);

	}
}
