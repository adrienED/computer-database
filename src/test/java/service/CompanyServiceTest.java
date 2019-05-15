package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import dto.CompanyDTO;
import model.Company;
import persistence.CompanyDAO;

public class CompanyServiceTest {
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	CompanyService companyService = (CompanyService) ctx.getBean("CompanyService");
	
	
	CompanyDAO companyDAO = mock(CompanyDAO.class);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAll() {
		Company company = new Company();
		List<Company> listCompanyModel= new ArrayList<>();
		company.setId(2L);
		company.setName("Thinking Machines");		
		listCompanyModel.add(company);
		Company company2 = new Company();
		company2.setId(3L);
		company2.setName("RCA");		
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
		
		
		assertEquals(listCompanyDTO, companyService.getAll(2, 1));
		
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
