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
	public void testGetAll() {
		 List<Company> companies = new ArrayList<Company>();
		 
		 
	        try {
	            Connection connection = ConnectionDAO.getConnection();
	            PreparedStatement statement = connection.prepareStatement("select * from company");
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	            	Company company = new Company();
	    	   
	    	        	company.setId(resultSet.getLong("id"));
	    	            company.setName(resultSet.getString("name"));
	    	            
	            	companies.add(company);

	            }
	            connection.close();
	        } catch (SQLException ex) {
	        	
	        }
		}
	

	

	@Test
	public void testGetAllIntInt() {
		fail("Not yet implemented"); // TODO
	}

}
