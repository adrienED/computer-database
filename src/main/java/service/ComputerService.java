package service;

import org.springframework.stereotype.Component;

import mapper.ComputerMapper;
import model.Computer;
import repository.ComputerRepository;

@Component("ComputerService")
public class ComputerService {

	private final ComputerMapper computerMapper;
	private final ComputerRepository computerRepository;

	public ComputerService(ComputerMapper computerMapper, ComputerRepository computerRepository) {
		super();
		this.computerMapper = computerMapper;
		this.computerRepository = computerRepository;
	}

	public Computer create(Computer computer) {
		return computerRepository.save(computer);
	}
	/*
	public void update(Computer computer)
			throws   ComputerNotFoundException {
		this.computerDAO.update(computer);
	}

	public boolean delete(long id) {
		return this.computerDAO.delete(id);
	}

	public Computer findById(String id) throws ComputerNotFoundException {
		return this.computerDAO.findById(this.computerMapper.idToLong(id));
	}

	public List<Computer> getAll(int limit, int offset) {

		List<Computer> computerList = computerDAO.getAll(limit, offset);

		return computerList;
	}

	public List<Computer> getAllOrderedBy(int limit, int offset, String orderByParameter) {

		List<Computer> computerList = computerDAO.getAllOrderedBy(limit, offset, orderByParameter);

		return computerList;
	}

	public int getNbOfComputer() {
		int nbOfComputer = computerDAO.getNbOfComputer();
		return nbOfComputer;

	}

	public List<Computer> search(String keyword)  {

		List<Computer> computerList;
		computerList = computerDAO.getSearchComputer(keyword);

		return computerList;

	}
	*/
}