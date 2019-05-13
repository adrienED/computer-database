package mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dto.CompanyDTO;
import model.Company;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration

public class CompanyMapperTest {
	
	Company company = new Company();
	
	CompanyDTO companyDTO = new CompanyDTO();
	CompanyDTO companydto = new CompanyDTO();
	
	@Autowired
	private CompanyMapper companyMapper;


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
