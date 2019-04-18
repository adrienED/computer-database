package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Company;

public class CompanyDAO {

	    private static String jdbcURL;
	    private static String jdbcUsername;
	    private static String jdbcPassword;
	    private static Connection jdbcConnection;
	     
	    public CompanyDAO() {
	        this.jdbcURL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	        this.jdbcUsername = "admincdb";
	        this.jdbcPassword = "qwerty1234";
	    }
	     
	    protected static void connect() throws SQLException {
	        if (jdbcConnection == null || jdbcConnection.isClosed()) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	            } catch (ClassNotFoundException e) {
	                throw new SQLException(e);
	            }
	            jdbcConnection = DriverManager.getConnection(
	                                        jdbcURL, jdbcUsername, jdbcPassword);
	        }
	    }
	     
	    protected static void disconnect() throws SQLException {
	        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
	            jdbcConnection.close();
	        }
	    }	   
	     
	    public static List<Company> listAllCompany() throws SQLException {
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
