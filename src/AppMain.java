import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.Company;
import persistence.CompanyDAO;

public class AppMain {
	

	public static void main(String[] args) throws SQLException {
		List<Company> test = new ArrayList<>();


		CompanyDAO cdao = new CompanyDAO("jdbc:mysql://localhost:3306/bookstore","root","");
		test=cdao.listAllCompany();



	}

}
