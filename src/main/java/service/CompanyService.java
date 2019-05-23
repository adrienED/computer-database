package service;

import java.util.List;

import org.springframework.stereotype.Component;

import model.Company;
import persistence.CompanyDAO;

@Component("CompanyService")
public class CompanyService {


	private final CompanyDAO companyDAO;

	public CompanyService( CompanyDAO companyDAO) {
		super();
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
