package com.excilys.CDB.controller;

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

import com.excilys.CDB.binding.dto.CompanyDTO;
import com.excilys.CDB.binding.dto.ComputerDTO;
import com.excilys.CDB.binding.mapper.CompanyMapper;
import com.excilys.CDB.binding.mapper.ComputerMapper;
import com.excilys.CDB.binding.validator.ComputerValidator;
import com.excilys.CDB.core.exception.EmptyCompanyNameException;
import com.excilys.CDB.core.exception.EmptyComputerNameException;
import com.excilys.CDB.core.exception.InvalidDateChronology;
import com.excilys.CDB.core.exception.InvalidDateValueException;
import com.excilys.CDB.service.CompanyService;
import com.excilys.CDB.service.ComputerService;

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
/*
	@PostMapping
	public ModelAndView submit(@Validated @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result,
			ModelMap model) {

		ModelAndView mView = new ModelAndView();

		model.addAttribute("name", computerDTO.getName());
		model.addAttribute("introduced", computerDTO.getIntroduced());
		model.addAttribute("discontinued", computerDTO.getDiscontinued());
		model.addAttribute("companyName", computerDTO.getCompanyName());
		model.addAttribute("company_id", computerDTO.getCompany_id());


		try {
			computerValidator.validate(computerDTO);
			computerService.create(computerMapper.dtoToModel(computerDTO));
			return new ModelAndView("redirect:dashboard");

		} catch (InvalidDateChronology | InvalidDateValueException | EmptyComputerNameException
				| EmptyCompanyNameException e) {
			mView.setViewName("error");
			mView.getModel().put("errorMessage", e.getMessage());
			return mView;
		}

	}
	*/
	
	
	
	@PostMapping
	public String submit(@Validated @ModelAttribute("computerDTO") ComputerDTO computerDTO) {

		

	


		try {
			computerValidator.validate(computerDTO);
			computerService.create(computerMapper.dtoToModel(computerDTO));
			return "redirect:dashboard";

		} catch (InvalidDateChronology | InvalidDateValueException | EmptyComputerNameException
				| EmptyCompanyNameException e) {

			return "error";
		}

	}
	
	
	
	
}
