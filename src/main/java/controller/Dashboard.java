package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import mapper.ComputerMapper;
import persistence.CompanyDAO;
import service.ComputerService;

@Controller
@RequestMapping(value = "/dashboard")
public class Dashboard {

	static Logger logger = LoggerFactory.getLogger(Dashboard.class);

	@Autowired
	ComputerService computerService;

	@Autowired
	CompanyDAO companyDAO;
	
	@Autowired
	ComputerMapper computerMapper;

	public Dashboard() {
	}
	
	@GetMapping
	public ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws InvalidDateValueException, InvalidDateChronology, SQLException, ComputerNotFoundException {

		for (int i = 623 ; i<=625 ; i++)
		computerService.delete(i);
		
		int nbOfComputerByPage = 10;
		int page = 1;
		String orderParameter = "id";
		int offset = 0;
		
        ModelAndView mv = new ModelAndView();

		List<ComputerDTO> listComputerDTO = new ArrayList<>();
		int nbOfComputer = 10;

		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		if (page != 1)
			offset = 10 * page - 10;

		if (request.getParameter("search") != null) {

			try {
				listComputerDTO = this.computerService.search(request.getParameter("search")).stream().map(this.computerMapper::modelToDto).collect(Collectors.toList());
				
				mv.getModel().put("ListComputer", listComputerDTO);

				nbOfComputer = listComputerDTO.size();
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
				e1.printStackTrace();
			}

			try {
				listComputerDTO = this.computerService.getAllOrderedBy(nbOfComputerByPage, page = page * 10 - 10,
						orderParameter).stream().map(this.computerMapper::modelToDto).collect(Collectors.toList());

			} catch (InvalidDateChronology e) {
				logger.error("Date invalid controller", e);
			}
			mv.getModel().put("ListComputer", listComputerDTO);
			
			

		}

		int lastPage = nbOfComputer / nbOfComputerByPage;
		
		mv.getModel().put("page", request.getParameter("page"));
		mv.getModel().put("lastPage", lastPage);
		mv.getModel().put("nbOfComputer", nbOfComputer);
		mv.getModel().put("OrderBy", orderParameter);


		
		return mv;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
