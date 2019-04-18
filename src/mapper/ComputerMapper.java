package mapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import model.Computer;
import persistence.ComputerDAO;

public class ComputerMapper {
	
	
	public static Computer Insert() throws SQLException {

		ComputerDAO cdao = new ComputerDAO();
				
		System.out.print("name ? : ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.print("Introduced date ? : ");
		String introduced = sc.nextLine();
		System.out.print("Discountinued date ? : ");
		String discontinued = sc.nextLine();
		System.out.print("Company_id? : ");
		String company_id = sc.nextLine();

		Date introucedD = Date.valueOf(introduced);
		Date discontinuedD = Date.valueOf(discontinued);
		long company_idL = Long.valueOf(company_id).longValue();

		
		System.out.println(cdao.CompanyIDCheck(company_idL));
		
		
		Computer computer = new Computer(name, introucedD, discontinuedD, company_idL);
		return computer;
	}

}
