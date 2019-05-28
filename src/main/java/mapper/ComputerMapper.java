package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Company;
import model.Computer;
import repository.CompanyRepository;
import service.CompanyService;

@Component("ComputerMapper")
public class ComputerMapper implements RowMapper<Computer> {

	private Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
	
	CompanyService companyService;

	public ComputerMapper(CompanyService companyService) {
		this.companyService=companyService;
	}

	@Override
	public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		if (resultSet.getLong("id") == 0L || resultSet.getString("name") == null)
			return null;

		Computer computer = new Computer();

		computer.setId(resultSet.getLong("id"));

		computer.setName(resultSet.getString("name"));
		if (resultSet.getDate("introduced") != null) {
			computer.setIntroduced(resultSet.getDate("introduced").toLocalDate());
		}
		if (resultSet.getDate("discontinued") != null) {
			computer.setDiscontinued(resultSet.getDate("discontinued").toLocalDate());
		}

		computer.setCompanyID(resultSet.getLong("company_id"));


		return computer;
	}

	public Computer dtoToModel(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		Computer computer=new Computer();
		try {

			if (computerDTO.getId() != null)
				computer.setId(Long.parseLong(computerDTO.getId()));

			computer.setName(computerDTO.getName());

			if (computerDTO.getIntroduced()==null || computerDTO.getIntroduced() =="")
				computer.setIntroduced(null);
			else
				computer.setIntroduced(LocalDate.parse(computerDTO.getIntroduced()));

			if (computerDTO.getDiscontinued()==null || computerDTO.getDiscontinued()=="")
				computer.setDiscontinued(null);
			else
				computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued()));
			long id = companyService.findByName(computerDTO.getCompanyName());
			if ( id == 0)
				computer.setCompanyID(0);
			else {
				computer.setCompanyID(id);
			}
			logger.info(computerDTO.toString());
		} catch (NullPointerException e) {
			logger.error("null exception dtoToModel", e);

		}
		
		return computer;
	}

	public ComputerDTO modelToDto(Computer computer) {

		ComputerDTO computerDTO = new ComputerDTO();
		System.out.println(computer.getId());
		
		if (computer.getId() !=0)
		computerDTO.setId(Long.toString(computer.getId()));
		if (computer.getName() !=null )
		computerDTO.setName(computer.getName());

		if (computer.getIntroduced() != null)
			computerDTO.setIntroduced(computer.getIntroduced().toString());

		if (computer.getDiscontinued() != null)
			computerDTO.setDiscontinued(computer.getDiscontinued().toString());
		
		System.out.println(computer.getCompanyID());
		Optional<Company> company = companyService.findById(computer.getCompanyID());
		computerDTO.setCompanyName(companyService.findById(computer.getCompanyID()).get().getName());

		return computerDTO;
	}

	public long idToLong(String id) {

		return Long.parseLong(id);
	}

}