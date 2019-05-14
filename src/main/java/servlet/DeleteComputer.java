package servlet;

import java.io.IOException;
import java.util.stream.IntStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import mapper.ComputerMapper;
import model.Computer;
import service.ComputerService;

@WebServlet("/deleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    ComputerService computerService = ComputerService.getInstance();
    ComputerMapper computerMapper = ComputerMapper.getInstance();
    
    static Logger logger = LoggerFactory.getLogger(DeleteComputer.class);
    
    public DeleteComputer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String listDelete = request.getParameter("selection");
		
		String [] list = listDelete.split(",");
		System.out.println(listDelete);
		
		IntStream.range(0,list.length).forEach(i -> { 
			try {

				ComputerDTO computerDTOtoDelete = computerService.findById(list[i]);
				Computer computer = new Computer();
				computer = computerMapper.dtoToModel(computerDTOtoDelete);
				computerService.delete(computer);

			} catch (InvalidDateValueException | NotFoundException | InvalidDateChronology | ComputerNotFoundException e) {
				logger.error("Error delete computer by id", e);
			}});

		getServletContext().getRequestDispatcher("/dashboard").forward(request, response);
	
	}
}
