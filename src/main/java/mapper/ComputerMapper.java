package mapper;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Computer;
import persistence.CompanyDAO;

public class ComputerMapper {

	private Logger logger = LoggerFactory.getLogger(ComputerMapper.class);

	private CompanyDAO companyDAO = CompanyDAO.getInstance();

	private ComputerMapper() {
	}

	private static ComputerMapper INSTANCE = null;

	public static ComputerMapper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ComputerMapper();
		}
		return INSTANCE;
	}

	public Computer dtoToModel(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		Computer computer = new Computer();
		try {

			if (computerDTO.getId() != null)
				computer.setId(Long.parseLong(computerDTO.getId()));

			computer.setName(computerDTO.getName());

			if (computerDTO.getIntroduced() == "")
				computer.setIntroduced(null);
			else
				computer.setIntroduced(LocalDate.parse(computerDTO.getIntroduced()));

			if (computerDTO.getDiscontinued() == "")
				computer.setDiscontinued(null);
			else
				computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued()));

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
			computerDTO.setIntroduced(computer.getIntroduced().toString());

		if (computer.getDiscontinued() != null)
			computerDTO.setDiscontinued(computer.getDiscontinued().toString());

		computerDTO.setCompanyName(companyDAO.findById(computer.getCompanyID()).getName());

		return computerDTO;
	}

	public long idToInt(String id) {

		return Integer.parseInt(id);
	}

}