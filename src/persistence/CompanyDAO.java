package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

	    private String jdbcURL;
	    private String jdbcUsername;
	    private String jdbcPassword;
	    private Connection jdbcConnection;
	     
	    public CompanyDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
	        this.jdbcURL = jdbcURL;
	        this.jdbcUsername = jdbcUsername;
	        this.jdbcPassword = jdbcPassword;
	    }
	     
	    protected void connect() throws SQLException {
	        if (jdbcConnection == null || jdbcConnection.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver");
	            } catch (ClassNotFoundException e) {
	                throw new SQLException(e);
	            }
	            jdbcConnection = DriverManager.getConnection(
	                                        jdbcURL, jdbcUsername, jdbcPassword);
	        }
	    }
	     
	    protected void disconnect() throws SQLException {
	        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
	            jdbcConnection.close();
	        }
	    }	   
	     
	    public List<Company> listAllCompany() throws SQLException {
	        List<Company> listCompany = new ArrayList<>();
	         
	        String sql = "SELECT * FROM company";
	         
	        connect();
	         
	        Statement statement = jdbcConnection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	            long id = resultSet.getLong("id");
	            String name = resultSet.getString("name");
	            
	             
	            Company company = new Company(id,name);
	            listCompany.add(company);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        disconnect();
	         
	        return listCompany;
	    }
}
