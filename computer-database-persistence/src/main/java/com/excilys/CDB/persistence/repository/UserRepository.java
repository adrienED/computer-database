package com.excilys.CDB.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.excilys.CDB.core.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
    @Query(" select u from User u " +
            " where u.username = ?1")
    Optional<User> findUserWithName(String username);
}

