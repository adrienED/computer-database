

import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import mapper.CompanyMapper;
import mapper.ComputerMapper;
import persistence.CompanyDAO;
import persistence.ConnectionDAO;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

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
	 @Bean
	 	public ConnectionDAO ConnectionDAO() {
		 return new ConnectionDAO();
	 }
	 
	 @Bean
	 	public ComputerValidator ComputerValidator() {
		 return new ComputerValidator();
	 }
	 

		
}
