package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
		int httpErrorCode = getErrorCode(httpRequest);
		switch (httpErrorCode) {
			case 403: return new ModelAndView("error-403");
			case 404: return new ModelAndView("error-404");
			default : return new ModelAndView("error-404");
		}
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
	
}

