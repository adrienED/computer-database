package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import model.Computer;
import persistence.CompanyDAO;

@Component("ComputerMapper")
public class ComputerMapper implements RowMapper<Computer> {

	private Logger logger = LoggerFactory.getLogger(ComputerMapper.class);

	
	private final CompanyDAO companyDAO;

	

	public ComputerMapper(CompanyDAO companyDAO) {
		super();
		
		this.companyDAO = companyDAO;
	}

	@Override
	public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		if (resultSet.getLong("id") == 0L || resultSet.getString("name") == null)
			return null;

		Computer.Builder builder = new Computer.Builder();

		builder.withId(resultSet.getLong("id"));

		builder.withName(resultSet.getString("name"));
		if (resultSet.getDate("introduced") != null) {
			builder.withIntroduced(resultSet.getDate("introduced").toLocalDate());
		}
		if (resultSet.getDate("discontinued") != null) {
			builder.withDiscontinued(resultSet.getDate("discontinued").toLocalDate());
		}

		builder.withCompanyID(resultSet.getLong("company_id"));
		Computer computer = builder.build();

		return computer;
	}

	public Computer dtoToModel(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		Computer.Builder builder = new Computer.Builder();
		try {

			if (computerDTO.getId() != null)
				builder.withId(Long.parseLong(computerDTO.getId()));

			builder.withName(computerDTO.getName());

			if (computerDTO.getIntroduced()==null || computerDTO.getIntroduced() =="")
				builder.withIntroduced(null);
			else
				builder.withIntroduced(LocalDate.parse(computerDTO.getIntroduced()));

			if (computerDTO.getDiscontinued()==null || computerDTO.getDiscontinued()=="")
				builder.withDiscontinued(null);
			else
				builder.withDiscontinued(LocalDate.parse(computerDTO.getDiscontinued()));
			long id = companyDAO.findByName(computerDTO.getCompanyName());
			if ( id == 0)
			builder.withCompanyID(0);
			else {
				builder.withCompanyID(id);
			}
			logger.info(computerDTO.toString());
		} catch (NullPointerException e) {
			logger.error("null exception dtoToModel", e);

		}
		Computer computer = builder.build();
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
		computerDTO.setCompanyName(companyDAO.findById(computer.getCompanyID()).getName());

		return computerDTO;
	}

	public long idToLong(String id) {

		return Long.parseLong(id);
	}

}