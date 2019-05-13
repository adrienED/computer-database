package service;

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

	public ComputerService() {
	}

	@Autowired
	private ComputerMapper computerMapper;
	private ComputerDAO computerDAO;

	public long create(Computer computer) throws InvalidDateValueException, InvalidDateChronology {
		return this.computerDAO.create(computer);
	}

	public boolean update(Computer computer) throws InvalidDateValueException, InvalidDateChronology {
		return this.computerDAO.update(computer);
	}

	public boolean delete(Computer computer) throws InvalidDateValueException, InvalidDateChronology {
		return this.computerDAO.delete(computer);
	}

	public ComputerDTO findById(String id) throws NotFoundException, InvalidDateChronology, ComputerNotFoundException {
		return this.computerMapper.modelToDto(this.computerDAO.findById(this.computerMapper.idToInt(id)));
	}

	public List<ComputerDTO> getAll(int limit, int offset) throws InvalidDateChronology {

		List<Computer> computerList = computerDAO.getAll(limit, offset);
		List<ComputerDTO> computerDtoList = (List<ComputerDTO>) computerList.stream()
				.map(s -> computerMapper.modelToDto(s)).collect(Collectors.toList());
		return computerDtoList;
	}

	public List<ComputerDTO> getAllOrderedBy(int limit, int offset, String orderByParameter)
			throws InvalidDateChronology {

		List<Computer> computerList = computerDAO.getAllOrderedBy(limit, offset, orderByParameter);
		List<ComputerDTO> computerDtoList = (List<ComputerDTO>) computerList.stream()
				.map(s -> computerMapper.modelToDto(s)).collect(Collectors.toList());
		return computerDtoList;
	}

	public int getNbOfComputer() {
		int nbOfComputer = computerDAO.getNbOfComputer();
		return nbOfComputer;

	}

	public List<ComputerDTO> search(String keyword) throws InvalidDateChronology {

		List<Computer> computerList;
		computerList = computerDAO.getSearchComputer(keyword);
		List<ComputerDTO> computerDtoList = (List<ComputerDTO>) computerList.stream()
				.map(s -> computerMapper.modelToDto(s)).collect(Collectors.toList());

		return computerDtoList;

	}
}