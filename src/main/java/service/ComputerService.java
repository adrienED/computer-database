package service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public int getNbOfComputer() {
		long nbOfComputer = computerRepository.count();
		return (int) nbOfComputer;
	}
	
	public List <Computer> findAllComputers (){
		return computerRepository.findAll();
	}
	
	public List<Computer> findAllPagined(Pageable pageable){
		
		Page<Computer> comPage =computerRepository.findAll(pageable);
	
		List<Computer> computers = comPage.getContent();

		return  computers;
	}
	
	
	public void deleteComputer (long id) {
		computerRepository.deleteById(id);
		
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

	

	}

	public List<Computer> search(String keyword)  {

		List<Computer> computerList;
		computerList = computerDAO.getSearchComputer(keyword);

		return computerList;

	}
	*/
}