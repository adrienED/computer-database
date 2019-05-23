package service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.NotFoundException;
import mapper.ComputerMapper;
import model.Computer;
import persistence.ComputerDAO;

@Component("ComputerService")
public class ComputerService {

	private final ComputerMapper computerMapper;

	private final ComputerDAO computerDAO;

	public ComputerService(ComputerMapper computerMapper, ComputerDAO computerDAO) {
		super();
		this.computerMapper = computerMapper;
		this.computerDAO = computerDAO;
	}

	public long create(Computer computer) throws SQLException {
		return this.computerDAO.create(computer);
	}

	public void update(Computer computer)
			throws  SQLException, ComputerNotFoundException {
		this.computerDAO.update(computer);
	}

	public boolean delete(long id)
			throws  SQLException, ComputerNotFoundException {
		return this.computerDAO.delete(id);
	}

	public Computer findById(String id) throws NotFoundException, InvalidDateChronology, ComputerNotFoundException {
		return this.computerDAO.findById(this.computerMapper.idToLong(id));
	}

	public List<Computer> getAll(int limit, int offset) throws InvalidDateChronology {

		List<Computer> computerList = computerDAO.getAll(limit, offset);

		return computerList;
	}

	public List<Computer> getAllOrderedBy(int limit, int offset, String orderByParameter) throws InvalidDateChronology {

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