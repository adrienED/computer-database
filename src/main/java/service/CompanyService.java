package service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import Config.AppConfig;
import dto.CompanyDTO;
import mapper.CompanyMapper;
import model.Company;
import persistence.CompanyDAO;

@Component("CompanyService")
public class CompanyService {
	
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	
	private CompanyDAO companyDAO = ctx.getBean(CompanyDAO.class);
	private CompanyMapper companyMapper = ctx.getBean(CompanyMapper.class);

	    public CompanyService() {}


	public List<CompanyDTO> getAll(int limit, int offset){
		
		List<Company> companyList = companyDAO.getAll(limit, offset);
		List<CompanyDTO> companyDtoList = (List<CompanyDTO>) companyList.stream().map(s -> companyMapper.modelToDto(s)).collect(Collectors.toList());
		
		return companyDtoList;
	} 	  	 	
	
	public Company getNameById (long id) {
		Company company =companyDAO.findById(id);
		return company;
	}
	
	public void deleteCompanyById (long id) {
		companyDAO.deleteCompanyById(id);
	}

}
