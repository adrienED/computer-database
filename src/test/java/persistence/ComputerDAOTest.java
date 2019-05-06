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
		
		LocalDate date = LocalDate.parse("2018-12-12");


		when (resulSetMock.getLong("id")).thenReturn(13L);
		when (resulSetMock.getString("name")).thenReturn("IBM");
		when (resulSetMock.getDate("introduced")).thenReturn(date);
		when (resulSetMock.getDate("discontinued")).thenReturn(2018-12-12);
		when (resulSetMock.getLong("company_id")).thenReturn(36L);
		
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		
		Computer computer = new Computer();	
		
		LocalDate.parse("2016-12-12");
		LocalDate.parse("2017-12-12");		
		
		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(LocalDate.parse("2016-12-12"));
		computer.setDiscontinued(LocalDate.parse("2017-12-12"));	
		computer.setCompanyID(1L);
		
		assertEquals(computer, computerDAO.populate(resulSetMock));
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}


	@Test
	public void testFindById() throws ComputerNotFoundException, InvalidDateChronology {

		Computer computer = new Computer();
		
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		computer = computerDAO.findById(23L);
		
		Computer computerRef = new Computer();
		
		computerRef.setId(23L);
		computerRef.setName("test");
		computerRef.setIntroduced(LocalDate.parse("2016-12-12"));
		computerRef.setDiscontinued(LocalDate.parse("2017-12-12"));	
		computerRef.setCompanyID(1L);
		
		
		assertEquals(computerRef, computer);
	}


}
