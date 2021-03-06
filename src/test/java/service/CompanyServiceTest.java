package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ConfigForTest;
import dto.CompanyDTO;
import model.Company;
import persistence.CompanyDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigForTest.class)
public class CompanyServiceTest {
	
	@Autowired
	CompanyService companyService;
	
	
	CompanyDAO companyDAO = mock(CompanyDAO.class);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAll() {
		
		List<Company> listCompanyModel= new ArrayList<>();
		
		Company company = new Company.Builder()
		
		.withParameter(2L, "Thinkin Machine")	
		.build();
		
		listCompanyModel.add(company);
		Company company2 = new Company.Builder()
				
				.withParameter(3L, "RCA")	
				.build();		
		listCompanyModel.add(company2);
		
		CompanyDTO companyDTO = new CompanyDTO();
		List<CompanyDTO> listCompanyDTO= new ArrayList<>();
		companyDTO.setId("2");
		companyDTO.setName("Thinking Machines");		
		listCompanyDTO.add(companyDTO);
		
		CompanyDTO companyDTO2 = new CompanyDTO();
		companyDTO2.setId("3");
		companyDTO2.setName("RCA");		
		listCompanyDTO.add(companyDTO2);
		
		when (companyDAO.getAll(2,1)).thenReturn(listCompanyModel);
		
		
		assertEquals(listCompanyDTO, companyService.getAll());
		
	}

	@Test
	public void testGetNameById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeleteCompanyById() {
		fail("Not yet implemented"); // TODO
	}

}
