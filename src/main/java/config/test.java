package config;

import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import model.Company;
import model.Computer;
import persistence.CompanyDAO;
import persistence.ComputerDAO;
import validator.ComputerValidator;

public class test {

	static ApplicationContext ctx = new AnnotationConfigApplicationContext(WebConfig.class);

	ComputerValidator computerValidator = (ComputerValidator) ctx.getBean("ComputerValidator");

	ComputerMapper computerMapper = (ComputerMapper) ctx.getBean("ComputerMapper");

	static ComputerDAO computerDAO = (ComputerDAO) ctx.getBean("ComputerDAO");

	static CompanyDAO companyDAO = (CompanyDAO) ctx.getBean("CompanyDAO");
	
	static CompanyMapper companyMapper = (CompanyMapper) ctx.getBean(CompanyMapper.class);


	public static void main(String[] args) throws ComputerNotFoundException, InvalidDateChronology, SQLException {
		System.out.println(companyDAO.getAll());

		System.out.println(companyDAO.findById(13L));

		System.out.println(companyDAO.findByName("Apple Inc."));

		// System.out.println(companyDAO.getAll(10, 1));

		Computer computer = new Computer.Builder().withId(620L).withName("poghyujg")

				.withDiscontinued(LocalDate.parse("2017-12-12")).withCompanyID(1L).build();

		try {
			computerDAO.update(computer);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.out.println(computerDAO.getSearchComputer("macbook"));
		System.out.println(computerDAO.findById(24L));

		System.out.println(computerDAO.getAllOrderedBy(5, 0, "nameDESC"));

		System.out.println(computerDAO.getAllOrderedBy(10, 0, "nameDESC"));

		System.out.print(computerDAO.getSearchComputer("Apple"));

		System.out.println(computerDAO.findById(25));

		computerDAO.delete(580);
		
	}
}
