package validator;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ComputerDTO;

public class ComputerValidator {

	public ComputerValidator() {}

	Logger logger = LoggerFactory.getLogger(ComputerValidator.class);

	public boolean validate(ComputerDTO computerDTO) {

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

		if (computerDTO.getIntroduced() != null)
			validateDateIntroduced = validateDate(computerDTO.getIntroduced());
		else
			validateDateIntroduced = true;

		if (computerDTO.getDiscontinued() != null)
			validateDateDiscontinued = validateDate(computerDTO.getDiscontinued());
		else
			validateDateDiscontinued = true;

		if (computerDTO.getIntroduced() != null || computerDTO.getDiscontinued() != null)
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

	private boolean validateDate(LocalDate date) {
		boolean validate = false;
		
			try {
				
				LocalDate minDate = LocalDate.parse("1970-01-01");
				if (date.isAfter(minDate))
					validate = true;
			} catch (Exception e) {
				this.logger.error(e.getMessage(), e);
			}
		
		return validate;
	}

	private boolean validateDateOrder(LocalDate introduced, LocalDate discontinued) {
		boolean validateDateOrder=false;
			if (introduced.isBefore(discontinued))
				validateDateOrder = true;			
			
		return validateDateOrder;
	}
}
