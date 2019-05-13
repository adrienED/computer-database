package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import mapper.CompanyMapper;
import mapper.ComputerMapper;

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
	 

}
