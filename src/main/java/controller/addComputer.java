package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.EmptyCompanyNameException;
import exception.EmptyComputerNameException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

@Controller
@RequestMapping(value = "/addComputer")
public class addComputer {

	static Logger logger = LoggerFactory.getLogger(addComputer.class);

	private final ComputerMapper computerMapper;

	private final CompanyService companyService;

	private final ComputerService computerService;

	private final ComputerValidator computerValidator;

	private final CompanyMapper companyMapper;

	public addComputer(ComputerMapper computerMapper, CompanyService companyService, ComputerService computerService,
			ComputerValidator computerValidator, CompanyMapper companyMapper) {
		this.computerMapper = computerMapper;
		this.companyService = companyService;
		this.computerService = computerService;
		this.computerValidator = computerValidator;
		this.companyMapper = companyMapper;
	}

	@GetMapping
	public ModelAndView listCompanies() {
		ModelAndView mv = new ModelAndView("addComputer", "computerDTO", new ComputerDTO());

		List<CompanyDTO> listCompanies = this.companyService.getAll().stream().map(this.companyMapper::modelToDto)
				.collect(Collectors.toList());

		mv.getModel().put("ListCompanies", listCompanies);

		return mv;
	}

	@PostMapping
	public ModelAndView submit(@Validated @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result,
			ModelMap model) {

		ModelAndView mView = new ModelAndView();

		model.addAttribute("name", computerDTO.getName());
		model.addAttribute("introduced", computerDTO.getIntroduced());
		model.addAttribute("discontinued", computerDTO.getDiscontinued());
		model.addAttribute("companyName", computerDTO.getCompanyName());

		try {
			computerValidator.validate(computerDTO);
			computerService.create(computerMapper.dtoToModel(computerDTO));
			return new ModelAndView("redirect:dashboard");

		} catch (EmptyComputerNameException | EmptyCompanyNameException | InvalidDateChronology
				| InvalidDateValueException | SQLException e) {
			mView.setViewName("error");
			mView.getModel().put("errorMessage", e.getMessage());
			return mView;
		}

	}
}
