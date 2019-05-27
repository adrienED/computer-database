package service;

import java.util.List;

import org.springframework.stereotype.Component;

import model.Company;
import persistence.CompanyDAO;

@Component("CompanyService")
public class CompanyService implements ICompanyService {


	private final CompanyDAO companyDAO;

	public CompanyService( CompanyDAO companyDAO) {
		super();
		this.companyDAO = companyDAO;
	}

	@Override
	public List<Company> listCompany() {
		
		 return companyDAO.listCompanies();
	}
}