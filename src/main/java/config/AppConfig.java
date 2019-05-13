package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mapper.CompanyMapper;
import mapper.ComputerMapper;
import persistence.CompanyDAO;
import persistence.ComputerDAO;

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
	 	public ComputerDAO ComputerDAO() {
		 return new ComputerDAO();
	 }
	 
	 

}
