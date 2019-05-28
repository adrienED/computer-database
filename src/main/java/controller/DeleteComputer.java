/*package controller;

import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mapper.ComputerMapper;
import service.ComputerService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputer {
	
	static Logger logger = LoggerFactory.getLogger(DeleteComputer.class);
	
	private final ComputerService computerService;
	private final ComputerMapper computerMapper;

	public DeleteComputer(ComputerService computerService, ComputerMapper computerMapper) {
		super();
		this.computerService = computerService;
		this.computerMapper = computerMapper;
	}

	@PostMapping
	public String doPost(HttpServletRequest request) {

		String listDelete = request.getParameter("selection");

		String[] list = listDelete.split(",");
		IntStream.range(0, list.length).forEach(i -> {
				computerService.delete(computerMapper.idToLong(list[i]));
		});
		
		 return "redirect:dashboard";
	}
}
*/
