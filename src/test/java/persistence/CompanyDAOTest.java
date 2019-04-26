package persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import model.Company;

public class CompanyDAOTest {
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
	public void testFindById() throws SQLException {
		Company company = new Company();
		
		CompanyDAO companyDAO = CompanyDAO.getInstance();
		company = companyDAO.findById(13L);
		
		Company companyRef = new Company();
		companyRef.setId(13L);
		companyRef.setName("IBM");
		
		if (company.equals(companyRef)) 
			System.out.println("ok");
		
	}

	@Test
	public void testPopulate() throws SQLException, IOException {

		when (resulSetMock.getLong("id")).thenReturn(13L);
		when (resulSetMock.getString("name")).thenReturn("IBM");
		
		CompanyDAO companyDAO = CompanyDAO.getInstance();
		
		Company companyRef = new Company();
		companyRef.setId(13L);
		companyRef.setName("IBM");
		
		
		if (companyDAO.populate(resulSetMock).equals(companyRef))
			System.out.println("ok");
		
	}

	@Test
	public void testGetAll() throws SQLException {
		 List<Company> companies = new ArrayList<Company>();
		 Company company = new Company();
		 company.setId(1L);
		 company.setName("Apple Inc.");
		 
		 companies.add(company);
		 
		 when(resulSetMock.getLong("id")).thenReturn(1L);
			when(resulSetMock.getString("name")).thenReturn("Apple Inc.");
			when(resulSetMock.getLong("id")).thenReturn(2L);
			when(resulSetMock.getString("name")).thenReturn("Thinking Machines");
			when(resulSetMock.getLong("id")).thenReturn(3L);
			when(resulSetMock.getString("name")).thenReturn("RCA");
			when(resulSetMock.getLong("id")).thenReturn(4L);
			when(resulSetMock.getString("name")).thenReturn("Netronics");
			when(resulSetMock.getLong("id")).thenReturn(5L);
			when(resulSetMock.getString("name")).thenReturn("Tandy Corporation");
			when(resulSetMock.getLong("id")).thenReturn(6L);
			when(resulSetMock.getString("name")).thenReturn("COmmodore International");
			when(resulSetMock.getLong("id")).thenReturn(7L);
			when(resulSetMock.getString("name")).thenReturn("MOS Technology");
			when(resulSetMock.getLong("id")).thenReturn(8L);
			when(resulSetMock.getString("name")).thenReturn("Micro Instrumentation and Telemetry Systems");
			when(resulSetMock.getLong("id")).thenReturn(9L);
			when(resulSetMock.getString("name")).thenReturn("IMS Associates, Inc.");
			when(resulSetMock.getLong("id")).thenReturn(10L);
			when(resulSetMock.getString("name")).thenReturn("Digital Equipment Corporation");
			when(resulSetMock.getLong("id")).thenReturn(11L);
			when(resulSetMock.getString("name")).thenReturn("Lincoln Laboratory");
			when(resulSetMock.getLong("id")).thenReturn(12L);
			when(resulSetMock.getString("name")).thenReturn("Moore School of Electrical Engineering");
			when(resulSetMock.getLong("id")).thenReturn(13L);
			when(resulSetMock.getString("name")).thenReturn("IBM");
			when(resulSetMock.getLong("id")).thenReturn(14L);
			when(resulSetMock.getString("name")).thenReturn("Amiga Corporation");
			when(resulSetMock.getLong("id")).thenReturn(15L);
			when(resulSetMock.getString("name")).thenReturn("Canon");
			when(resulSetMock.getLong("id")).thenReturn(16L);
			when(resulSetMock.getString("name")).thenReturn("Nokia");
			when(resulSetMock.getLong("id")).thenReturn(17L);
			when(resulSetMock.getString("name")).thenReturn("Moore School of Electrical Engineering");
			when(resulSetMock.getLong("id")).thenReturn(18L);
			when(resulSetMock.getString("name")).thenReturn("OQO");
			when(resulSetMock.getLong("id")).thenReturn(19L);
			when(resulSetMock.getString("name")).thenReturn("NeXT");
			when(resulSetMock.getLong("id")).thenReturn(20L);
			when(resulSetMock.getString("name")).thenReturn("Atari");
			
			when(resulSetMock.next()).thenReturn(true).thenReturn(false);
			
			
		}

	@Test
	public void testGetAllIntInt() {
		fail("Not yet implemented"); // TODO
	}

}
