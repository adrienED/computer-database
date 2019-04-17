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

		CompanyDAO cdaoa = new CompanyDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
		listCompany=cdaoa.listAllCompany();
		return listCompany;
		
	}

}
