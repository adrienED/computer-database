package mapper;


import java.time.LocalDate;
import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Company;
import model.Computer;
import persistence.CompanyDAO;

public class ComputerMapper  {

	public ComputerMapper(CompanyDAO companyDAO) {
		super();
		this.companyDAO = companyDAO;
	}

	private CompanyDAO companyDAO;

	
	public Computer dtoToModel(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		Computer computer = new Computer();
		
		if (computerDTO.getId() != null) {
			computer.setId(Long.parseLong(computerDTO.getId()));
		}
		
		if (!computerDTO.getIntroducedDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new InvalidDateValueException(computerDTO.getIntroducedDate());
		}
		if (!computerDTO.getDiscontinuedDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new InvalidDateValueException(computerDTO.getDiscontinuedDate());
		}
		else {
			computer.setName(computerDTO.getName());
			computer.setIntroduced(LocalDate.parse(computerDTO.getIntroducedDate()));
			computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinuedDate()));
			Company company = companyDAO.findById(Long.parseLong(computerDTO.getCompanyDTO().getId()));

			computer.setCompany(company);
			
		}
		return computer;
	}

	
	public ComputerDTO modelToDto(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(Long.toString(computer.getId()));
		computerDTO.setName(computer.getName());
		if (computer.getIntroduced() != null) {
			computerDTO.setIntroducedDate(computer.getIntroduced().toString());
		}
		if (computer.getDiscontinued() != null) {
			computerDTO.setDiscontinuedDate(computer.getDiscontinued().toString());
		}
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(Long.toString(computer.getCompany().getId()));
		computerDTO.setCompanyDTO(companyDTO);
		return computerDTO;
	}


	public long idToInt(String id) {
		
		return Integer.parseInt(id);
	}

}