package config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import mapper.CompanyMapper;
import mapper.ComputerMapper;
import persistence.CompanyDAO;
import persistence.ComputerDAO;
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
	public ComputerDAO ComputerDAO() {
		return new ComputerDAO();
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
	
	 @Bean
	    public DataSource mysqlDataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC");
	        dataSource.setUsername("admincdb");
	        dataSource.setPassword("qwerty1234");
	 
	        return dataSource;
	    }

}
