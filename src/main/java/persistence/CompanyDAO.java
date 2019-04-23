package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Company;

public class CompanyDAO {

	  	private static final String SQL_FIND_ALL = "SELECT id, name FROM company";
	    private static final String SQL_FIND_WITH_ID = "SELECT id, name FROM company WHERE id = ?";
	    private static final String SQL_FIND_ALL_PAGINED = "SELECT id,name FROM company ORDER BY id LIMIT ? OFFSET ?";
	    
	    public Company populate(ResultSet resultSet) {
	    	Company company = new Company();
	        try {
	        	company.setId(resultSet.getLong("id"));
	            company.setName(resultSet.getString("name"));
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return company;
	    }
	            

		public List<Company> getAll() {
	        List<Company> companies = new ArrayList<Company>();
	        try {
	            Connection connection = ConnectionDAO.getConnection();
	            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	            	Company company = populate(resultSet);
	            	companies.add(company);

	            }
	            connection.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return companies;
		}

	
		public Company findById(long id) {
			Company Company = new Company();
			try {
				Connection connection = ConnectionDAO.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_WITH_ID);
				statement.setLong(1, id);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Company.setName(resultSet.getString("name"));
					Company.setId(resultSet.getLong("id"));
				}
				connection.close();
			} catch (SQLException ex) {
				Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
			}
			return Company;
		}
		
	
		public List<Company> getAll(int limit, int offset) {
	        List<Company> companies = new ArrayList<Company>();
	        try {
	            Connection connection = ConnectionDAO.getConnection();
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
	            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return companies;
		}

	}