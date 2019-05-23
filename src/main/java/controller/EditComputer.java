package controller;

import java.sql.SQLException;
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

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.EmptyCompanyNameException;
import exception.EmptyComputerNameException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

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

		try {

			computerDTO = computerMapper.modelToDto(computerService.findById(request.getParameter("id")));

		} catch (NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
			logger.error("Erreur getComputerDTO", e);
		}
		List<CompanyDTO> companyList = this.companyService.getAll().stream().map(this.companyMapper::modelToDto)
				.collect(Collectors.toList());

		mv.getModel().put("computer", computerDTO);

		mv.getModel().put("listCompanies", companyList);

		return mv;

	}

	@PostMapping
	public ModelAndView submit(@Validated @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result,
			ModelMap model) throws SQLException, ComputerNotFoundException {

		ModelAndView mView = new ModelAndView();

		model.addAttribute("id", computerDTO.getId());
		model.addAttribute("name", computerDTO.getName());
		model.addAttribute("introduced", computerDTO.getIntroduced());
		model.addAttribute("discontinued", computerDTO.getDiscontinued());
		model.addAttribute("companyName", computerDTO.getCompanyName());

		try {
			computerValidator.validate(computerDTO);
			computerService.update(computerMapper.dtoToModel(computerDTO));
			mView.setViewName("redirect:dashboard");
			return mView;

		} catch (InvalidDateValueException | InvalidDateChronology | EmptyCompanyNameException
				| EmptyComputerNameException e) {
			mView.setViewName("error");
			mView.getModel().put("errorMessage", e.getMessage());
			return mView;
		}

	}

}
