package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import dto.CompanyDTO;
import model.Company;
import model.Company.Builder;

@Component("CompanyMapper")
public class CompanyMapper implements RowMapper<Company> {

	Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

	public CompanyMapper() {
	}

	@Override
	public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Company company = new Company.Builder().withParameter(resultSet.getLong("id"), resultSet.getString("name"))
				.build();

		return company;
	}

	public Company dtoToModel(CompanyDTO companyDTO) {
		Builder builder = new Company.Builder();
		try {
			builder.withParameter(Long.parseLong(companyDTO.getId()), companyDTO.getName());
		} catch (NullPointerException e) {
			logger.error("dtoToModel null input", e);
		}
		Company company = builder.build();
		return company;
	}

	public CompanyDTO modelToDto(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		try {

			companyDTO.setId(Long.toString(company.getId()));
			companyDTO.setName(company.getName());

		} catch (NullPointerException e) {
			logger.error("ModeleTODto null input", e);
		}
		return companyDTO;
	}

}
