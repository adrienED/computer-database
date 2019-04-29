package controller;

import java.io.IOException;
import java.sql.SQLException;
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


@WebServlet("/")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ComputerDAO computerDAO;
	private ComputerService computerService;

	public void init() {

		//computerDAO = ComputerDAO.getInstance();
		//computerService = ComputerService.getInstance();

	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/add":
				addComputer(request, response);
				break;
			case "/dashboard":
				dashboard(request, response);
				break;
			}
		}
		 catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	
	
	
	private void addComputer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/addComputer.jsp");
		dispatcher.forward(request, response);
	}
	
	private void dashboard(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
			List<ComputerDTO> computerDAOList = new ArrayList<ComputerDTO>();
		
		try {
			
				
				computerDAOList = computerService.getAll(10, 1);
	
				}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		request.setAttribute("ComputerList", computerDAOList);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		dispatcher.forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
