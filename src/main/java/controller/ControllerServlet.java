package controller;
	import java.io.IOException;
	import java.sql.SQLException;
	import java.util.List;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	/**
	 * Servlet implementation class ControleurServletCRUD
	 */
	@WebServlet("/")
	public class ControllerServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	    

	    public ControllerServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action = request.getServletPath();

			try {
				switch (action) {
				case "/DashBoardServlet":
					dash(request, response);
				break;
				}
			}
				catch (SQLException ex) {
				throw new ServletException(ex);
			}
		}

	


		private void dash(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
			
			dispatcher.forward(request, response);

		}

		


		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	}



