package service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mapper.CompanyMapper;
import model.Company;
import persistence.CompanyDAO;


public class CompanyServiceTest {
	
	CompanyService companyService = null;
	
	
	CompanyDAO companyDAO = mock(CompanyDAO.class);

	@Before
	public void setUp() throws Exception {
		
		companyService=companyService.getInstance();
	}

	@Test
	public void testGetAll() {
		Company company = new Company();
		company.setId(23L);
		company.setName("test");
		List<Company> listCompany= new ArrayList<>();
		listCompany.add(company);
		
		when (companyDAO.getAll(5,10)).thenReturn(listCompany);
		
		
		assertEquals(listCompany,companyService.getAll(5, 10));
	}

}
