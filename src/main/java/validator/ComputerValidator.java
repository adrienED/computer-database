package validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dto.ComputerDTO;
import exception.InvalidDateChronology;

@Component ("ComputerValidator")
public class ComputerValidator {

	public ComputerValidator() {}

	Logger logger = LoggerFactory.getLogger(ComputerValidator.class);

	public boolean validate(ComputerDTO computerDTO) throws InvalidDateChronology {

		boolean validateName;
		boolean validateDateIntroduced;
		boolean validateDateDiscontinued;
		boolean validateDateOrder;
		boolean validateNameCompany;

		if (computerDTO.getName() != null)
			validateName = true;
		else
			validateName = false;

		if (computerDTO.getCompanyName() != null)
			validateNameCompany = true;
		else
			validateNameCompany = false;

		if (computerDTO.getIntroduced() != "")
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

		this.logger.info(validateName + " " + validateDateIntroduced + " " + validateDateDiscontinued + " "
				+ validateDateOrder + " " + validateNameCompany);

		if (validateName == true && validateDateIntroduced == true && validateDateDiscontinued == true
				&& validateDateOrder == true)
			return true;
		else
			return false;

	}

	private boolean validateDate(String date) throws InvalidDateChronology {
		boolean validate = false;
		try {
			LocalDate minDate = LocalDate.parse("1970-01-01");
			LocalDate dateLocal = LocalDate.parse(date);
			if (dateLocal.isAfter(minDate))
				validate = true;
		}
		catch (ParseException e) {
			this.logger.error(" erreur parse input date ");
		}
		return validate;
	}

	private boolean validateDateOrder(String introduced, String discontinued) {
		boolean validateDateOrder=false;
		try {
			LocalDate dateLocalInt = LocalDate.parse(introduced);
			LocalDate dateLocalDisc = LocalDate.parse(discontinued);
			if (dateLocalInt.isBefore(dateLocalDisc))
				validateDateOrder = true;
		}
		catch (ParseException e) {
			this.logger.error(" erreur parse input date ");
		}
		return validateDateOrder;
	}
}
