package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId("12");
		computerDTO.setName("Apple III");
		computerDTO.setIntroduced(LocalDate.parse("1980-05-01"));
		computerDTO.setDiscontinued(LocalDate.parse("1984-04-01"));
		computerDTO.setCompanyName("Apple Inc.");
		System.out.println(computerService.findById("12"));
		
		assertEquals(computerDTO, computerService.findById("12"));
	}

	@Test
	public void testGetAllInint() throws InvalidDateChronology {


		Company company = new Company.Builder().
		withParameter(1L, "Apple Inc.")
		.build();

		Computer computer = new Computer.Builder()
		.withId(23L)
		.withName("Sony")
		.withIntroduced(LocalDate.parse("2016-12-12"))
		.withDiscontinued(LocalDate.parse("2016-12-12"))
		.withCompanyID(36L)
		.build();

		List<Computer> listComputerModel = new ArrayList<>();
		listComputerModel.add(computer);

		Computer computer2 = new Computer.Builder()
				.withId(23L)
				.withName("Sony")
				.withIntroduced(LocalDate.parse("2016-12-12"))
				.withDiscontinued(LocalDate.parse("2016-12-12"))
				.withCompanyID(36L)
				.build();

		listComputerModel.add(computer2);
		
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId("1");
		companyDTO.setName("Apple Inc.");

		ComputerDTO computerDTO = new ComputerDTO();

		computerDTO.setId("78");
		computerDTO.setName("Macintosh 512K");
		computerDTO.setIntroduced(LocalDate.parse("1984-09-10"));
		computerDTO.setDiscontinued(LocalDate.parse("1986-04-14"));
		computerDTO.setCompanyName("Apple Inc.");

		List<ComputerDTO> listComputerDTO = new ArrayList<>();
		listComputerDTO.add(computerDTO);

		ComputerDTO computerDTO2 = new ComputerDTO();
		computerDTO2.setId("79");
		computerDTO2.setName("Macintosh SE");
		computerDTO2.setIntroduced(LocalDate.parse("1987-03-02"));
		computerDTO2.setDiscontinued(LocalDate.parse("1989-08-01"));
		computerDTO2.setCompanyName("Apple Inc.");

		listComputerDTO.add(computerDTO2);
		
		assertEquals(listComputerDTO, computerService.getAll(2, 77));
	}

}
