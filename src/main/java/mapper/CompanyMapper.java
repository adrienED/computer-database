package mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dto.CompanyDTO;
import model.Company;
import model.Company.Builder;

@Component("CompanyMapper")
	public class CompanyMapper {

	Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

	public CompanyMapper() {
	}

	public Company dtoToModel(CompanyDTO companyDTO) {
		Builder builder = new Company.Builder();
		try {
			builder.withParameter(Long.parseLong(companyDTO.getId()), companyDTO.getName());
		} catch (NullPointerException e) {
			System.out.print("dtoToModel null input");
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
			System.out.print("modelToDto null");
			logger.error("ModeleTODto null input", e);
		}
		return companyDTO;
	}

}
