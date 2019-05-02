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

import dto.ComputerDTO;
import exception.InvalidDateChronology;
import persistence.ComputerDAO;
import service.ComputerService;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String numeroDePage = request.getParameter("page");
		System.out.println(numeroDePage);

		if (numeroDePage == null)
			numeroDePage = "2";

		request.setAttribute("page", numeroDePage);

		List<ComputerDTO> computerDAOList = new ArrayList<ComputerDTO>();
		ComputerService computerService = ComputerService.getInstance();
		try {
			computerDAOList = computerService.getAll(10, Integer.parseInt(numeroDePage));
		} catch (InvalidDateChronology e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		request.setAttribute("ListComputer", computerDAOList);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
