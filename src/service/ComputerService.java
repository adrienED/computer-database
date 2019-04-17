package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Computer;
import persistence.ComputerDAO;

public class ComputerService {
	
	ComputerDAO cdao = new ComputerDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
	
	public static List<Computer> ListComputer() throws SQLException {
		
		List<Computer> testa = new ArrayList<>();

		ComputerDAO cdaoa = new ComputerDAO("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","admincdb","qwerty1234");
		testa=cdaoa.listAllComputer();
		return testa;

		 
		
	}

}
