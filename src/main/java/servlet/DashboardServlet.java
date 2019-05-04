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
	
	private int nbOfComputerByPage = 10;
	private int page = 1;

	
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nbOfComputerByPageString = request.getParameter("nbOfComputerByPage");
		if(nbOfComputerByPageString!=null)
			nbOfComputerByPage = Integer.parseInt(nbOfComputerByPageString);
		
		int nbOfComputer = computerService.getNbOfComputer();

		String pageString = request.getParameter("page");
		if(pageString!=null)
			page = Integer.parseInt(pageString);


		int lastPage= nbOfComputer/nbOfComputerByPage;


		List<ComputerDTO> computerDAOList = controller.getComputerPage(page);
		
		
		
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		request.setAttribute("page", page);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("nbOfComputer", nbOfComputer);
		request.setAttribute("ListComputer", computerDAOList);
		
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
