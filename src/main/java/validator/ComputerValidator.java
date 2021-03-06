package validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dto.ComputerDTO;
import exception.EmptyCompanyNameException;
import exception.EmptyComputerNameException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;

@Component ("ComputerValidator")
public class ComputerValidator {

	public ComputerValidator() {}

	Logger logger = LoggerFactory.getLogger(ComputerValidator.class);

	public boolean validate(ComputerDTO computerDTO) throws EmptyComputerNameException, EmptyCompanyNameException, InvalidDateChronology, InvalidDateValueException {

		boolean validateName;
		boolean validateDateIntroduced;
		boolean validateDateDiscontinued;
		boolean validateDateOrder;
		boolean validateNameCompany;

		if (computerDTO.getName() != null && computerDTO.getName() !="")
			validateName = true;
		else
			throw new EmptyComputerNameException();

		if (computerDTO.getCompanyName() != null)
			validateNameCompany = true;
		else
			throw new EmptyCompanyNameException();

		if (computerDTO.getIntroduced() != "" || computerDTO.getIntroduced() != null)
			validateDateIntroduced = validateDate(computerDTO.getIntroduced());	
			
		else
			validateDateIntroduced = true;

		if (computerDTO.getDiscontinued() != "")
			validateDateDiscontinued = validateDate(computerDTO.getDiscontinued());
		else
			validateDateDiscontinued = true;

		if (computerDTO.getIntroduced() != "" && computerDTO.getDiscontinued() != "")
			validateDateOrder = validateDateOrder(computerDTO.getIntroduced(), computerDTO.getDiscontinued());
		else
			validateDateOrder = true;

		if (validateName == true && validateDateIntroduced == true && validateDateDiscontinued == true
				&& validateDateOrder == true)
			return true;
		else
			return false;

	}

	private boolean validateDate(String date) throws InvalidDateChronology, InvalidDateValueException {
		boolean validate = false;
		try {
			LocalDate minDate = LocalDate.parse("1970-01-01");
			LocalDate dateLocal = LocalDate.parse(date);
			if (dateLocal.isBefore(minDate))
				throw new InvalidDateChronology();
		}
		catch (ParseException e) {
			throw new InvalidDateValueException(date);
		}
		return validate;
	}

	private boolean validateDateOrder(String introduced, String discontinued) throws InvalidDateChronology, InvalidDateValueException {
		boolean validateDateOrder=false;
		try {
			LocalDate dateLocalInt = LocalDate.parse(introduced);
			LocalDate dateLocalDisc = LocalDate.parse(discontinued);
			if (dateLocalInt.isAfter(dateLocalDisc))
				throw new InvalidDateChronology();
		}
		catch (ParseException e) {
			this.logger.error(" erreur parse input date ");
			throw new InvalidDateValueException(introduced);
		}
		return validateDateOrder;
	}
}
