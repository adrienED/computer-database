package com.excilys.CDB.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.CDB.core.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
