package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mapper.CompanyMapper;
import model.Company;
import persistence.CompanyDAO;

@Component("CompanyService")
public class CompanyService {
	
	@Autowired
	CompanyMapper companyMapper;
	@Autowired
	CompanyDAO companyDAO;

	public CompanyService() {
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
