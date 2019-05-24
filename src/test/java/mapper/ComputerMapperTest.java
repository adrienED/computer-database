package mapper;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ConfigForTest;
import config.WebConfig;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Computer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigForTest.class)
public class ComputerMapperTest {

	@Autowired
	ComputerMapper computerMapper;

	ComputerDTO computerDTO = new ComputerDTO();

	@Test
	public void testDtoToModelWhithoutDate() throws InvalidDateValueException, InvalidDateChronology {

	Computer.Builder builder = new Computer.Builder();

	// valid

	LocalDate introDate = LocalDate.parse("2016-12-12");
	LocalDate disconDate = LocalDate.parse("2017-12-12");

	builder.withId(23L);
	builder.withName("test");

	builder.withCompanyID(1L);
	
	Computer computer = builder.build();

	computerDTO.setId("23");
	computerDTO.setName("test");
	computerDTO.setIntroduced(null);
	computerDTO.setDiscontinued(null);
	computerDTO.setCompanyName("Apple Inc.");
	
	
	System.out.println(computerMapper.dtoToModel(computerDTO));
	System.out.println(computer);

	assertEquals(23L, computerMapper.dtoToModel(computerDTO).getId());
	assertEquals("test", computerMapper.dtoToModel(computerDTO).getName());
	assertEquals(computer.getIntroduced(), computerMapper.dtoToModel(computerDTO).getIntroduced());
	assertEquals(computer.getDiscontinued(), computerMapper.dtoToModel(computerDTO).getDiscontinued());
	assertEquals(computer.getCompanyID(), computerMapper.dtoToModel(computerDTO).getCompanyID());
	assertEquals(computer, computerMapper.dtoToModel(computerDTO));

	
	}

	@Test
	public void testDtoToModelAllParameter() throws InvalidDateValueException, InvalidDateChronology {

			Computer.Builder builder = new Computer.Builder();

			// valid

			LocalDate introDate = LocalDate.parse("2016-12-12");
			LocalDate disconDate = LocalDate.parse("2017-12-12");

			builder.withId(23L);
			builder.withName("test");
			builder.withIntroduced(introDate);
			builder.withDiscontinued(disconDate);
			builder.withCompanyID(1L);
			
			Computer computer = builder.build();

			computerDTO.setId("23");
			computerDTO.setName("test");
			computerDTO.setIntroduced("2016-12-12");
			computerDTO.setDiscontinued("2017-12-12");
			computerDTO.setCompanyName("Apple Inc.");
			
			
			System.out.println(computerMapper.dtoToModel(computerDTO));

			assertEquals(23L, computerMapper.dtoToModel(computerDTO).getId());
			assertEquals("test", computerMapper.dtoToModel(computerDTO).getName());
			assertEquals(computer.getIntroduced(), computerMapper.dtoToModel(computerDTO).getIntroduced());
			assertEquals(computer.getDiscontinued(), computerMapper.dtoToModel(computerDTO).getDiscontinued());
			assertEquals(computer.getCompanyID(), computerMapper.dtoToModel(computerDTO).getCompanyID());
			assertEquals(computer, computerMapper.dtoToModel(computerDTO));
	}



	@Test
	public void testModelToDto() throws InvalidDateValueException, InvalidDateChronology {
		// valid

		LocalDate introDate = LocalDate.parse("2016-12-12");
		LocalDate disconDate = LocalDate.parse("2017-12-12");

		Computer.Builder builder = new Computer.Builder();

		builder.withId(23L);
		builder.withName("test");
		builder.withIntroduced(introDate);
		builder.withDiscontinued(disconDate);
		builder.withCompanyID(1L);
		
		Computer computer = builder.build();

		computerDTO.setId("23");
		computerDTO.setName("test");
		computerDTO.setIntroduced("2016-12-12");
		computerDTO.setDiscontinued("2017-12-12");
		computerDTO.setCompanyName("Apple Inc.");

		assertEquals(computerDTO.getId(), computerMapper.modelToDto(computer).getId());
		assertEquals(computerDTO.getName(), computerMapper.modelToDto(computer).getName());
		assertEquals(computerDTO.getIntroduced(), computerMapper.modelToDto(computer).getIntroduced());
		assertEquals(computerDTO.getDiscontinued(), computerMapper.modelToDto(computer).getDiscontinued());
		System.out.println(computerMapper.modelToDto(computer));
		assertEquals(computerDTO.getCompanyName(), computerMapper.modelToDto(computer).getCompanyName());

	}

	@Test
	public void testIdToInt() {
		String id = "23";

		assertEquals(23L, computerMapper.idToLong(id));

	}

}
