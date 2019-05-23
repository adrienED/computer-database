package service;

import java.util.List;

import org.springframework.stereotype.Component;

import mapper.CompanyMapper;
import model.Company;
import persistence.CompanyDAO;

@Component("CompanyService")
public class CompanyService {

	private final CompanyMapper companyMapper;

	private final CompanyDAO companyDAO;

	public CompanyService(CompanyMapper companyMapper, CompanyDAO companyDAO) {
		super();
		this.companyMapper = companyMapper;
		this.companyDAO = companyDAO;
	}

	public List<Company> getAll() {

		List<Company> companyList = companyDAO.getAll();

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
