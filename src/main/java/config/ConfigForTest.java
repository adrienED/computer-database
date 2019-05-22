package config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
	
@Configuration
@ComponentScan (basePackages = {"mapper", "persistence","service","controller","validator"})
public class ConfigForTest {
	

	@Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
 
        return dataSource;
    }
	    
	  


}
