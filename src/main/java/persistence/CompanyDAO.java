package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import model.Company;

@Component("CompanyDAO")
public class CompanyDAO implements ICompanyDAO {

	
	   @PersistenceContext
	   private EntityManager em;

	@Override
	public List<Company> listCompanies() {
	      CriteriaQuery<Company> criteriaQuery = em.getCriteriaBuilder().createQuery(Company.class);

	      @SuppressWarnings("unused")
	      Root<Company> root = criteriaQuery.from(Company.class);
	      return em.createQuery(criteriaQuery).getResultList();
	}

	
	

	
}
