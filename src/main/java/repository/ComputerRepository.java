package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import model.Computer;


public interface ComputerRepository extends JpaRepository<Computer, Long> {
		
	
}
