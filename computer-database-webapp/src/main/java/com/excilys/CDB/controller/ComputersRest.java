package com.excilys.CDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.CDB.binding.dto.ComputerDTO;
import com.excilys.CDB.binding.mapper.ComputerMapper;
import com.excilys.CDB.binding.validator.ComputerValidator;
import com.excilys.CDB.core.exception.EmptyCompanyNameException;
import com.excilys.CDB.core.exception.EmptyComputerNameException;
import com.excilys.CDB.core.exception.InvalidDateChronology;
import com.excilys.CDB.core.exception.InvalidDateValueException;
import com.excilys.CDB.core.model.Computer;
import com.excilys.CDB.persistence.repository.ComputerRepository;
import com.excilys.CDB.service.ComputerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/computers")
public class ComputersRest {

	@Autowired
	ComputerService computerService;

	@Autowired
	ComputerValidator computerValidator;

	@Autowired
	ComputerMapper computerMapper;

	@Autowired
	ComputerRepository computerRepository;

	ObjectMapper mapper = new ObjectMapper();

	@GetMapping(produces = "application/json")
	public String getAllComputersJSON() {

		String jsonResult = null;
		try {
			jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(computerService.findAllComputers());
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		return jsonResult;
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public String getComputer(@PathVariable String id) throws JsonProcessingException {
		String jsonResult = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(computerMapper.modelToDto(computerService.findById(id).get()));

		return jsonResult;
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Computer> deleteComputer(@PathVariable String id) {
		computerService.deleteComputer(Long.parseLong(id));
		return new ResponseEntity<Computer>(HttpStatus.NO_CONTENT);

	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") String id, @RequestBody ComputerDTO computerDTO)
			throws EmptyComputerNameException, EmptyCompanyNameException, InvalidDateChronology,
			InvalidDateValueException, Exception {
		Computer computer = new Computer();
		if (computerValidator.validate(computerDTO)) {
			computer = computerMapper.dtoToModel(computerDTO);
			computerService.update(computer);
		} else {
			throw new Exception();
		}

	}

	@PostMapping(value = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody ComputerDTO computerDTO) throws EmptyComputerNameException,
			EmptyCompanyNameException, InvalidDateChronology, InvalidDateValueException {
		Computer computer = new Computer();
		
		System.out.println(computerDTO);
		System.out.println(computerMapper.dtoToModel(computerDTO));
		System.out.println(computerValidator.validate(computerDTO));
			computer = computerMapper.dtoToModel(computerDTO);
		computerService.create(computer);
		
	}
	
	
	@GetMapping(value = "/{name}", produces = "application/json")
	public String searchName(@PathVariable String name) throws JsonProcessingException {
		String jsonResult = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(computerMapper.modelToDto(computerService.search(name,PageRequest.of(0, 10))));

		return jsonResult;
	}
	
	

}