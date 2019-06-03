package com.excilys.CDB.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.CDB.binding.dto.ComputerDTO;
import com.excilys.CDB.core.model.Company;
import com.excilys.CDB.core.model.Computer;

@Component("ComputerMapper")
public class ComputerMapper implements RowMapper<Computer> {

	private Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
	

	public ComputerMapper() {
		
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



		return computer;
	}
	

	public Computer dtoToModel(ComputerDTO computerDTO){
		Computer computer=new Computer();
		Company company = new Company();
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

				company.setId(computerDTO.getCompany_id());	
			if (computerDTO.getCompanyName() != null || computerDTO.getDiscontinued()=="")
				company.setName(computerDTO.getCompanyName());
			
			computer.setCompany(company);
			

		} catch (NullPointerException e) {
			logger.error("null exception dtoToModel", e);

		}
		
		return computer;
	}

	public ComputerDTO modelToDto(Computer computer) {

		ComputerDTO computerDTO = new ComputerDTO();
		
		if (computer.getId() !=0) {computerDTO.setId(Long.toString(computer.getId()));}

		if (computer.getName() !=null ) {computerDTO.setName(computer.getName());}
		
		if (computer.getIntroduced() != null)
			computerDTO.setIntroduced(computer.getIntroduced().toString());

		if (computer.getDiscontinued() != null)
			computerDTO.setDiscontinued(computer.getDiscontinued().toString());
		
		if(computer.getCompany() != null) {
		
		computerDTO.setCompanyName(computer.getCompany().getName());
		computerDTO.setCompany_id(computer.getCompany().getId());
		}
		else {
			computerDTO.setCompanyName("");
		}
		return computerDTO;
	}

	public long idToLong(String id) {

		return Long.parseLong(id);
	}

}