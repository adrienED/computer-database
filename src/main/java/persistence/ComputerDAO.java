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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import model.Company;
import model.Computer;
import model.Company.Builder;

@Component("ComputerDAO")
public class ComputerDAO {

	Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	@Autowired
	ConnectionDAO connectionDAO;

	public ComputerDAO() {
	}

	private static final String SQL_FIND_ALL = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.id";
	private static final String SQL_FIND_WITH_ID = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id WHERE A.id = ?";
	private static final String SQL_CREATE = "INSERT INTO computer (name, introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?,discontinued = ?,company_id = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id=?";
	private static final String SQL_FIND_ALL_PAGINED = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY id LIMIT ? OFFSET ?";
	private static final String SQL_COUNT_ALL = "SELECT COUNT(*) FROM computer";
	private static final String SQL_FIND_SEARCH_COMPUTER = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id WHERE (A.name like ? OR B.name like ?) ";
	private static final String SQL_FIND_ALL_ORDERED_BY_NAME = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.name LIMIT ? OFFSET ?";
	private static final String SQL_FIND_ALL_ORDERED_BY_NAME_DESC = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.name DESC LIMIT ? OFFSET ?";

	private static final String SQL_FIND_ALL_ORDERED_BY_INTRODUCED = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.introduced LIMIT ? OFFSET ?";
	private static final String SQL_FIND_ALL_ORDERED_BY_INTRODUCED_DESC = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.introduced DESC LIMIT ? OFFSET ?";

	private static final String SQL_FIND_ALL_ORDERED_BY_DISCONTINUED = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.discontinued LIMIT ? OFFSET ?";
	private static final String SQL_FIND_ALL_ORDERED_BY_DISCONTINUED_DESC = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.discontinued DESC LIMIT ? OFFSET ?";

	private static final String SQL_FIND_ALL_ORDERED_BY_COMPANY = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY B.name LIMIT ? OFFSET ?";
	private static final String SQL_FIND_ALL_ORDERED_BY_COMPANY_DESC = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY B.name DESC LIMIT ? OFFSET ?";

	public Computer populate(ResultSet resultSet) throws InvalidDateChronology {
		Computer.Builder builder = new Computer.Builder();
		try {
			builder.withId(resultSet.getLong("id"));

			builder.withName(resultSet.getString("name"));
			if (resultSet.getDate("introduced") != null) {
				builder.withIntroduced(resultSet.getDate("introduced").toLocalDate());
			}
			if (resultSet.getDate("discontinued") != null) {
				builder.withDiscontinued(resultSet.getDate("discontinued").toLocalDate());
			}

			builder.withCompanyID(resultSet.getLong("company_id"));

		} catch (SQLException ex) {
			logger.error("Erreur SQL ComputerPopulate", ex);
		}
		Computer computer = builder.build();
		return computer;
	}

	public List<Computer> getAll() throws InvalidDateChronology {
		List<Computer> computeresultSet = new ArrayList<Computer>();
		try {
			Connection connection = connectionDAO.getConnection();
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
			Connection connection = connectionDAO.getConnection();
			PreparedStatement statement;
			statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, computer.getName());
			if (computer.getIntroduced() == null)
				statement.setNull(2, java.sql.Types.DATE);
			else
				statement.setDate(2, Date.valueOf(computer.getIntroduced()));
			if (computer.getDiscontinued() == null) {
				statement.setNull(3, java.sql.Types.DATE);
			} else
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
			Connection connection = connectionDAO.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
			statement.setLong(1, computer.getId());
			statement.executeUpdate();
			connection.close();
		} catch (SQLException ex) {
			logger.error("Erreur SQL DeleteComputer", ex);
		}
		logger.info("computer effacer");
		return true;
	}

	public boolean update(Computer computer) {
		try {
			Connection connection = connectionDAO.getConnection();
			PreparedStatement statement;
			statement = connection.prepareStatement(SQL_UPDATE);
			statement.setString(1, computer.getName());
			if (computer.getIntroduced() == null)
				statement.setDate(2, null);
			else
				statement.setDate(2, Date.valueOf(computer.getIntroduced()));
			if (computer.getDiscontinued() == null)
				statement.setDate(3, null);
			else
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

		try (Connection connection = connectionDAO.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(SQL_FIND_WITH_ID);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Computer.Builder builder = new Computer.Builder();

				builder.withId(resultSet.getLong("id"));

				builder.withName(resultSet.getString("name"));
				if (resultSet.getDate("introduced") != null) {
					builder.withIntroduced(resultSet.getDate("introduced").toLocalDate());
				}
				if (resultSet.getDate("discontinued") != null) {
					builder.withDiscontinued(resultSet.getDate("discontinued").toLocalDate());
				}

				if (resultSet.getString("company_id") != null) {
					builder.withCompanyID(resultSet.getLong("company_id"));
				}

				Computer computer = builder.build();
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
			Connection connection = connectionDAO.getConnection();
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

	public List<Computer> getAllOrderedBy(int limit, int offset, String orderByParameter) throws InvalidDateChronology {
		List<Computer> computeresultSet = new ArrayList<Computer>();

		PreparedStatement statement;
		try {
			Connection connection = connectionDAO.getConnection();

			switch (orderByParameter) {
			case "name":
				statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_NAME);
				break;

			case "nameDESC":
				statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_NAME_DESC);
				break;

			case "introduced":
				statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_INTRODUCED);
				break;

			case "introducedDESC":
				statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_INTRODUCED_DESC);
				break;
			case "discontinued":
				statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_DISCONTINUED);
				break;

			case "discontinuedDESC":
				statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_DISCONTINUED_DESC);
				break;
			case "companyDESC":
				statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_COMPANY_DESC);
				break;
			case "id":
				statement = connection.prepareStatement(SQL_FIND_ALL_PAGINED);
				break;
			default:
				statement = connection.prepareStatement(SQL_FIND_ALL_PAGINED);
			}

			statement.setLong(1, limit);
			statement.setLong(2, offset);
			System.out.println(statement);

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

	public List<Computer> getSearchComputer(String search) throws InvalidDateChronology {
		List<Computer> computeresultSet = new ArrayList<Computer>();
		try {
			Connection connection = connectionDAO.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_SEARCH_COMPUTER);
			statement.setString(1, "%" + search + "%");
			statement.setString(2, "%" + search + "%");
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
			Connection connection = connectionDAO.getConnection();
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