package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Computer;


public interface ComputerRepository extends JpaRepository<Computer, Long> {
		
	
	@Query("SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS companyID FROM Computer AS A LEFT JOIN Company AS B ON A.companyID = B.id WHERE B.name like %:search%")
	public List<Computer> findSomething(@Param("search") String search, Pageable pageable);
	
	@Query("FROM Computer c WHERE c.name = :name")
	Computer findByName(@Param("name") String name);
	
	
}
