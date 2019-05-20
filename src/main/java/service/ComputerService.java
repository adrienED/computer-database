package service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import mapper.ComputerMapper;
import model.Computer;
import persistence.ComputerDAO;

@Component("ComputerService")
public class ComputerService {

	public ComputerService(ComputerMapper computerMapper, ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
		this.computerMapper = computerMapper;

	}


	protected ComputerMapper computerMapper;

	protected ComputerDAO computerDAO;

	public long create(Computer computer) throws InvalidDateValueException, InvalidDateChronology, SQLException {
		return this.computerDAO.create(computer);
	}

	public void update(Computer computer) throws InvalidDateValueException, InvalidDateChronology, SQLException, ComputerNotFoundException {
		 this.computerDAO.update(computer);
	}

	public boolean delete(Computer computer) throws InvalidDateValueException, InvalidDateChronology, SQLException, ComputerNotFoundException {
		return this.computerDAO.delete(computer);
	}

	public Computer findById(String id) throws NotFoundException, InvalidDateChronology, ComputerNotFoundException {
		return this.computerDAO.findById(this.computerMapper.idToInt(id));
	}

	public List<Computer> getAll(int limit, int offset) throws InvalidDateChronology {

		List<Computer> computerList = computerDAO.getAll(limit, offset);

		return computerList;
	}

	public List<Computer> getAllOrderedBy(int limit, int offset, String orderByParameter)
			throws InvalidDateChronology {

		List<Computer> computerList = computerDAO.getAllOrderedBy(limit, offset, orderByParameter);

		return computerList;
	}

	public int getNbOfComputer() throws SQLException {
		int nbOfComputer = computerDAO.getNbOfComputer();
		return nbOfComputer;

	}

	public List<Computer> search(String keyword) throws InvalidDateChronology {

		List<Computer> computerList;
		computerList = computerDAO.getSearchComputer(keyword);
		
		return computerList;

	}
}