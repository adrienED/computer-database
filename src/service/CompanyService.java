package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.Computer;
import persistence.CompanyDAO;
import persistence.ComputerDAO;

public class CompanyService {
	
public static List<Company> ListComputer() throws SQLException {
		
		List<Company> listCompany = new ArrayList<>();

		CompanyDAO cdaoa = new CompanyDAO();
		listCompany=cdaoa.listAllCompany();
		return listCompany;
		
	}

}
