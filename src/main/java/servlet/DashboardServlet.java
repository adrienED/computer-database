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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Config.AppConfig;

import dto.ComputerDTO;
import exception.InvalidDateChronology;
import persistence.CompanyDAO;
import service.ComputerService;

@Controller
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	private ComputerService computerService = ctx.getBean(ComputerService.class);

	static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	private int nbOfComputerByPage = 10;
	private int page = 1;
	private String orderParameter = "id";
	private int offset=1;

	public DashboardServlet() {
	}
	@GetMapping("/dashboard")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ComputerDTO> listComputer = new ArrayList<ComputerDTO>();
		int nbOfComputer = 10;
		
		if( request.getParameter("page") != null)
		page = Integer.parseInt(request.getParameter("page"));
		
		if (page !=1)
		offset=10*page-10;
		

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

			nbOfComputer = computerService.getNbOfComputer();
			

			try {
				listComputer = computerService.getAllOrderedBy( nbOfComputerByPage,page = page * 10 - 10,orderParameter);
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
