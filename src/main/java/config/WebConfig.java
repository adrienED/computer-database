package config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan (basePackages = {"mapper", "persistence","service","controller","validator"})
public class WebConfig implements WebMvcConfigurer{
	
	 @Bean
	    public DataSource mysqlDataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC");
	        dataSource.setUsername("admincdb");
	        dataSource.setPassword("qwerty1234");
	 
	        return dataSource;
	    }
	  
	    @Bean
	    public ViewResolver viewResolver() {
	        InternalResourceViewResolver bean = 
	          new InternalResourceViewResolver();
	        bean.setPrefix("/WEB-INF/view/");
	        bean.setSuffix(".jsp");
	        return bean;
	    }
	    
	    @Override
	    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	 	   registry.addResourceHandler("/static/**").addResourceLocations("/static/theme/");

	}
}

