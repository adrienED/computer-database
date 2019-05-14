package persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	public void testPopulate() throws SQLException {
		
		LocalDate date = LocalDate.parse("2018-12-12");


		when (resulSetMock.getLong("id")).thenReturn(13L);
		when (resulSetMock.getString("name")).thenReturn("IBM");
		//when (resulSetMock.getDate("introduced")).thenReturn(date);
		//when (resulSetMock.getDate("discontinued")).thenReturn(2018-12-12);
		when (resulSetMock.getLong("company_id")).thenReturn(36L);
		
		
		Computer computer = new Computer();	
		
		LocalDate.parse("2016-12-12");
		LocalDate.parse("2017-12-12");		
		
		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(LocalDate.parse("2016-12-12"));
		computer.setDiscontinued(LocalDate.parse("2017-12-12"));	
		computer.setCompanyID(1L);
		
		//assertEquals(computer, computerDAO.populate(resulSetMock));
	}

	@Test
	public void testGetAllIntInt() {
		
		Computer computer = new Computer();
		
		computer.setId(23L);
		computer.setName("Macintosh Plus");
		computer.setIntroduced(LocalDate.parse("1986-01-16"));
		computer.setDiscontinued(LocalDate.parse("1990-10-15"));	
		computer.setCompanyID(0);
		
		Computer computerRef = new Computer();
		
		computerRef.setId(23L);
		computerRef.setName("Macintosh Plus");
		computerRef.setIntroduced(LocalDate.parse("1986-01-16"));
		computerRef.setDiscontinued(LocalDate.parse("1990-10-15"));	
		computerRef.setCompanyID(0);
	}


	@Test
	public void testFindById() throws ComputerNotFoundException, InvalidDateChronology {

		Computer computer = new Computer();
		
		computer = computerDAO.findById(23L);
		
		Computer computerRef = new Computer();
		
		computerRef.setId(23L);
		computerRef.setName("Macintosh Plus");
		computerRef.setIntroduced(LocalDate.parse("1986-01-16"));
		computerRef.setDiscontinued(LocalDate.parse("1990-10-15"));	
		computerRef.setCompanyID(0);
		
		
		assertEquals(computerRef, computer);
	}


}
