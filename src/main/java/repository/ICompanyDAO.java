package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Company;

public interface ICompanyDAO extends JpaRepository<Company, Long> {

}
