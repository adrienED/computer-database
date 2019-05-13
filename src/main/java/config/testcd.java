package config;

import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.InvalidDateChronology;
import exception.InvalidDateValueException;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import model.Company;
import model.Computer;

public class testcd {
	
    

    public static void main (String [] args) throws InvalidDateValueException, InvalidDateChronology {
    	
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);


    	Company company = new Company();
    	
    	CompanyDTO companyDTO = new CompanyDTO();

    	CompanyMapper companyMapper	= (CompanyMapper) ctx.getBean("CompanyMapper");
    		
    		companyDTO.setId("25");
    		companyDTO.setName("testa");
    		
    		System.out.println(companyMapper.dtoToModel(companyDTO).getName());
    		
    		
    		
    		
    	 ComputerMapper computerMapper= (ComputerMapper) ctx.getBean("ComputerMapper");
    	 
    	 
    	 
    	 
    	LocalDate introDate = LocalDate.parse("2016-12-12");
 		LocalDate disconDate = LocalDate.parse("2017-12-12");
 		ComputerDTO computerDTO = new ComputerDTO();
 		

 		computerDTO.setId("23");
 		computerDTO.setName("Macbook");
 		computerDTO.setIntroduced(introDate);
 		computerDTO.setDiscontinued(disconDate);
 		computerDTO.setCompanyName("Apple Inc.");
 		
 		
 		System.out.println(computerMapper.dtoToModel(computerDTO).getName());
    	 
    	 
    	 
    	 
    	 
    	 
    	}

    
    
}
