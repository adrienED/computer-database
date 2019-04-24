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

	private ComputerMapper mapper = ComputerMapper.getInstance();
	private ComputerDAO dao =ComputerDAO.getInstance();
		
	 
	public long create(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		
		return this.dao.create(mapper.dtoToModel(computerDTO));
	};
	
	public boolean update(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		return this.dao.update(this.mapper.dtoToModel(computerDTO));
	};
	
	public boolean delete(ComputerDTO computerDTO) throws InvalidDateValueException, InvalidDateChronology {
		return this.dao.delete(this.mapper.dtoToModel(computerDTO));
	};
	
	public ComputerDTO findById(String id) throws NotFoundException, InvalidDateChronology, ComputerNotFoundException {
		return this.mapper.modelToDto(this.dao.findById(this.mapper.idToInt(id)));
	};
	
	public List<ComputerDTO> getAll(int limit, int offset) throws InvalidDateChronology{
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		List<Computer> computerList = computerDAO.getAll(limit, offset);
		List<ComputerDTO> computerDtoList = (List<ComputerDTO>) computerList.stream().map(s -> mapper.modelToDto(s)).collect(Collectors.toList());

		return computerDtoList;
}
	
	
	
}