package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mapper.CompanyMapper;
import mapper.ComputerMapper;
import persistence.CompanyDAO;
import service.CompanyService;
import service.ComputerService;

@Configuration
public class AppConfig {
	
	 @Bean
	    public CompanyMapper CompanyMapper() {
	        return new CompanyMapper();
	    }
	 
	 @Bean
	    public ComputerMapper ComputerMapper() {
	        return new ComputerMapper();
	    }
	 
	 @Bean
	 	public CompanyDAO CompanyDAO() {
		 return new CompanyDAO();
	 }
	 
	 @Bean
	 	public CompanyService CompanyService() {
		 return new CompanyService();
	 }
	 
	 @Bean
	 	public ComputerService ComputerService() {
		 return new ComputerService();
	 }
	 
	 
	 
	 

}
