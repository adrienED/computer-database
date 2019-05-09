package mapper;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.CompanyDTO;
import model.Company;
import persistence.CompanyDAO;

public class CompanyMapper {

	Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	private CompanyMapper() {
	}

	private static CompanyMapper INSTANCE = null;

	public static CompanyMapper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CompanyMapper();
		}
		return INSTANCE;
	}

	public Company dtoToModel(CompanyDTO companyDTO) {
		Company company = new Company();
		try {
			company.setId(Long.parseLong(companyDTO.getId()));
			company.setName(companyDTO.getName());
		} catch (NullPointerException e) {
			System.out.print("dtoToModel null input");
			logger.error("dtoToModel null input", e);

		}
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
