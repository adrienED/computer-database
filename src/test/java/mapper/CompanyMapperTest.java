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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Config.AppConfig;
import dto.CompanyDTO;
import model.Company;
import service.ComputerService;

public class CompanyMapperTest {
	
	CompanyMapper companyMapper = null;
	Company company = new Company();
	
	CompanyDTO companyDTO = new CompanyDTO();
	CompanyDTO companydto = new CompanyDTO();
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);



	@Test
	public void testDtoToModel() {
		companyMapper = ctx.getBean(CompanyMapper.class);
				
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
		companyMapper = ctx.getBean(CompanyMapper.class);
		
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
