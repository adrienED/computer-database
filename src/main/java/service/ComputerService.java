package service;


import java.util.List;
import java.util.stream.Collectors;

import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import mapper.ComputerMapper;
import model.Computer;
import persistence.ComputerDAO;

public class ComputerService {
	
	    private ComputerService()
	    {}

	    private static ComputerService INSTANCE = null;

	    public static ComputerService getInstance()
	    {           
	        if (INSTANCE == null)
	        {   INSTANCE = new ComputerService(); 
	        }
	        return INSTANCE;
	    }

	private ComputerMapper computerMapper = ComputerMapper.getInstance();
	private ComputerDAO computerDAO =ComputerDAO.getInstance();
		
	 
	public long create(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		
		return this.computerDAO.create(computerMapper.dtoToModel(computerDTO));
	};
	
	public boolean update(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		return this.computerDAO.update(this.computerMapper.dtoToModel(computerDTO));
	};
	
	public boolean delete(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		return this.computerDAO.delete(this.computerMapper.dtoToModel(computerDTO));
	};
	
	public ComputerDTO findById(String id) throws NotFoundException, InvalidDateChronology, ComputerNotFoundException {
		return this.computerMapper.modelToDto(this.computerDAO.findById(this.computerMapper.idToInt(id)));
	};
	
	public List<ComputerDTO> getAll(int limit, int offset) throws InvalidDateChronology{
		
		List<Computer> computerList = computerDAO.getAll(limit, offset);
		System.out.println(computerList);
		List<ComputerDTO> computerDtoList = (List<ComputerDTO>) computerList.stream().map(s -> computerMapper.modelToDto(s)).collect(Collectors.toList());
		System.out.println(computerDtoList);
		return computerDtoList;
}
	
	public int getNbOfComputer () {
		int nbOfComputer = computerDAO.getNbOfComputer();
		return nbOfComputer;
		
	}
	
	
	
}