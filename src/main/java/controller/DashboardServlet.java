package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import config.AppConfig;
import exception.InvalidDateChronology;
import model.Computer;
import persistence.CompanyDAO;
import service.ComputerService;


public class DashboardServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	static ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

	static ComputerService computerService = (ComputerService) ctx.getBean("ComputerService");

	static CompanyDAO companyDAO = (CompanyDAO) ctx.getBean("CompanyDAO");

	private int nbOfComputerByPage = 10;
	private int page = 1;
	private String orderParameter = "id";
	private int offset = 0;

	public DashboardServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Computer> listComputer = new ArrayList<Computer>();
		int nbOfComputer = 10;

		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		if (page != 1)
			offset = 10 * page - 10;

		if (request.getParameter("search") != null) {

			try {

				listComputer = computerService.search(request.getParameter("search"));
				request.setAttribute("ListComputer", listComputer);

				nbOfComputer = listComputer.size();
			} catch (InvalidDateChronology e) {
				logger.error("erreur create dashboard");
			}
		} else {

			if (request.getParameter("nbByPage") != null)
				nbOfComputerByPage = Integer.parseInt(request.getParameter("nbByPage"));

			if (request.getParameter("OrderBy") != null)
				orderParameter = request.getParameter("OrderBy");

			try {
				nbOfComputer = computerService.getNbOfComputer();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				listComputer = computerService.getAllOrderedBy(nbOfComputerByPage, page = page * 10 - 10,
						orderParameter);
			} catch (InvalidDateChronology e) {
				logger.error("Date invalid controller", e);
			}
			request.setAttribute("ListComputer", listComputer);

		}

		int lastPage = nbOfComputer / nbOfComputerByPage;

		request.setAttribute("page", request.getParameter("page"));
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("nbOfComputer", nbOfComputer);
		request.setAttribute("OrderBy", orderParameter);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
