package validator;

import static org.junit.Assert.*;

import org.junit.Test;

import dto.ComputerDTO;

public class ComputerValidatorTest {

	@Test
	public void testValidate() {
		
		//assertEquals
		
		ComputerDTO computerDTO = new ComputerDTO();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		
		computerDTO.setName("test");
		computerDTO.setIntroduced("2017-12-12");
		computerDTO.setDiscontinued("2018-12-12");
		computerDTO.setCompanyName("Apple");

		assertEquals(true, (computerValidator.validate(computerDTO)));
		
		//assertNotEquals
		
		computerDTO = new ComputerDTO();
		computerValidator = ComputerValidator.getInstance();
		
		computerDTO.setName("test");
		computerDTO.setIntroduced("2019-12-12");
		computerDTO.setDiscontinued("2018-12-12");
		computerDTO.setCompanyName("Apple");

		assertNotEquals(true, (computerValidator.validate(computerDTO)));
		
		
	}

}
