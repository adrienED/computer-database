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
import model.Computer;
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

		List<ComputerDTO> listComputer = new ArrayList<ComputerDTO>();
		int nbOfComputer;
		
		
		if (request.getParameter("search") != null) {
			nbOfComputer = listComputer.size();
			try {
				listComputer =controller.search(request.getParameter("search"));
				request.setAttribute("ListComputer", listComputer);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
				dispatcher.forward(request, response);
			} catch (InvalidDateChronology e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		nbOfComputer = controller.getNbOfComputer();
		listComputer = controller.getComputerPage(page);
		request.setAttribute("ListComputer", listComputer);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		dispatcher.forward(request, response);
		
		}
		
		String nbOfComputerByPageString = request.getParameter("nbOfComputerByPage");
		if(nbOfComputerByPageString!=null)
			nbOfComputerByPage = Integer.parseInt(nbOfComputerByPageString);

		String pageString = request.getParameter("page");
		if(pageString!=null)
			page = Integer.parseInt(pageString);


		int lastPage= nbOfComputer/nbOfComputerByPage;
		
		

		request.setAttribute("page", page);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("nbOfComputer", nbOfComputer );

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
	}

}
