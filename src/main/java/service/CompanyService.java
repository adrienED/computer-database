package service;


import java.util.List;
import java.util.stream.Collectors;
import dto.CompanyDTO;
import mapper.CompanyMapper;
import model.Company;
import persistence.CompanyDAO;


public class CompanyService {
	
	protected CompanyMapper companyMapper;
	protected CompanyDAO companyDAO;
	
	
	public CompanyService(CompanyMapper CM, CompanyDAO CDAO) {
		this.companyMapper = CM;
		this.companyDAO = CDAO;
	}

	public List<CompanyDTO> getAll(int limit, int offset){
		CompanyDAO companyDAO = CompanyDAO.getInstance();
		List<Company> companyList = companyDAO.getAll(limit, offset);
		List<CompanyDTO> companyDtoList = (List<CompanyDTO>) companyList.stream().map(s -> companyMapper.modelToDto(s)).collect(Collectors.toList());
		
		return companyDtoList;
	} 	  	 	

}
