package mapper;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Computer;

public class ComputerMapperTest {

	ComputerMapper computerMapper = null;

	ComputerDTO computerDTO = new ComputerDTO();

	@Before
	public void setUp() throws Exception {
		computerMapper = ComputerMapper.getInstance();
	}

	@Test
	public void testDtoToModel() throws InvalidDateValueException, InvalidDateChronology {

		Computer computer = new Computer();

		// valid

		LocalDate introDate = LocalDate.parse("2016-12-12");
		LocalDate disconDate = LocalDate.parse("2017-12-12");

		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(introDate);
		computer.setDiscontinued(disconDate);
		computer.setCompanyID(1L);

		computerDTO.setId("23");
		computerDTO.setName("test");
		computerDTO.setIntroduced("2016-12-12");
		computerDTO.setDiscontinued("2017-12-12");
		computerDTO.setCompanyName("Apple Inc.");

		assertEquals(23L, computerMapper.dtoToModel(computerDTO).getId());
		assertEquals("test", computerMapper.dtoToModel(computerDTO).getName());
		assertEquals(computer.getIntroduced(), computerMapper.dtoToModel(computerDTO).getIntroduced());
		assertEquals(computer.getCompanyID(), computerMapper.dtoToModel(computerDTO).getCompanyID());
		assertEquals(computer, computerMapper.dtoToModel(computerDTO));

		// invalid

		introDate = LocalDate.parse("2016-12-12");
		disconDate = LocalDate.parse("2017-12-12");

		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(introDate);
		computer.setDiscontinued(disconDate);
		computer.setCompanyID(2L);

		computerDTO.setId("24");
		computerDTO.setName("tes");
		computerDTO.setIntroduced("2018-12-12");
		computerDTO.setDiscontinued("2019-12-12");
		computerDTO.setCompanyName("RCA");

		assertNotEquals(23L, computerMapper.dtoToModel(computerDTO).getId());
		assertNotEquals("test", computerMapper.dtoToModel(computerDTO).getName());
		assertNotEquals(computer.getIntroduced(), computerMapper.dtoToModel(computerDTO).getIntroduced());
		assertNotEquals(computer.getCompanyID(), computerMapper.dtoToModel(computerDTO).getCompanyID());
		assertNotEquals(computer, computerMapper.dtoToModel(computerDTO));

	}

	@Test
	public void testModelToDto() throws InvalidDateValueException, InvalidDateChronology {
		// valid

		LocalDate introDate = LocalDate.parse("2016-12-12");
		LocalDate disconDate = LocalDate.parse("2017-12-12");

		Computer computer = new Computer();

		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(introDate);
		computer.setDiscontinued(disconDate);
		computer.setCompanyID(1L);

		computerDTO.setId("23");
		computerDTO.setName("test");
		computerDTO.setIntroduced("2016-12-12");
		computerDTO.setDiscontinued("2017-12-12");
		computerDTO.setCompanyName("Apple Inc.");

		assertEquals(computerDTO.getId(), computerMapper.modelToDto(computer).getId());
		assertEquals(computerDTO.getName(), computerMapper.modelToDto(computer).getName());
		assertEquals(computerDTO.getIntroduced(), computerMapper.modelToDto(computer).getIntroduced());
		assertEquals(computerDTO.getDiscontinued(), computerMapper.modelToDto(computer).getDiscontinued());
		assertEquals(computerDTO.getCompanyName(), computerMapper.modelToDto(computer).getCompanyName());

		// invalid

		introDate = LocalDate.parse("2016-12-12");
		disconDate = LocalDate.parse("2017-12-12");

		computer.setId(23L);
		computer.setName("test");
		computer.setIntroduced(introDate);
		computer.setDiscontinued(disconDate);
		computer.setCompanyID(3L);

		computerDTO.setId("24");
		computerDTO.setName("tes");
		computerDTO.setIntroduced("2018-12-12");
		computerDTO.setDiscontinued("2019-12-12");
		computerDTO.setCompanyName("TEST");

		assertNotEquals(computerDTO.getId(), computerMapper.modelToDto(computer).getId());
		assertNotEquals(computerDTO.getName(), computerMapper.modelToDto(computer).getName());
		assertNotEquals(computerDTO.getIntroduced(), computerMapper.modelToDto(computer).getIntroduced());
		assertNotEquals(computerDTO.getDiscontinued(), computerMapper.modelToDto(computer).getCompanyName());
		assertNotEquals(computerDTO.getCompanyName(), computerMapper.modelToDto(computer).getCompanyName());
	}

	@Test
	public void testIdToInt() {
		String id = "23";

		assertEquals(23L, computerMapper.idToInt(id));

	}

}
