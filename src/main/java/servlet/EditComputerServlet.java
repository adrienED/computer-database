package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dto.CompanyDTO;
import dto.ComputerDTO;

@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controller controller = new Controller();

	public EditComputerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComputerDTO computerDTO = new ComputerDTO();

		computerDTO = controller.getComputerDTOById(request.getParameter("id"));

		List<CompanyDTO> listCompanyDTO = controller.getListCompany();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/editComputer.jsp");
		request.setAttribute("computer", computerDTO);
		System.out.println(computerDTO.getName());
		request.setAttribute("listCompanies", listCompanyDTO);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(request.getParameter("id"));
		computerDTO.setName(request.getParameter("computerName").toString());
		computerDTO.setIntroduced(request.getParameter("Introduced").toString());
		computerDTO.setDiscontinued(request.getParameter("Discontinued").toString());
		computerDTO.setCompanyName(request.getParameter("companyName").toString());

		controller.updateComputer(computerDTO);

	}
}
