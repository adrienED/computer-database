

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import service.CompanyService;
import service.ComputerService;
import persistence.CompanyDAO;
import persistence.ComputerDAO; 
import mapper.CompanyMapper;
import mapper.ComputerMapper;

@Configuration
public class AppConfig {
	
	@Bean
    public CompanyService CompanyService() {
        return new CompanyService();
    }
	
	@Bean
	public ComputerService ComputerService (){
		return new ComputerService();
	}
	
	@Bean
	public CompanyDAO CompanyDAO (){
		return new CompanyDAO();
	}
	
	@Bean
	public ComputerDAO ComputerDAO() {
		return new ComputerDAO();
	}
	
	@Bean
	public ComputerMapper ComputerMapper() {
		return new ComputerMapper();
	}
	
	@Bean
	public CompanyMapper CompanyMapper() {
		return new CompanyMapper();
	}
}
