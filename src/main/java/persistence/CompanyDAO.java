package persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Component;

import com.sun.xml.bind.v2.model.core.ID;

import model.Company;

@Component("CompanyDAO")
public class CompanyDAO implements ICompanyDAO {

	
	   @PersistenceContext
	   private EntityManager em;

	@Override
	public List<Company> listCompanies() {
	      CriteriaQuery<Company> criteriaQuery = em.getCriteriaBuilder().createQuery(Company.class);

	      criteriaQuery.from(Company.class);
	      return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Optional<Company> findById(ID primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	

	
}
