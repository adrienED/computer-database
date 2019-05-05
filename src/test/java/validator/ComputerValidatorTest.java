package validator;

import static org.junit.Assert.*;

import org.junit.Test;

import dto.ComputerDTO;

public class ComputerValidatorTest {

	@Test
	public void testValidate() {
		
		ComputerDTO computerDTO = new ComputerDTO();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		
		computerDTO.setId("23");
		computerDTO.setName("test");
		computerDTO.setIntroduced("2018-12-12");
		computerDTO.setDiscontinued("2017-12-12");
		computerDTO.setCompanyName("Apple");

		System.out.println(computerValidator.validate(computerDTO));
		
	}

}
