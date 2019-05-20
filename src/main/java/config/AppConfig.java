package config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import mapper.CompanyMapper;
import mapper.ComputerMapper;
import persistence.CompanyDAO;
import persistence.ComputerDAO;

@Configuration
@ComponentScan (basePackageClasses = {CompanyMapper.class, ComputerMapper.class, CompanyDAO.class, ComputerDAO.class})
public class AppConfig {
	
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
