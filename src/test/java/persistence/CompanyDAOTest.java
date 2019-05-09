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

	}
	
	@Test
	public void testFindById() throws SQLException {
		Company company = new Company();
		
		CompanyDAO companyDAO = CompanyDAO.getInstance();
		company = companyDAO.findById(13L);
		
		Company companyRef = new Company();
		companyRef.setId(13L);
		companyRef.setName("IBM");
		
		assertEquals(companyRef, company);
		
	}

	@Test
	public void testPopulate() throws SQLException, IOException {

		when (resulSetMock.getLong("id")).thenReturn(13L);
		when (resulSetMock.getString("name")).thenReturn("IBM");
		
		CompanyDAO companyDAO = CompanyDAO.getInstance();
		
		Company companyRef = new Company();
		companyRef.setId(13L);
		companyRef.setName("IBM");
		
		assertEquals(companyRef, companyDAO.populate(resulSetMock));
		
	}


	@Test
	public void testGetAllIntInt() throws SQLException {
		CompanyDAO companyDAO = CompanyDAO.getInstance();
		 
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
			
			
			when(resulSetMock.next()).thenReturn(true).thenReturn(false);
			
			assertEquals(resulSetMock,companyDAO.getAll(10,1) );
		}
	}


