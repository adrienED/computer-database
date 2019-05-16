package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	@Autowired
	DataSource mysqDataSource;

	public ComputerDAO() {
	}

	private static final String SQL_FIND_ALL = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.id";
	private static final String SQL_FIND_BY_ID = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id WHERE A.id = ?";
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

	public long create(Computer computer) throws SQLException {
		Long lastInsertedId = 1L;
		
		
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(mysqDataSource);
		vJdbcTemplate.update(SQL_CREATE, new Object[] {computer.getName(),
														computer.getIntroduced(),
														computer.getDiscontinued(),
														computer.getCompanyID()});
		return lastInsertedId;
	}

	public boolean delete(Computer computer) throws SQLException {
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(mysqDataSource);
		vJdbcTemplate.update(SQL_DELETE, new Object[] {computer.getId(),
														});
		logger.info("computer effacer");
		return true;
	}

	public void update(Computer computer) throws SQLException {
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(mysqDataSource);
		vJdbcTemplate.update(SQL_UPDATE, new Object[] {computer.getName(),
														computer.getIntroduced(),
														computer.getDiscontinued(),
														computer.getCompanyID(),
														computer.getId()});
	
	}

	public Computer findById(long id) throws ComputerNotFoundException, InvalidDateChronology {

		JdbcTemplate vJdbcTemplate = new JdbcTemplate(mysqDataSource);
		
		Computer computer = 
		vJdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] {id},
				new BeanPropertyRowMapper<Computer>(Computer.class));
		
		System.out.println(computer);
		if (computer.getId() != 0)	
				return computer;
		else {
			return null;
		}
	}


	public List<Computer> getAll(int limit, int offset) throws InvalidDateChronology {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			 
		
			
			List<Map> rows = new JdbcTemplate(mysqDataSource).queryForList(SQL_FIND_ALL_PAGINED);
			for (Map row : rows) {
				Computer computer = new Computer.Builder()
						.withId((long)row.get("id"))
						.withName((String)(row.get("name")))
						.withIntroduced(LocalDate.parse((CharSequence) (row.get("introduced"))))
						.withDiscontinued(LocalDate.parse((CharSequence) (row.get("discontinued"))))
				.build();
						
						computers.add(computer);
			}
				
			return computers;

	}
		catch (Exception e) {
			// TODO: handle exception
		}
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
			

			JdbcTemplate vJdbcTemplate = new JdbcTemplate(mysqDataSource);
			nbOfComputer = vJdbcTemplate.query(SQL_COUNT_ALL, (Integer.class));
		} catch (SQLException ex) {
			logger.error("Erreur SQL ListComputer", ex);
		}
		return nbOfComputer;
	}

}