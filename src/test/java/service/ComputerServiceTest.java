package service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.NotFoundException;
import model.Company;
import model.Computer;
import persistence.ComputerDAO;

public class ComputerServiceTest {

	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	ComputerService computerService = (ComputerService) ctx.getBean("ComputerService");
	

	ComputerDAO computerDAO = mock(ComputerDAO.class);

	@Test
	public void testFindById() throws NotFoundException, InvalidDateChronology, ComputerNotFoundException {
		
		Computer computer = new Computer();
		computer.setId(12L);
		computer.setName("Apple III");
		computer.setIntroduced(LocalDate.parse("1980-05-01"));
		computer.setDiscontinued(LocalDate.parse("1984-04-01"));
		computer.setCompanyID(1L);
		System.out.println(computerService.findById("12"));
		
		assertEquals(computer, computerService.findById("12"));
	}

	@Test
	public void testGetAllInint() throws InvalidDateChronology {


		Company company = new Company();
		company.setId(1L);
		company.setName("Apple Inc.");

		Computer computer = new Computer();

		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(LocalDate.parse("2016-12-12"));
		computer.setDiscontinued(LocalDate.parse("2016-12-12"));
		computer.setCompanyID(36L);

		List<Computer> listComputerModel = new ArrayList<>();
		listComputerModel.add(computer);

		Computer computer2 = new Computer();

		computer2.setId(24L);
		computer2.setName("test2");
		computer2.setIntroduced(LocalDate.parse("2016-12-12"));
		computer2.setDiscontinued(LocalDate.parse("2016-12-12"));
		computer2.setCompanyID(36L);

		listComputerModel.add(computer2);
		
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId("1");
		companyDTO.setName("Apple Inc.");

		ComputerDTO computerDTO = new ComputerDTO();

		computerDTO.setId("23");
		computerDTO.setName("test");
		computerDTO.setIntroduced(LocalDate.parse("2016-12-12"));
		computerDTO.setDiscontinued(LocalDate.parse("2016-12-12"));
		computerDTO.setCompanyName("Apple Inc.");

		List<ComputerDTO> listComputerDTO = new ArrayList<>();
		listComputerDTO.add(computerDTO);

		ComputerDTO computerDTO2 = new ComputerDTO();
		computerDTO2.setName("test2");
		computerDTO2.setIntroduced(LocalDate.parse("2016-12-12"));
		computerDTO2.setDiscontinued(LocalDate.parse("2016-12-12"));
		computerDTO.setCompanyName("Apple Inc.");

		listComputerDTO.add(computerDTO2);


		when(computerDAO.getAll(2, 1)).thenReturn(listComputerModel);
		
		assertEquals(listComputerDTO, computerService.getAll(2, 1));
	}

}
