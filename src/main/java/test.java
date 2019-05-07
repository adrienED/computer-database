import java.util.List;

import controller.Controller;
import dto.CompanyDTO;
import service.CompanyService;
import service.ComputerService;

public class test {
	
	public static void main (String [] args) {
		
		CompanyService companyService = CompanyService.getInstance(); 
			
		List<CompanyDTO> companyDTOs = companyService.getAll(10, 0);
		
		System.out.println(companyDTOs);
	}

}
