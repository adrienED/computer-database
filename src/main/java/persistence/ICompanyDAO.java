package persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.sun.xml.bind.v2.model.core.ID;

import model.Company;
import model.Computer;

public interface ICompanyDAO extends Repository<Computer, Long>{
	
		Optional<Company> findById(ID primaryKey);
	
	   List<Company> listCompanies();
	   

}
