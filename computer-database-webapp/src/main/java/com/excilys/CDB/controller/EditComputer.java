package com.excilys.CDB.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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
import com.excilys.CDB.core.exception.ComputerNotFoundException;
import com.excilys.CDB.core.exception.EmptyCompanyNameException;
import com.excilys.CDB.core.exception.EmptyComputerNameException;
import com.excilys.CDB.core.exception.InvalidDateChronology;
import com.excilys.CDB.core.exception.InvalidDateValueException;
import com.excilys.CDB.core.model.Computer;
import com.excilys.CDB.service.CompanyService;
import com.excilys.CDB.service.ComputerService;

@Controller
@RequestMapping(value = "/editComputer")
public class EditComputer {

	private final ComputerService computerService;

	private final ComputerMapper computerMapper;

	private final CompanyService companyService;

	private final CompanyMapper companyMapper;

	private final ComputerValidator computerValidator;

	static Logger logger = LoggerFactory.getLogger(EditComputer.class);

	public EditComputer(ComputerService computerService, ComputerMapper computerMapper, CompanyService companyService,
			CompanyMapper companyMapper, ComputerValidator computerValidator) {
		super();
		this.computerService = computerService;
		this.computerMapper = computerMapper;
		this.companyService = companyService;
		this.companyMapper = companyMapper;
		this.computerValidator = computerValidator;
	}

	@GetMapping
	public ModelAndView doGet(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("editComputer", "computerDTO", new ComputerDTO());

		ComputerDTO computerDTO = new ComputerDTO();

		Computer computer = computerService.findById(request.getParameter("id")).get();
		
		computerDTO = computerMapper.modelToDto(computer);
		List<CompanyDTO> companyList = this.companyService.getAll().stream().map(this.companyMapper::modelToDto)
				.collect(Collectors.toList());

		mv.getModel().put("computer", computerDTO);

		mv.getModel().put("listCompanies", companyList);

		return mv;
		
	}

	@PostMapping
	public ModelAndView submit(@Validated @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result,
			ModelMap model) throws ComputerNotFoundException {

		ModelAndView mView = new ModelAndView();

		model.addAttribute("id", computerDTO.getId());
		model.addAttribute("name", computerDTO.getName());
		model.addAttribute("introduced", computerDTO.getIntroduced());
		model.addAttribute("discontinued", computerDTO.getDiscontinued());
		model.addAttribute("companyName", computerDTO.getCompanyName());
		model.addAttribute("company_id", computerDTO.getCompany_id());
		
		System.out.println(computerDTO);

		try {
			computerValidator.validate(computerDTO);
			computerService.update(computerMapper.dtoToModel(computerDTO));
			mView.setViewName("redirect:dashboard");
			return mView;

		} catch (InvalidDateValueException | InvalidDateChronology | EmptyComputerNameException | EmptyCompanyNameException e) {
			mView.setViewName("error");
			mView.getModel().put("errorMessage", e.getMessage());
			return mView;
		}
	}
}

