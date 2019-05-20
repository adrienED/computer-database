package service;

import java.util.List;

import org.springframework.stereotype.Component;

import mapper.CompanyMapper;
import model.Company;
import persistence.CompanyDAO;

@Component("CompanyService")
public class CompanyService {

	protected CompanyMapper companyMapper;
	protected CompanyDAO companyDAO;

	public CompanyService(CompanyMapper companyMapper, CompanyDAO companyDAO) {
		this.companyMapper = companyMapper;
		this.companyDAO=companyDAO;
	}

	public List<Company> getAll(int limit, int offset) {

		List<Company> companyList = companyDAO.getAll(limit, offset);

		return companyList;
	}

	public Company getNameById(long id) {
		Company company = companyDAO.findById(id);
		return company;
	}

	public void deleteCompanyById(long id) {
		companyDAO.deleteCompanyById(id);
	}

}
