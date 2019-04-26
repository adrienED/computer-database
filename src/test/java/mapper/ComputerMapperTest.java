package mapper;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Company;
import model.Computer;

public class ComputerMapperTest {
	
	ComputerMapper computerMapper = null;
	Computer computer = new Computer();
	
	ComputerDTO computerDTO = new ComputerDTO();


	@Before
	public void setUp() throws Exception {
		computerMapper=ComputerMapper.getInstance();
	}

	@Test
	public void testDtoToModel() throws InvalidDateValueException, InvalidDateChronology {
		
		//valid
		
		LocalDate introDate = LocalDate.parse("2016-12-12");
		LocalDate disconDate = LocalDate.parse("2017-12-12");
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId("1");
		companyDTO.setName("Apple Inc.");
		
		Company company = new Company();
		company.setId(1L);
		company.setName("Apple Inc.");
		
		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(introDate);
		computer.setDiscontinued(disconDate);	
		computer.setCompany(company);
		
		computerDTO.setId("23");
		computerDTO.setName("test");
		computerDTO.setIntroduced("2016-12-12");
		computerDTO.setDiscontinued("2017-12-12");	
		computerDTO.setCompanyDTO(companyDTO);
		
		
		
		assertEquals(23L,computerMapper.dtoToModel(computerDTO).getId() );
		assertEquals("test",computerMapper.dtoToModel(computerDTO).getName());
		assertEquals(computer.getIntroduced(),computerMapper.dtoToModel(computerDTO).getIntroduced());
		assertEquals(computer.getCompany(),computerMapper.dtoToModel(computerDTO).getCompany());
		assertEquals(computer,computerMapper.dtoToModel(computerDTO));
		
		
		//invalid
		
		
		introDate = LocalDate.parse("2016-12-12");
		disconDate = LocalDate.parse("2017-12-12");
		companyDTO = new CompanyDTO();
		companyDTO.setId("1");
		companyDTO.setName("Apple ");
		
		company = new Company();
		company.setId(1L);
		company.setName("Apple Inc.");
		
		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(introDate);
		computer.setDiscontinued(disconDate);	
		computer.setCompany(company);
		
		computerDTO.setId("24");
		computerDTO.setName("tes");
		computerDTO.setIntroduced("201612-12");
		computerDTO.setDiscontinued("20112-12");	
		computerDTO.setCompanyDTO(companyDTO);
		
		//System.out.println(computer.getId());
		System.out.println(computerMapper.dtoToModel(computerDTO));
	
		assertThat(computer.getId(), not(computerMapper.dtoToModel(computerDTO).getId()));
		//assertThat(computer.getName(), not(computerMapper.dtoToModel(computerDTO).getName()));
		//assertThat(computer.getIntroduced(), not(computerMapper.dtoToModel(computerDTO).getIntroduced()));
		//assertThat(computer.getDiscontinued(), not(computerMapper.dtoToModel(computerDTO).getDiscontinued()));
		//assertThat(computer.getCompany(), not(computerMapper.dtoToModel(computerDTO).getCompany()));
		
	}

	@Test
	public void testModelToDto() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIdToInt() {
		fail("Not yet implemented"); // TODO
	}

}
