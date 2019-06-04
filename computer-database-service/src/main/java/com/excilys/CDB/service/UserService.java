package com.excilys.CDB.service;

import org.springframework.stereotype.Component;

import com.excilys.CDB.persistence.repository.UserRepository;

@Component("UserService")
public class UserService {

	private final UserRepository userRepository;
	
	public UserService (UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	public void findbyId() {


	}

}
