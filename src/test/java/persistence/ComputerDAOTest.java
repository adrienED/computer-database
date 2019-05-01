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

import dto.CompanyDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import model.Company;
import model.Computer;

public class ComputerDAOTest {
	
	
	ConnectionDAO connectionDAO = new ConnectionDAO();
	
	ResultSet resulSetMock = mock(ResultSet.class);

	@Before
	public void setUp() throws Exception {
		
		FileInputStream f;
		try {
			f = new FileInputStream("src/test/resources/db.properties");		
				Properties properties = new Properties();
	            properties.load(f);
	          
	            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Test
	public void testPopulate() throws SQLException {


		when (resulSetMock.getLong("id")).thenReturn(13L);
		when (resulSetMock.getString("name")).thenReturn("IBM");
		//when (resulSetMock.getDate("introduced")).thenReturn(2017-12-12);
		//when (resulSetMock.getDate("discontinued")).thenReturn(2018-12-12);
		when (resulSetMock.getLong("company_id")).thenReturn(36L);
		
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		
		Computer computer = new Computer();	
		
		LocalDate.parse("2016-12-12");
		LocalDate.parse("2017-12-12");		
		Company company = new Company();
		company.setId(1L);
		company.setName("Apple Inc.");
		
		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(LocalDate.parse("2016-12-12"));
		computer.setDiscontinued(LocalDate.parse("2017-12-12"));	
		computer.setCompany(company);
		
		//assertEquals(computer, computerDAO.populate(resulSetMock));
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}


	@Test
	public void testFindById() throws ComputerNotFoundException, InvalidDateChronology {

		Computer computer = new Computer();
		
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		computer = computerDAO.findById(13L);
		
		Computer computerRef = new Computer();
		
		Company company = new Company();
		company.setId(1L);
		company.setName("Apple Inc.");
		
		computerRef.setId(23L);
		computerRef.setName("test");
		computerRef.setIntroduced(LocalDate.parse("2016-12-12"));
		computerRef.setDiscontinued(LocalDate.parse("2017-12-12"));	
		computerRef.setCompany(company);
		
		
		assertEquals(computerRef, computer);
	}


}
