package service;


import java.util.List;
import java.util.stream.Collectors;
import dto.CompanyDTO;
import mapper.CompanyMapper;
import model.Company;
import persistence.CompanyDAO;


public class CompanyService {
	
	private CompanyMapper companyMapper = CompanyMapper.getInstance();
	private CompanyDAO companyDAO = CompanyDAO.getInstance();

	    private CompanyService()
	    {}

	    private static CompanyService INSTANCE = null;

	    public static CompanyService getInstance()
	    {           
	        if (INSTANCE == null)
	        {   INSTANCE = new CompanyService(); 
	        }
	        return INSTANCE;
	    }
	

	public List<CompanyDTO> getAll(int limit, int offset){
		
		List<Company> companyList = companyDAO.getAll(limit, offset);
		List<CompanyDTO> companyDtoList = (List<CompanyDTO>) companyList.stream().map(s -> companyMapper.modelToDto(s)).collect(Collectors.toList());
		
		return companyDtoList;
	} 	  	 	
	
	public Company getNameById (long id) {
		Company company =companyDAO.findById(id);
		return company;
	}

}
