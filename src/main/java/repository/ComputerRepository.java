package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import model.Computer;


public interface ComputerRepository extends JpaRepository<Computer, Long> {
			
	
	@Query("select c FROM Computer c LEFT JOIN Company B ON c.company_id = B.id WHERE (B.name like %:search% OR c.name like %:search%)")
	public Page<Computer> findSomething(@Param("search") String search, Pageable pageable);
	
	
	@Query("select c FROM Computer c LEFT JOIN Company B ON c.company_id = B.id ")
	public Page<Computer> listComputers(Pageable pageable);
	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Computer WHERE company_id =?1")
	public void deleteByCompanyID (long company_id);
	
	
}
