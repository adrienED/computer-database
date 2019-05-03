package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import persistence.ComputerDAO;
import service.ComputerService;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Controller controller = new Controller();
	ComputerService computerService = ComputerService.getInstance();

	
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int nbOfComputer = computerService.getNbOfComputer();

		String numeroDePage = request.getParameter("page");
		System.out.println(numeroDePage);

		if (numeroDePage == null)
			numeroDePage = "1";

		request.setAttribute("page", numeroDePage);

		List<ComputerDTO> computerDAOList = controller.getComputerPage(numeroDePage);
		
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		request.setAttribute("nbOfComputer", nbOfComputer);
		request.setAttribute("ListComputer", computerDAOList);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
