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
import org.springframework.beans.factory.annotation.Autowired;

import dto.CompanyDTO;
import model.Company;

public class CompanyMapperTest {
	
	
	Company company = new Company();
	
	CompanyDTO companyDTO = new CompanyDTO();
	CompanyDTO companydto = new CompanyDTO();
	
	@Autowired
	CompanyMapper companyMapper;


	public void setUp() throws Exception {
		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDtoToModel() {
				
		company.setId(23L);
		company.setName("test");
		
		companyDTO.setId("23");
		companyDTO.setName("test");
		
		
		assertEquals(23L,companyMapper.dtoToModel(companyDTO).getId() );
		assertEquals("test",companyMapper.dtoToModel(companyDTO).getName());
		assertEquals(company,companyMapper.dtoToModel(companyDTO));
		
		companyDTO.setId("24");
		companyDTO.setName("testa");
		
		assertNotEquals(23L,companyMapper.dtoToModel(companyDTO).getId());
		assertNotEquals("test",companyMapper.dtoToModel(companyDTO).getName());
		assertNotEquals(company,companyMapper.dtoToModel(companyDTO).getName());
		
	}
	

	@Test
	public void testModelToDto() {
	
		
		company.setId(23L);
		company.setName("test");
		
		companyDTO.setId("23");
		companyDTO.setName("test");
		
		assertEquals("23",companyMapper.modelToDto(company).getId());
		assertEquals("test",companyMapper.modelToDto(company).getName());
		assertEquals(companyDTO,companyMapper.modelToDto(company));
		
		company.setId(24L);
		company.setName("testa");
		
		
		assertNotEquals("23",companyMapper.modelToDto(company).getId());
		assertNotEquals("test",companyMapper.modelToDto(company).getName());
		assertNotEquals(companyDTO,companyMapper.modelToDto(company));
		
		
	}

}
