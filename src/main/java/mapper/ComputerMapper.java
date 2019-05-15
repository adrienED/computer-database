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
import persistence.CompanyDAO;

@Component("ComputerMapper")
public class ComputerMapper {

	private Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
	
	@Autowired
	CompanyDAO companyDAO ;

	public ComputerMapper() {
	}

	public Computer dtoToModel(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		Computer computer = new Computer();
		try {

			if (computerDTO.getId() != null)
				computer.setId(Long.parseLong(computerDTO.getId()));

			computer.setName(computerDTO.getName());

			if (computerDTO.getIntroduced()==null)
				computer.setIntroduced(null);
			else
				computer.setIntroduced(computerDTO.getIntroduced());

			if (computerDTO.getDiscontinued()== null)
				computer.setDiscontinued(null);
			else
				computer.setDiscontinued(computerDTO.getDiscontinued());

			computer.setCompanyID(companyDAO.findByName(computerDTO.getCompanyName()));
			logger.info(computerDTO.toString());
		} catch (NullPointerException e) {
			logger.error("null exception dtoToModel", e);

		}
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