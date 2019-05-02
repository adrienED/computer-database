package servlet;

import java.io.IOException;
import java.util.HashMap;
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

		response.getWriter().append("Served at: ").append(request.getContextPath());

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/addComputer.jsp");

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
		inputsCreateComputer.put("computername", computerName);
		System.out.println(computerName);

		// get introducedDate

		String introduced = request.getParameter("introduced");
		inputsCreateComputer.put("introduced", introduced);

		// get discontinued

		String discontinued = request.getParameter("discontinued");
		inputsCreateComputer.put("discontinued", discontinued);

		// get idCompany

		String IdCompany = request.getParameter("companyId");
		inputsCreateComputer.put("idCompany", IdCompany);
		ComputerDTO computerDTO = new ComputerDTO();

		computerDTO.setName(inputsCreateComputer.get("computerName"));
		computerDTO.setIntroduced(inputsCreateComputer.get("introduced"));
		computerDTO.setDiscontinued(inputsCreateComputer.get("discontinued"));
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(inputsCreateComputer.get("idCompany"));
		computerDTO.setCompanyDTO(companyDTO);

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
