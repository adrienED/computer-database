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
     
    public ComputerDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
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
	 String sql = "INSERT INTO computer (name, introduced, discontinued) VALUES (?, ?, ?)";
	 connect();
		         
	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	statement.setString(1, computer.getName());
	statement.setDate(2, computer.getIntroduced());
	statement.setDate(3, computer.getDiscontinued());
	
		         
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
		
	}
