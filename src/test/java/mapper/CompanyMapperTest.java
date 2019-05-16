package mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.AppConfig;
import dto.CompanyDTO;
import model.Company;
import persistence.CompanyDAO;
import service.CompanyService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)

public class CompanyMapperTest {
	
	@Autowired
	CompanyMapper companyMapper;

	@Autowired
	CompanyDAO companyDAO;
	
	
	
	CompanyDTO companyDTO = new CompanyDTO();
	CompanyDTO companydto = new CompanyDTO();

	public void setUp() throws Exception {
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDtoToModel() {
		
		Company.Builder builder = new Company.Builder();		
		builder.withParameter(23L, "Macbook");
		Company company = builder.build();
		
		companyDTO.setId("23");
		companyDTO.setName("Macbook");
		
		assertEquals(23L,companyMapper.dtoToModel(companyDTO).getId() );
		assertEquals("Macbook",companyMapper.dtoToModel(companyDTO).getName());
		assertEquals(company,companyMapper.dtoToModel(companyDTO));
		
		
		
		assertEquals(23L,companyMapper.dtoToModel(companyDTO).getId());
		assertEquals("Macbook",companyMapper.dtoToModel(companyDTO).getName());
		assertEquals(company,companyMapper.dtoToModel(companyDTO));
		
	}
	

	@Test
	public void testModelToDto() {
	
		
		Company.Builder builder = new Company.Builder();		
		builder.withParameter(23L, "Macbook");
		Company company = builder.build();
		
		companyDTO.setId("23");
		companyDTO.setName("Macbook");
		
		assertEquals("23",companyMapper.modelToDto(company).getId());
		assertEquals("Macbook",companyMapper.modelToDto(company).getName());
		assertEquals(companyDTO,companyMapper.modelToDto(company));
		
		company.setId(23L);
		company.setName("Macbook");
		
		
		assertEquals("23",companyMapper.modelToDto(company).getId());
		assertEquals("Macbook",companyMapper.modelToDto(company).getName());
		assertEquals(companyDTO,companyMapper.modelToDto(company));
		
		
	}

}
