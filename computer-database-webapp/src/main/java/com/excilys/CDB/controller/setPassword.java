package com.excilys.CDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.excilys.CDB.core.model.User;
import com.excilys.CDB.service.UserService;

@Controller 
public class setPassword {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;

	@GetMapping("/set")
	public void setPassword () {
		
		User user = new User();
		user.setUserId(1);
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		
		userService.SavePass(user);
		
		
	}
}
