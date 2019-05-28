package repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Company;

	public interface CompanyRepository extends JpaRepository<Company, Long> {
		
		
		@Query("FROM Company c WHERE c.name = :name")
		Company findByName(@Param("name") String name);
		
	   
		
		
		
		
	    
	}