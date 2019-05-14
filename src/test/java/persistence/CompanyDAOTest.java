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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import model.Company;

public class CompanyDAOTest {
	
	ResultSet resulSetMock = mock(ResultSet.class);
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

	
	@Autowired
	CompanyDAO companyDAO;
	

	@Before
	public void setUp() throws Exception {
		
		companyDAO = (CompanyDAO) ctx.getBean("CompanyDAO");

	}
	
	@Test
	public void testFindById() throws SQLException {
		Company company = new Company();

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
		
		Company companyRef = new Company();
		companyRef.setId(13L);
		companyRef.setName("IBM");
		
		assertEquals(companyRef, companyDAO.populate(resulSetMock));
		
	}


	@Test
	public void testGetAllIntInt() throws SQLException {

		
		ArrayList<Company> companies = new ArrayList<Company>();
		Company company = new Company();
		 
			company.setId(1L);
			company.setName("Apple Inc.");
			
			companies.add(company);
			Company company1 = new Company();
			company1.setId(2L);
			company1.setName("Thinking Machines");
			companies.add(company1);
			Company company2 = new Company();
			company2.setId(3L);
			company2.setName("RCA");
			companies.add(company2);
			
			assertEquals(companies,companyDAO.getAll(3,0) );
		}
	}


