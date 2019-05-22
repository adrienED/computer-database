package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.IntStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import mapper.ComputerMapper;
import model.Computer;
import persistence.CompanyDAO;
import service.ComputerService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputer {
	
	
	private final ComputerService computerService;
	
	private final CompanyDAO companyDAO;
	
	private final ComputerMapper computerMapper;

	static Logger logger = LoggerFactory.getLogger(DeleteComputer.class);

	

	public DeleteComputer(ComputerService computerService, CompanyDAO companyDAO, ComputerMapper computerMapper) {
		super();
		this.computerService = computerService;
		this.companyDAO = companyDAO;
		this.computerMapper = computerMapper;
	}



	@PostMapping
	protected void doPost(HttpServletRequest request)
			throws ServletException, IOException {

		String listDelete = request.getParameter("selection");

		String[] list = listDelete.split(",");
		System.out.println(listDelete);

		IntStream.range(0, list.length).forEach(i -> {
			try {
				computerService.delete(computerMapper.idToLong(list[i]));

			} catch (InvalidDateValueException
					| ComputerNotFoundException | SQLException | InvalidDateChronology e) {
				logger.error("Error delete computer by id", e);
			}
		});
	}
}
