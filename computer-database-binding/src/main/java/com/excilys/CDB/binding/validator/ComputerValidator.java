package com.excilys.CDB.binding.validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.CDB.binding.dto.ComputerDTO;
import com.excilys.CDB.core.exception.EmptyCompanyNameException;
import com.excilys.CDB.core.exception.EmptyComputerNameException;
import com.excilys.CDB.core.exception.InvalidDateChronology;
import com.excilys.CDB.core.exception.InvalidDateValueException;

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
		
		System.out.println(computerDTO);

		if (computerDTO.getName() != null && computerDTO.getName() !="")
			validateName = true;
		else
			throw new EmptyComputerNameException();

		

		if ( ! computerDTO.getIntroduced().isEmpty() && computerDTO.getIntroduced() != null)
			validateDateIntroduced = validateDate(computerDTO.getIntroduced());	
			
		else
			validateDateIntroduced = true;

		if ( ! computerDTO.getDiscontinued().isEmpty() && computerDTO.getDiscontinued() != null)
			validateDateDiscontinued = validateDate(computerDTO.getDiscontinued());
		else
			validateDateDiscontinued = true;

		if ( ! computerDTO.getIntroduced().isEmpty() && ! computerDTO.getDiscontinued().isEmpty())
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
		catch (Exception e) {
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
		catch (Exception e) {
			this.logger.error(" erreur parse input date ");
			throw new InvalidDateValueException(introduced);
		}
		return validateDateOrder;
	}
}
