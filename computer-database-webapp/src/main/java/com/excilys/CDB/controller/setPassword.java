package com.excilys.CDB.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.excilys.CDB.binding.dto.ComputerDTO;
import com.excilys.CDB.core.model.User;
import com.excilys.CDB.service.UserService;

@Controller 
public class setPassword {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	addComputer addComputer;
	
	@Autowired
	UserService userService;

	@GetMapping("/set")
	public void setPassword () {
		
		/*
		
		User user = new User();
		user.setUserId(1);
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		
		userService.SavePass(user);
		
		*/
		
		ComputerDTO computerDTO = new ComputerDTO();

		computerDTO.setName("test");
		computerDTO.setIntroduced("2018-12-12");
		computerDTO.setDiscontinued("2017-12-12");
		computerDTO.setCompanyName("Apple Inc.");
		computerDTO.setCompany_id(1);
		
		
	//	submit(computerDTO);
		
		
		
		
		
	}
}
