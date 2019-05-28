package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Company;
import repository.CompanyRepository;


@Component("CompanyService")
public class CompanyService  {


@Autowired
CompanyRepository companyRepository;


	public CompanyService() {
	}


	public List<Company> getAll() {
		 return companyRepository.findAll();
	}
	
	public long findByName(String name) {
		return companyRepository.findByName(name).getId();
	}
	
	public Optional<Company> findById (long id) {
		return companyRepository.findById(id);
	}

	
	
}