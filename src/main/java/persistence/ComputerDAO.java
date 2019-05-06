package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import model.Company;
import model.Computer;

public class ComputerDAO {
	
	Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	

	    private ComputerDAO()
	    {}

	    private static ComputerDAO INSTANCE = null;
	     
	    public static ComputerDAO getInstance()
	    {           
	        if (INSTANCE == null)
	        {   INSTANCE = new ComputerDAO(); 
	        }
	        return INSTANCE;
	    }

	
	
	private static final String SQL_FIND_ALL = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.id";
	private static final String SQL_FIND_WITH_ID = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id WHERE A.id = ?";
	private static final String SQL_CREATE = "INSERT INTO computer (name, introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?,discontinued = ?,company_id = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id=?";
	private static final String SQL_FIND_ALL_PAGINED = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY id LIMIT ? OFFSET ?";
	private static final String SQL_COUNT_ALL = "SELECT COUNT(*) FROM computer";
	public Computer populate(ResultSet resultSet) throws InvalidDateChronology {
		Computer computer = new Computer();
		try {
			computer.setId(resultSet.getLong("id"));
			computer.setName(resultSet.getString("name"));
			if (resultSet.getDate("introduced") != null) {
				computer.setIntroduced(resultSet.getDate("introduced").toLocalDate());
			}
			if (resultSet.getDate("discontinued") != null) {
				computer.setDiscontinued(resultSet.getDate("discontinued").toLocalDate());
			}
		
			computer.setCompanyID(resultSet.getLong("company_id"));


		} catch (SQLException ex) {
			logger.error("Erreur SQL ComputerPopulate", ex);
		}
		return computer;
	}

	public List<Computer> getAll() throws InvalidDateChronology {
		List<Computer> computeresultSet = new ArrayList<Computer>();
		try {
			Connection connection = ConnectionDAO.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Computer computer = populate(resultSet);
				computeresultSet.add(computer);

			}
			connection.close();
		} catch (SQLException ex) {
			logger.error("Erreur SQL ListComputer", ex);
		}
		return computeresultSet;
	}


	public long create(Computer computer) {
		Long lastInsertedId = null;
		try {
			Connection connection = ConnectionDAO.getConnection();
			PreparedStatement statement;
			statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, computer.getName());
			statement.setDate(2, Date.valueOf(computer.getIntroduced()));
			statement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			statement.setLong(4, computer.getCompanyID());
			statement.toString();
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				lastInsertedId = resultSet.getLong(1);
			}
			connection.close();
		} catch (SQLException ex) {
			logger.error("Erreur SQL createComputer", ex);
		}
		return lastInsertedId;
	}

	
	public boolean delete(Computer computer) {
		try {
			Connection connection = ConnectionDAO.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
			statement.setLong(1, computer.getId());
			statement.executeUpdate();
			connection.close();
		} catch (SQLException ex) {
			logger.error("Erreur SQL DeleteComputer", ex);
		}
		return true;
	}


	public boolean update(Computer computer) {
		try {
			Connection connection = ConnectionDAO.getConnection();
			PreparedStatement statement;
			statement = connection.prepareStatement(SQL_UPDATE);
			statement.setString(1, computer.getName());
			statement.setDate(2, Date.valueOf(computer.getIntroduced()));
			statement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			statement.setLong(4, computer.getCompanyID());
			statement.setLong(5, computer.getId());
			statement.executeUpdate();

			connection.close();
		} catch (SQLException ex) {
			logger.error("Erreur SQL updateComputer", ex);
		}
		return false;
	}


	public Computer findById(long id) throws ComputerNotFoundException, InvalidDateChronology {

		try (Connection connection = ConnectionDAO.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(SQL_FIND_WITH_ID);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Computer computer = new Computer();
				computer.setId(resultSet.getLong("id"));
				computer.setName(resultSet.getString("name"));
				if (resultSet.getDate("introduced") != null) {
					computer.setIntroduced(resultSet.getDate("introduced").toLocalDate());
				}
				if (resultSet.getDate("discontinued") != null) {
					computer.setDiscontinued(resultSet.getDate("discontinued").toLocalDate());
				}
				Company company = new Company();
				if (resultSet.getString("company_id") != null) {
					company.setId(resultSet.getLong("company_id"));
				}
				
				return computer;
			} else {
				throw new ComputerNotFoundException(id);
			}

		} catch (SQLException ex) {
			logger.error("Erreur SQL ComputerFindById", ex);
		}
		return null;

	}


	public List<Computer> getAll(int limit, int offset) throws InvalidDateChronology {
		List<Computer> computeresultSet = new ArrayList<Computer>();
		try {
			Connection connection = ConnectionDAO.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PAGINED);
			statement.setLong(1, limit);
			statement.setLong(2, offset);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Computer computer = populate(resultSet);
				computeresultSet.add(computer);

			}
			connection.close();
		} catch (SQLException ex) {
			logger.error("Erreur SQL ListComputer", ex);
		}
		return computeresultSet;
	}
	
	public int getNbOfComputer() {
			int nbOfComputer = 0;
			try {
				Connection connection = ConnectionDAO.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_COUNT_ALL);
				ResultSet resultSet = statement.executeQuery();
				
				resultSet.next();
				nbOfComputer = resultSet.getInt(1);

				
				connection.close();
			} catch (SQLException ex) {
				logger.error("Erreur SQL ListComputer", ex);
			}
			return nbOfComputer;
		}
	
	
	
}