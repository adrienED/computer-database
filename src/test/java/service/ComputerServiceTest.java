package service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exception.InvalidDateChronology;
import model.Company;
import model.Computer;
import persistence.ComputerDAO;

public class ComputerServiceTest {

	ComputerService computerService = null;

	ComputerDAO companyDAO = mock(ComputerDAO.class);

	@Test
	public void testFindById() {

	}

	@Test
	public void testGetAll() throws InvalidDateChronology {

		LocalDate introDate = LocalDate.parse("2016-12-12");
		LocalDate disconDate = LocalDate.parse("2017-12-12");

		Company company = new Company();
		company.setId(1L);
		company.setName("Apple Inc.");

		Computer computer = new Computer();

		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(introDate);
		computer.setDiscontinued(disconDate);
		computer.setCompany(company);

		List<Computer> listComputer = new ArrayList<>();
		listComputer.add(computer);

		Computer computer2 = new Computer();

		computer2.setId(24L);
		computer2.setName("test2");
		computer2.setIntroduced(introDate);
		computer2.setDiscontinued(disconDate);
		computer2.setCompany(company);

		listComputer.add(computer2);

		when(companyDAO.getAll(5, 10)).thenReturn(listComputer);

		assertEquals(listComputer, computerService.getAll(5, 10));
	}

}
