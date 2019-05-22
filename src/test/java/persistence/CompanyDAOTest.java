package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ConfigForTest;
import model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigForTest.class)
public class CompanyDAOTest {
	
	ResultSet resulSetMock = mock(ResultSet.class);
	
	@Autowired
	CompanyDAO companyDAO;
	

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testPopulate() throws SQLException {
		when (resulSetMock.getLong("id")).thenReturn(13L);
		when (resulSetMock.getString("name")).thenReturn("IBM");
		
		Company company = new Company.Builder()
	 			.withParameter(13L, "IBM")
	 			.build();
		
		assertEquals(company, companyDAO.populate(resulSetMock));
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFindById() {
		Company company;

		company = companyDAO.findById(13L);
		
		Company companyRef = new Company.Builder().
		withParameter(13L, "IBM")
		.build();
		
		
		assertEquals(companyRef, company);
	}

	@Test
	public void testFindByName() {

		
		assertEquals(13L, companyDAO.findByName("IBM"));
	}

	@Test
	public void testGetAllIntInt() {
		ArrayList<Company> companies = new ArrayList<Company>();
		
		 	Company company = new Company.Builder()
		 			.withParameter(1L, "Apple Inc.")
		 			.build();
			
			companies.add(company);
			
			Company company1 = new Company.Builder()
		 			.withParameter(2L, "Thinking Machines")
		 			.build();

			companies.add(company1);
			Company company2 = new Company.Builder()
		 			.withParameter(3L, "RCA")
		 			.build();

			companies.add(company2);
			
			assertEquals(companies,companyDAO.getAll(3,0) );
		}
	

	@Test
	public void testDeleteCompanyById() {
		fail("Not yet implemented"); // TODO
	}

}
