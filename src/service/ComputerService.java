package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Computer;
import persistence.ComputerDAO;
import ui.AppMain;

public class ComputerService {

	public static List<Computer> ListComputer() throws SQLException {
		ComputerDAO cdao = new ComputerDAO();
		List<Computer> testa = new ArrayList<>();

		ComputerDAO cdaoa = new ComputerDAO();
		testa = cdaoa.listAllComputer();
		return testa;

	}

	public static void InsertComputer(Computer computer) throws SQLException {
		ComputerDAO cdao = new ComputerDAO();
		cdao.insertComputer(computer);
	}

	public static Computer DetailsComputer(Computer computer) throws SQLException {
		Computer computerD = new Computer();
		ComputerDAO cdao = new ComputerDAO();
		computerD = cdao.getComputer(computer.getId());
		return computerD;
	}

	public static void UpdateComputer(Computer computer) throws SQLException {
		ComputerDAO cdao = new ComputerDAO();
		if(computer.getId()==0) { System.out.println("Computer not found");}
		else {
		cdao.updateComputer(computer);
		}
	}

	public static void DeleteComputer() throws SQLException {

		String id = AppMain.DeleteComputer();
		long idD = Long.valueOf(id).longValue();

		Computer computer = new Computer(idD);
		ComputerDAO cdao = new ComputerDAO();

		cdao.deleteComputer(computer);
	}

}
