package validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import dto.ComputerDTO;
import exception.InvalidDateChronology;

public class ComputerValidatorTest {
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	ComputerValidator computerValidator = (ComputerValidator) ctx.getBean("ComputerValidator");
	

	@Test
	public void testValidate() throws InvalidDateChronology {
		
		ComputerDTO computerDTO = new ComputerDTO();
		
		computerDTO.setName("test");
		computerDTO.setIntroduced(LocalDate.parse("2017-12-12"));
		computerDTO.setDiscontinued(LocalDate.parse("2018-12-12"));
		computerDTO.setCompanyName("Apple");

		assertEquals(true, (computerValidator.validate(computerDTO)));
		
		computerDTO = new ComputerDTO();
		
		computerDTO.setName("test");
		computerDTO.setIntroduced(LocalDate.parse("2019-12-12"));
		computerDTO.setDiscontinued(LocalDate.parse("2018-12-12"));
		computerDTO.setCompanyName("Apple");

		assertNotEquals(true, (computerValidator.validate(computerDTO)));
		
		
	}

}
