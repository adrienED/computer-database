package com.excilys.CDB.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.excilys.CDB.core.model.Company;
	
public interface CompanyRepository extends JpaRepository<Company, Long> {
				
		@Query("FROM Company c WHERE c.name = :name")
		Company findByName(@Param("name") String name);		
	    
	}