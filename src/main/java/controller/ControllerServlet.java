package controller;

import java.io.IOException;
import java.sql.SQLException;
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

		computerDAO = ComputerDAO.getInstance();
		computerService = ComputerService.getInstance();

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
				insertComputer(request, response);
				break;
			case "/edit":
				editComputer(request, response);
				break;
			case "/showByName":
				deleteComputer(request, response);
				break;
			case "/delete":
				showEditForm(request, response);
				break;
			default:
				listComputer(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	
	
	private void listComputers(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean returnMenu = false;
			int n = 1;
			while (!returnMenu) {
				List<ComputerDTO> computerDAOList;
				computerDAOList = computerService.getAll(10, n);
				
				for (ComputerDTO computerDTO : computerDAOList) {
					System.out.println(computerDTO);
				}
				vue.menuNextPage();
				String choix2 = vue.readInputs();
				switch (choix2) {
				// retour
				case "0":
					returnMenu = true;
					break;
				// page suivante
				case "1":
					n += 10;
					break;
				// page precedente
				case "2":
					n -= 10;
					break;
				}
			}

		} catch (InvalidDateChronology e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
