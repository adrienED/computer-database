package com.excilys.CDB.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public ModelAndView loginGet() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	        if (!(auth instanceof AnonymousAuthenticationToken)) {
	            return new ModelAndView("redirect:/dashboard");
	        }
	        return new ModelAndView("login");
	    }
}
