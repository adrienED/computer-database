package validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ComputerDTO;


public class ComputerValidator {
	
	private static ComputerValidator instance;
	
	public static ComputerValidator getInstance() {
		if (instance == null)
			instance = new ComputerValidator();
		return instance;
	}
	
	Logger logger = LoggerFactory.getLogger(ComputerValidator.class);

	public boolean validate(ComputerDTO computerDTO) {
		
		boolean validateName, validateDateIntroduced 
		,validateDateDiscontinued, validateDateOrder,
		validateNameCompany;
		
		//validateID = validateId(computerDTO.getCompanyName());
		
		if (computerDTO.getName() != null) validateName = true;
		else validateName = false;
		
		if (computerDTO.getCompanyName() != null) validateNameCompany = true;
		else validateNameCompany = false;
		
		if (computerDTO.getIntroduced() !="")
			validateDateIntroduced =validateDate(computerDTO.getIntroduced());
		else validateDateIntroduced =true;
		
		if (computerDTO.getDiscontinued() !="")
			validateDateDiscontinued =validateDate(computerDTO.getDiscontinued());
		else validateDateDiscontinued =true;
		
		if (computerDTO.getIntroduced() !="" || computerDTO.getDiscontinued() !="")
			validateDateOrder = validateDateOrder(computerDTO.getIntroduced(), computerDTO.getDiscontinued());
		else validateDateOrder =true;

		
		this.logger.info(validateName+ " "+validateDateIntroduced 
				+" "+validateDateDiscontinued+" "+validateDateOrder+" "+validateNameCompany);
		
		if ( validateName == true && validateDateIntroduced ==true
				&& validateDateDiscontinued==true && validateDateOrder == true) 
			return true;
		else return false;
	
	}
	/*
	private boolean validateId(String id) {
		boolean validateId = false;
		try {
			int localId = Integer.parseInt(id);
			
			if (localId > 0) {
				validateId = true;
			}
			
		} catch (Exception e) {

			this.logger.error(e.getMessage(),e);	
		}
		return validateId;
	}
	*/
	
	 
	
	private boolean validateDate(String date) {
		boolean validate = false;
		if ( date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			try {
				LocalDate localDate = LocalDate.parse(date);
				LocalDate minDate = LocalDate.parse("1970-01-01");
				if (localDate.isAfter(minDate)) validate = true;
			} catch (Exception e) {
				this.logger.error(e.getMessage(),e);
				
			}
			
		}
		return validate;
	}
	
	private boolean validateDateOrder(String introducedString, String discontinuedString) {
		boolean validateDateOrder=false;
		if ( introducedString.matches("\\d{4}-\\d{2}-\\d{2}") && discontinuedString.matches("\\d{4}-\\d{2}-\\d{2}"));
		
		try {
			LocalDate localIntroduceDate = LocalDate.parse(introducedString);
			LocalDate localDiscontinudDate = LocalDate.parse(discontinuedString);
			
			
			if (localIntroduceDate.isBefore(localDiscontinudDate)) validateDateOrder = true;
		} catch (Exception e) {
				this.logger.error(e.getMessage(),e);
			
			}
	
		return validateDateOrder;
		
	}
}
	
	



