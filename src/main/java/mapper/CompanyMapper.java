package mapper;

import dto.CompanyDTO;
import model.Company;

public class CompanyMapper {
	
	
	public Company dtoToModel(CompanyDTO companyDTO) {
		Company company = new Company();
		company.setId(Long.parseLong(companyDTO.getId()));
		company.setName(companyDTO.getName());
		return company;
	}

	
	public CompanyDTO modelToDto(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(Long.toString(company.getId()));
		companyDTO.setName(company.getName());
		return companyDTO;
	}
	
}

