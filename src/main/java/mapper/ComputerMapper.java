package mapper;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Computer;
import model.Company.Builder;
import persistence.CompanyDAO;

@Component("ComputerMapper")
public class ComputerMapper {

	private Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
	
	@Autowired
	CompanyDAO companyDAO ;

	public ComputerMapper() {
	}

	public Computer dtoToModel(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		Computer.Builder builder = new Computer.Builder();
		try {

			if (computerDTO.getId() != null) 
				builder.withId(Long.parseLong(computerDTO.getId()));
			
			
				builder.withName(computerDTO.getName());
						
				
			
			if (computerDTO.getIntroduced()==null)
				builder.withIntroduced(null);
			else
				builder.withIntroduced(computerDTO.getIntroduced());

			if (computerDTO.getDiscontinued()== null)
				builder.withDiscontinued(null);
			else
				builder.withDiscontinued(computerDTO.getDiscontinued());

			builder.withCompanyID(companyDAO.findByName(computerDTO.getCompanyName()));
			logger.info(computerDTO.toString());
		} catch (NullPointerException e) {
			logger.error("null exception dtoToModel", e);

		}
		Computer computer = builder.build();
		return computer;
	}

	public ComputerDTO modelToDto(Computer computer) {

		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(Long.toString(computer.getId()));
		computerDTO.setName(computer.getName());

		if (computer.getIntroduced() != null)
			computerDTO.setIntroduced(computer.getIntroduced());

		if (computer.getDiscontinued() != null)
			computerDTO.setDiscontinued(computer.getDiscontinued());

		computerDTO.setCompanyName(companyDAO.findById(computer.getCompanyID()).getName());

		return computerDTO;
	}

	public long idToInt(String id) {

		return Integer.parseInt(id);
	}

}