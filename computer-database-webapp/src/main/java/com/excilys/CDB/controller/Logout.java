package com.excilys.CDB.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/logout")
public class Logout {
	
	
	@GetMapping
	public void logout (HttpServletRequest request) {



		new SecurityContextLogoutHandler().logout(request, null, null);

	}

}
