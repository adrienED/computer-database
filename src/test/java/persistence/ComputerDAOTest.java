package persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import dto.CompanyDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import model.Company;
import model.Computer;

public class ComputerDAOTest {
	
	
	ConnectionDAO connectionDAO = new ConnectionDAO();
	
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	ComputerDAO computerDAO = (ComputerDAO) ctx.getBean("ComputerDAO");

	
	ResultSet resulSetMock = mock(ResultSet.class);

	@Before
	public void setUp() throws Exception {
		

	}
	

	@Test
	public void testPopulate() throws SQLException, InvalidDateChronology {
		
		Date date = Date.valueOf("2016-12-12");
		Date date2 = Date.valueOf("2017-12-12");

		when (resulSetMock.getLong("id")).thenReturn(23L);
		when (resulSetMock.getString("name")).thenReturn("IBM");
		when (resulSetMock.getDate("introduced")).thenReturn(date);
		when (resulSetMock.getDate("discontinued")).thenReturn(date2);
		when (resulSetMock.getLong("company_id")).thenReturn(1L);
		
		
		Computer computer = new Computer.Builder()
				.withId(23L)
				.withName("IBM")
				.withIntroduced(LocalDate.parse("2016-12-12"))
				.withDiscontinued(LocalDate.parse("2017-12-12"))	
				.withCompanyID(1L)
				.build();
		
		assertEquals(computer, computerDAO.populate(resulSetMock));
	}

	@Test
	public void testGetAllIntInt() {
		
		Computer computerRef = new Computer.Builder()
				.withId(23L)
				.withName("Macintosh Plus")
				.withIntroduced(LocalDate.parse("1986-01-16"))
				.withDiscontinued(LocalDate.parse("1990-10-15"))	
				.withCompanyID(1L)
				.build();


	}


	@Test
	public void testFindById() throws ComputerNotFoundException, InvalidDateChronology {

		Computer computer;
		
		computer = computerDAO.findById(23L);
		
		Computer computerRef = new Computer.Builder()
		.withId(23L)
		.withName("Macintosh Plus")
		.withIntroduced(LocalDate.parse("1986-01-16"))
		.withDiscontinued(LocalDate.parse("1990-10-15"))	
		.withCompanyID(1)
		.build();
		
		
		assertEquals(computerRef, computer);
	}


}
