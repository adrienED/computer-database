package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Computer;
import persistence.ComputerDAO;

public class ComputerService {
	
	ComputerDAO cdao = new ComputerDAO();
	
	public static List<Computer> ListComputer() throws SQLException {
		
		List<Computer> testa = new ArrayList<>();

		ComputerDAO cdaoa = new ComputerDAO();
		testa=cdaoa.listAllComputer();
		return testa;

	}

}
