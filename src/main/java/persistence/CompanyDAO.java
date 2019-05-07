package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import model.Company;

public class CompanyDAO {
	
	Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	ConnectionDAO connectionDAO = new ConnectionDAO();

	    private CompanyDAO()
	    {}

	    private static CompanyDAO INSTANCE = null;
	     
	  
	    public static CompanyDAO getInstance()
	    {           
	        if (INSTANCE == null)
	        {   INSTANCE = new CompanyDAO(); 
	        }
	        return INSTANCE;
	    }

	  	private static final String SQL_FIND_ALL = "SELECT id, name FROM company";
	    private static final String SQL_FIND_WITH_ID = "SELECT id, name FROM company WHERE id = ?";
	    private static final String SQL_FIND_WITH_NAME = "SELECT id FROM company WHERE name = ?";
	    private static final String SQL_FIND_ALL_PAGINED = "SELECT id,name FROM company ORDER BY id LIMIT ? OFFSET ?";
	    
	    public Company populate(ResultSet resultSet) {
	    	Company company = new Company();
	        try {
	        	company.setId(resultSet.getLong("id"));
	            company.setName(resultSet.getString("name"));
	            
	        } catch (SQLException ex) {
	        	logger.error("Erreur SQL populate", ex);
	        }
	        return company;
	    }
	            

		public List<Company> getAll() {
	        List<Company> companies = new ArrayList<Company>();
	        try {
	            Connection connection = connectionDAO.getConnection();
	            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	            	Company company = populate(resultSet);
	            	companies.add(company);

	            }
	            connection.close();
	        } catch (SQLException ex) {
	        	logger.error("Erreur SQL ListCompany", ex);
	        }
	        return companies;
		}

	
		public Company findById(long id) {
			Company Company = new Company();
			try {
				Connection connection = connectionDAO.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_WITH_ID);
				statement.setLong(1, id);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					
					Company.setId(resultSet.getLong("id"));
					Company.setName(resultSet.getString("name"));
				}
				connection.close();
			} catch (SQLException ex) {
				logger.error("Erreur SQL findById", ex);
			}
			return Company;
		}
		
		
		public long findByName(String name) {
			Long id = 0L;
			try {
				Connection connection = connectionDAO.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_WITH_NAME);
				statement.setString(1, name);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					
					id = resultSet.getLong("id");
					System.out.println(id);
				}
				connection.close();
			} catch (SQLException ex) {
				logger.error("Erreur SQL findById", ex);
			}
			return id;
		}
		
	
		public List<Company> getAll(int limit, int offset) {
	        List<Company> companies = new ArrayList<Company>();
	        try {
	            Connection connection = connectionDAO.getConnection();
	            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PAGINED);
	            statement.setLong(1, limit);
	            statement.setLong(2, offset);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	            	Company company = populate(resultSet);
	            	companies.add(company);

	            }
	            connection.close();
	        } catch (SQLException ex) {
	        	logger.error("Erreur SQL ListCompany", ex);
	        }
	        return companies;
		}
		
		

	}