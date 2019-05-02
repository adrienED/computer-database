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

		assertEquals(companyRef, company);

	}

	@Test
	public void testPopulate() throws SQLException, IOException {

		when(resulSetMock.getLong("id")).thenReturn(13L);
		when(resulSetMock.getString("name")).thenReturn("IBM");

		CompanyDAO companyDAO = CompanyDAO.getInstance();

		Company companyRef = new Company();
		companyRef.setId(13L);
		companyRef.setName("IBM");

		assertEquals(companyRef, companyDAO.populate(resulSetMock));

	}

	@Test
	public void testGetAllIntInt() throws SQLException {
		CompanyDAO companyDAO = CompanyDAO.getInstance();

		List<Company> listCompanies = new ArrayList<Company>();

		Company company = new Company();
		company.setId(1L);
		company.setName("Apple Inc.");
		listCompanies.add(company);
		company.setId(2L);
		company.setName("Thinking Machines");
		listCompanies.add(company);
		company.setId(3L);
		company.setName("RCA");
		listCompanies.add(company);
		company.setId(4L);
		company.setName("Netronics");
		listCompanies.add(company);
		company.setId(5L);
		company.setName("Tandy Corporation");
		listCompanies.add(company);
		company.setId(6L);
		company.setName("COmmodore International");
		listCompanies.add(company);
		company.setId(7L);
		company.setName("MOS Technology");
		listCompanies.add(company);
		company.setId(8L);
		company.setName("Micro Instrumentation and Telemetry Systems");
		listCompanies.add(company);
		company.setId(9L);
		company.setName("IMS Associates, Inc.");
		listCompanies.add(company);
		company.setId(10L);
		company.setName("Digital Equipment Corporation");
		listCompanies.add(company);

		assertEquals(listCompanies, companyDAO.getAll(10, 1));
	}
}
