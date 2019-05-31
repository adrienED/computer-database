package service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import model.Company;
import repository.CompanyRepository;
import repository.ComputerRepository;


@Component("CompanyService")
public class CompanyService  {



	private final CompanyRepository companyRepository;


	private final ComputerRepository computerRepository;


	public CompanyService(CompanyRepository companyRepository, ComputerRepository computerRepository) {
		this.companyRepository=companyRepository;
		this.computerRepository=computerRepository;
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
	
	
	public void deleteCompany(long id) {
		computerRepository.deleteByCompanyID(id);
		companyRepository.deleteById(id);
		System.out.println("supp ok");
	}

}