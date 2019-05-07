package servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

/**
 * Servlet implementation class DeleteComputer
 */
@WebServlet("/DeleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Controller controller = new Controller();
    
    public DeleteComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String listDelete = request.getParameter("selection");
		
		String [] list = listDelete.split(",");
		System.out.println(listDelete);
		
		for (int i=0; i<list.length;i++) {
			
			controller.deleteComputerById(list[i]);
			
		}
		
	
	
			//controller.deleteComputerById(temp);
			
		
		
			
		
		
		
		
		
		getServletContext().getRequestDispatcher("/DashboardServlet").forward(request, response);
	}

}
