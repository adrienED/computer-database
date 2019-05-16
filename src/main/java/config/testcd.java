package config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import exception.NotFoundException;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import model.Company;
import model.Computer;
import persistence.CompanyDAO;
import persistence.ComputerDAO;
import service.CompanyService;
import service.ComputerService;

public class testcd {
	
	//@Autowired
	//static
	//ComputerService computerService;
	
    

    public static void main (String [] args) throws InvalidDateValueException, InvalidDateChronology, NotFoundException, ComputerNotFoundException {
    	
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);


    	Company company = new Company();
    	
    	CompanyDTO companyDTO = new CompanyDTO();

    	CompanyMapper companyMapper	= (CompanyMapper) ctx.getBean("CompanyMapper");
    		
    		companyDTO.setId("25");
    		companyDTO.setName("testa");
    		
    	System.out.println(companyMapper.dtoToModel(companyDTO).getName());
	
    	 //ComputerMapper computerMapper= (ComputerMapper) ctx.getBean("ComputerMapper");

    	LocalDate introDate = LocalDate.parse("2016-12-12");
 		LocalDate disconDate = LocalDate.parse("2017-12-12");
 		ComputerDTO computerDTO = new ComputerDTO();
 		

 		computerDTO.setId("23");
 		computerDTO.setName("Macbook");
 		computerDTO.setIntroduced(introDate);
 		computerDTO.setDiscontinued(disconDate);
 		computerDTO.setCompanyName("Apple Inc.");
 		
 		ComputerService computerService = (ComputerService) ctx.getBean("ComputerService");
 		
 		ComputerDAO computerDAO = (ComputerDAO) ctx.getBean("ComputerDAO");
 		System.out.println(computerDAO.findById(13L));
 		//System.out.println(computerService.findById("13"));
 		
 		
 		//System.out.println(computerMapper.dtoToModel(computerDTO).getName());

 		//CompanyDAO companyDAO= (CompanyDAO) ctx.getBean("CompanyDAO");
   	 
 		//System.out.println( companyDAO.getAll());
 		
 		
 		//CompanyService companyService = (CompanyService) ctx.getBean("CompanyService");
 		
 		//System.out.println(companyService.getAll(10, 2));
 		
 		//ComputerService computerService = (ComputerService) ctx.getBean("ComputerService");
 		
 		//System.out.println(computerService.getAll(100, 0));
 		
 		//companyService.deleteCompanyById(4L);
 		
    	}

    
    
}
