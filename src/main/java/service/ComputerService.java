package service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import exception.ComputerNotFoundException;
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

	
	public void update(Computer computer)
			throws   ComputerNotFoundException {
		computerRepository.save(computer);
	}


	public Optional<Computer> findById(String id) throws ComputerNotFoundException {
		return this.computerRepository.findById(this.computerMapper.idToLong(id));
	}

	/*
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