package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import persistence.CompanyDAO;

public class CompanyService {
	
	public static List<Company> ListCompany() throws SQLException {
		List<Company> AllCompany = new ArrayList<>();
		AllCompany = CompanyDAO.listAllCompany();
		return AllCompany;
		
	}

}
