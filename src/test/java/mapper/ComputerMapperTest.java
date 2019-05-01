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
		computerMapper = ComputerMapper.getInstance();
	}

	@Test
	public void testDtoToModel() throws InvalidDateValueException, InvalidDateChronology {

		// valid

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

		assertEquals(23L, computerMapper.dtoToModel(computerDTO).getId());
		assertEquals("test", computerMapper.dtoToModel(computerDTO).getName());
		assertEquals(computer.getIntroduced(), computerMapper.dtoToModel(computerDTO).getIntroduced());
		assertEquals(computer.getCompany(), computerMapper.dtoToModel(computerDTO).getCompany());
		assertEquals(computer, computerMapper.dtoToModel(computerDTO));

		// invalid

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
		computerDTO.setIntroduced("2018-12-12");
		computerDTO.setDiscontinued("2019-12-12");
		computerDTO.setCompanyDTO(companyDTO);

		System.out.println(computer.getCompany());
		System.out.println(computerMapper.dtoToModel(computerDTO).getCompany());

		assertNotEquals(23L, computerMapper.dtoToModel(computerDTO).getId());
		assertNotEquals("test", computerMapper.dtoToModel(computerDTO).getName());
		assertNotEquals(computer.getIntroduced(), computerMapper.dtoToModel(computerDTO).getIntroduced());
		assertNotEquals(computer.getCompany(), computerMapper.dtoToModel(computerDTO).getCompany());
		assertNotEquals(computer, computerMapper.dtoToModel(computerDTO));

	}

	@Test
	public void testModelToDto() throws InvalidDateValueException, InvalidDateChronology {
		// valid

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

		assertEquals(computerDTO.getId(), computerMapper.modelToDto(computer).getId());
		assertEquals(computerDTO.getName(), computerMapper.modelToDto(computer).getName());
		assertEquals(computerDTO.getIntroduced(), computerMapper.modelToDto(computer).getIntroduced());
		assertEquals(computerDTO.getDiscontinued(), computerMapper.modelToDto(computer).getCompanyDTO());
		assertEquals(computerDTO.getCompanyDTO(), computerMapper.modelToDto(computer));

		// invalid

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
		computerDTO.setIntroduced("2018-12-12");
		computerDTO.setDiscontinued("2019-12-12");
		computerDTO.setCompanyDTO(companyDTO);

		assertNotEquals(computerDTO.getId(), computerMapper.modelToDto(computer).getId());
		assertNotEquals(computerDTO.getName(), computerMapper.modelToDto(computer).getName());
		assertNotEquals(computerDTO.getIntroduced(), computerMapper.modelToDto(computer).getIntroduced());
		assertNotEquals(computerDTO.getDiscontinued(), computerMapper.modelToDto(computer).getCompanyDTO());
		assertNotEquals(computerDTO.getCompanyDTO(), computerMapper.modelToDto(computer));
	}

	@Test
	public void testIdToInt() {
		String id = "23";

		assertEquals(23L, computerMapper.idToInt(id));

	}

}
