package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import model.Computer;
 

public class ComputerDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public ComputerDAO() {
    	this.jdbcURL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
        this.jdbcUsername = "admincdb";
        this.jdbcPassword = "qwerty1234";
    }
     
    protected void connect() throws SQLException {
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
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
          
    public List<Computer> listAllComputer() throws SQLException {
        List<Computer> listComputer = new ArrayList<>();
         
        String sql = "SELECT * FROM computer";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Date introduced = resultSet.getDate("introduced");
            Date discontinued = resultSet.getDate("discontinued");
            long company_id = resultSet.getLong("company_id");
             
            Computer computer = new Computer(id,name, introduced, discontinued, company_id);
            listComputer.add(computer);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listComputer;
    }
     
    public boolean deleteComputer(Computer computer) throws SQLException {
        String sql = "DELETE FROM computer where id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setLong(1, computer.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }

	public boolean insertComputer(Computer computer) throws SQLException {
	 String sql = "INSERT INTO computer (name, introduced, discontinued,company_id) VALUES (?, ?, ?,?)";
	 connect();
		         
	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	statement.setString(1, computer.getName());
	statement.setDate(2, computer.getIntroduced());
	statement.setDate(3, computer.getDiscontinued());
	statement.setLong(4, computer.getCompany_id());
	
		         
	boolean rowInserted = statement.executeUpdate() > 0;
	statement.close();
	disconnect();
	return rowInserted;
	}
	
	public boolean insertComputerPartial(String name) throws SQLException {
		 String sql = "INSERT INTO computer (name) VALUES ( ?)";
		 
		 connect();
			         
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, name);
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
			    }
	
	
	public boolean CompanyIDCheck(long company_id) throws SQLException {
        boolean check=false;
         
        String sql = "SELECT id FROM company";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            if (id==company_id) check=true;
            
            
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return check;
    }
	
	
	
	public boolean ComputerIDCheck(long computer_id) throws SQLException {
        boolean check=false;
         
        String sql = "SELECT id FROM computer";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            if (id==computer_id) check=true;
            
            
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return check;
    }
	
	
	 public boolean updateComputer(Computer computer) throws SQLException {
	        String sql = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ?";
	        sql += " WHERE id = ?";
	        connect();
	         
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, computer.getName());
	        statement.setDate(2, computer.getIntroduced());
	        statement.setDate(3, computer.getDiscontinued());
	        statement.setLong(4, computer.getCompany_id());
	        statement.setLong(5, computer.getId());
	         
	        boolean rowUpdated = statement.executeUpdate() > 0;
	        statement.close();
	        disconnect();
	        return rowUpdated;     
	    }
	     
	    public Computer getComputer(long id2) throws SQLException {
	        Computer computer = null;
	        String sql = "SELECT * FROM computer WHERE id = ?";
	         
	        connect();
	         
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setLong(1, id2);
	         
	        ResultSet resultSet = statement.executeQuery();
	         
	        if (resultSet.next()) {
	        	long id = resultSet.getLong("id");
	            String name = resultSet.getString("name");
	            Date introduced = resultSet.getDate("introduced");
	            Date discontinued = resultSet.getDate("discontinued");
	            long company_id = resultSet.getLong("company_id");
	             
	           computer = new Computer(id,name, introduced, discontinued, company_id);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        return computer;
}
}
