package validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ConfigForTest;
import dto.ComputerDTO;
import exception.EmptyCompanyNameException;
import exception.EmptyComputerNameException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigForTest.class)
public class ComputerValidatorTest {
	
	@Autowired
	ComputerValidator computerValidator;
	

	@Test
	public void testValidate() throws InvalidDateChronology, EmptyComputerNameException, EmptyCompanyNameException, InvalidDateValueException {
		
		ComputerDTO computerDTO = new ComputerDTO();
		
		computerDTO.setName("test");
		computerDTO.setIntroduced("2017-12-12");
		computerDTO.setDiscontinued("2018-12-12");
		computerDTO.setCompanyName("Apple");

		assertEquals(true, (computerValidator.validate(computerDTO)));
		
		computerDTO = new ComputerDTO();
		
		computerDTO.setName("test");
		computerDTO.setIntroduced("2019-12-12");
		computerDTO.setDiscontinued("2018-12-12");
		computerDTO.setCompanyName("Apple");

		assertNotEquals(true, (computerValidator.validate(computerDTO)));
		
		
	}

}
