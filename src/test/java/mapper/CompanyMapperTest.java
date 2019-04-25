package mapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import dto.CompanyDTO;
import model.Company;

public class CompanyMapperTest {
	
	CompanyMapper companyMapper = null;
	Company company = new Company();
	
	CompanyDTO companyDTO = new CompanyDTO();
	CompanyDTO companydto = new CompanyDTO();


	public void setUp() throws Exception {
		
	
		companyMapper = CompanyMapper.getInstance();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDtoToModel() {
		CompanyMapper companyMapper	=CompanyMapper.getInstance();
				
		company.setId(23L);
		company.setName("test");
		
		companyDTO.setId("23");
		companyDTO.setName("test");
		
		
		assertEquals(23L,companyMapper.dtoToModel(companyDTO).getId() );
		assertEquals("test",companyMapper.dtoToModel(companyDTO).getName());
		assertEquals(company,companyMapper.dtoToModel(companyDTO));
		
		
		
	}
	

	@Test
	public void testModelToDto() {
		CompanyMapper companyMapper	=CompanyMapper.getInstance();
		
		company.setId(23L);
		company.setName("test");
		
		companyDTO.setId("23");
		companyDTO.setName("test");
		
		assertEquals("23",companyMapper.modelToDto(company).getId());
		assertEquals("test",companyMapper.modelToDto(company).getName());
		assertEquals(companyDTO,companyMapper.modelToDto(company));
		
		if(!(companyMapper.modelToDto(company).getId().equals("24")))
			System.out.println("okID");
				
		if(!(companyMapper.modelToDto(company).getName().equals("testds")))
			System.out.println("okName");
		if(!(companyMapper.modelToDto(company).getName().equals(companydto)))
			System.out.println("ok");
	}

}
