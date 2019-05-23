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

	private final ComputerService computerService;
	
	private final CompanyDAO companyDAO;
	
	private final ComputerMapper computerMapper;	
	
	public Dashboard(ComputerService computerService, CompanyDAO companyDAO, ComputerMapper computerMapper) {
		super();
		this.computerService = computerService;
		this.companyDAO = companyDAO;
		this.computerMapper = computerMapper;
	}

	@GetMapping
	public ModelAndView doGet(HttpServletRequest request) throws InvalidDateValueException, InvalidDateChronology, SQLException, ComputerNotFoundException {

		int nbOfComputerByPage;
		String orderParameter;
		if(request.getParameter("nbOfComputerByPage")!=null)
			nbOfComputerByPage = Integer.parseInt(request.getParameter("nbOfComputerByPage"));
		else nbOfComputerByPage = 10;
		if (request.getParameter("orderBy") != null)
			orderParameter = request.getParameter("orderBy");
		else orderParameter = "id";
		int page = 1;
		
        ModelAndView mv = new ModelAndView();

		List<ComputerDTO> listComputerDTO = new ArrayList<>();
		int nbOfComputer = 10;

		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));


		if (request.getParameter("search") != null) {

			try {
				listComputerDTO = this.computerService.search(request.getParameter("search")).stream().map(this.computerMapper::modelToDto).collect(Collectors.toList());
				
				mv.getModel().put("ListComputer", listComputerDTO);

				nbOfComputer = listComputerDTO.size();
			} catch (InvalidDateChronology e) {
				logger.error("erreur create dashboard");
			}
		} else {

			if (request.getParameter("orderBy") != null)
				orderParameter = request.getParameter("orderBy");

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
		mv.getModel().put("nbOfComputerByPage", nbOfComputerByPage);

		
		return mv;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
