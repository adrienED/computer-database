package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Company;

	public interface CompanyRepository extends JpaRepository<Company, Long> {
		
		@Query("SELECT id FROM company WHERE name = ?")
	    long findByName(@Param("name")String name);

	}