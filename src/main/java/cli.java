import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Config.AppConfig;
import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import service.ComputerService;


public class cli {
	
	public static void main (String [] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	     ctx.register(AppConfig.class);
	     ctx.refresh();
	     ComputerService computerService = ctx.getBean(ComputerService.class);
		   
	/*	  
	
	List<ComputerDTO> companyDTOs = new ArrayList<ComputerDTO>();
	try {
		companyDTOs = computerService.getAll(50, 1);
	} catch (InvalidDateChronology e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	System.out.println(companyDTOs);
	
	*/
}
}
