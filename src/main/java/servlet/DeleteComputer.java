package servlet;

import java.io.IOException;
import java.util.stream.IntStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

@WebServlet("/deleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Controller controller = new Controller();
    
    public DeleteComputer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String listDelete = request.getParameter("selection");
		
		String [] list = listDelete.split(",");
		System.out.println(listDelete);
		
		IntStream.range(0,list.length).forEach(i -> { controller.deleteComputerById(list[i]);});

		getServletContext().getRequestDispatcher("/dashboard").forward(request, response);
	}

}
