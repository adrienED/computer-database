package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.ComputerDTO;
import mapper.ComputerMapper;
import service.CompanyService;
import service.ComputerService;

@Controller
@RequestMapping(value = "/dashboard")
public class Dashboard {

	static Logger logger = LoggerFactory.getLogger(Dashboard.class);

	@Autowired
	CompanyService companyService;

	private final ComputerService computerService;

	private final ComputerMapper computerMapper;

	public Dashboard(ComputerService computerService, ComputerMapper computerMapper) {
		super();
		this.computerService = computerService;
		this.computerMapper = computerMapper;
	}

	@GetMapping
	public ModelAndView doGet(HttpServletRequest request) {

		int page = 1;
		int nbOfComputer = computerService.getNbOfComputer();
		int nbOfComputerByPage;
		String orderParameter = "id";
		String AS = "ASC";
		if (request.getParameter("nbOfComputerByPage") != null)
			nbOfComputerByPage = Integer.parseInt(request.getParameter("nbOfComputerByPage"));
		else
			nbOfComputerByPage = 10;

		if (request.getParameter("orderBy") != null) {
			orderParameter = request.getParameter("orderBy");
			if (orderParameter.equals("company"))
				orderParameter = "B.name";
		}

		if (request.getParameter("AS") != null) {
			AS = request.getParameter("AS");
		}

		PageRequest pageRequest;

		ModelAndView mv = new ModelAndView();

		List<ComputerDTO> listComputerDTO = new ArrayList<>();

		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		if (request.getParameter("search") != null) {

			if (AS.equals("DESC"))
				pageRequest = PageRequest.of(page - 1, nbOfComputerByPage, Sort.by(orderParameter).descending());
			else
				pageRequest = PageRequest.of(page - 1, nbOfComputerByPage, Sort.by(orderParameter));

			listComputerDTO = this.computerService.search(request.getParameter("search"), pageRequest).stream()
					.map(this.computerMapper::modelToDto).collect(Collectors.toList());

			mv.getModel().put("ListComputer", listComputerDTO);

			nbOfComputer = listComputerDTO.size();

		} else {

			if (AS.equals("DESC"))
				pageRequest = PageRequest.of(page - 1, nbOfComputerByPage, Sort.by(orderParameter).descending());
			else
				pageRequest = PageRequest.of(page - 1, nbOfComputerByPage, Sort.by(orderParameter));

			listComputerDTO = this.computerService.findAll(pageRequest).stream().map(this.computerMapper::modelToDto)
					.collect(Collectors.toList());

			mv.getModel().put("ListComputer", listComputerDTO);
		}

		int lastPage = nbOfComputer / nbOfComputerByPage;

		mv.getModel().put("page", request.getParameter("page"));
		mv.getModel().put("nbOfComputer", nbOfComputer);
		mv.getModel().put("lastPage", lastPage);
		mv.getModel().put("OrderBy", orderParameter);
		mv.getModel().put("nbOfComputerByPage", nbOfComputerByPage);

		return mv;
	}
}
