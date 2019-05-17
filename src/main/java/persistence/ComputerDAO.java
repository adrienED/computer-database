package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import model.Company;
import model.Computer;

@Component("ComputerDAO")
public class ComputerDAO {

	Logger logger = LoggerFactory.getLogger(ComputerDAO.class);


	
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
	private static final String SQL_SEARCH_COMPUTER = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id WHERE (A.name like ? OR B.name like ?) ";
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
		return null;
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
		JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqDataSource);
		jdbcTemplate.update(SQL_DELETE, new Object[] {computer.getId(),
														});
		logger.info("computer effacer");
		return true;
	}

	public void update(Computer computer) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqDataSource);
		jdbcTemplate.update(SQL_UPDATE, new Object[] {computer.getName(),
														computer.getIntroduced(),
														computer.getDiscontinued(),
														computer.getCompanyID(),
														computer.getId()});
	
	}

	public Computer findById(long id)  {

		
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(mysqDataSource);
		
		Map row=
		vJdbcTemplate.queryForMap(SQL_FIND_BY_ID, new Object[] {id} );
		
		Computer computer = new Computer.Builder()
				.withId((long)row.get("id"))
				.withName((String)(row.get("name")))
				.withIntroduced( LocalDate.parse( row.get("introduced")))
				.withDiscontinued( LocalDate.parse( row.get("discontinued")))
				.withCompanyID((long)row.get("company_id"))
		.build();
		System.out.println(computer);
		
		
		return computer;
	}
		
		
		



	public List<Computer> getAll(int limit, int offset) throws InvalidDateChronology {
		List<Computer> computers = new ArrayList<Computer>();
	
			 
			JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqDataSource);
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_FIND_ALL_PAGINED,new Object[] {limit,offset});
			for (Map row : rows) {
				Computer computer = new Computer.Builder()
						.withId((long)row.get("id"))
						.withName((String)(row.get("name")))
						.withIntroduced(LocalDate.parse((CharSequence) (row.get("introduced"))))
						.withDiscontinued(LocalDate.parse((CharSequence) (row.get("discontinued"))))
						.withCompanyID((long)row.get("company_id"))
				.build();
						
						computers.add(computer);
			}


		return computers;
	}

	public List<Computer> getAllOrderedBy(int limit, int offset, String orderByParameter) throws InvalidDateChronology {
		List<Computer> computers = new ArrayList<Computer>();
		List<Map<String, Object>> rows;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqDataSource);
		rows = jdbcTemplate.queryForList(SQL_FIND_ALL,new Object[] {limit,offset});

			switch (orderByParameter) {
			case "name":
			 rows = jdbcTemplate.queryForList(SQL_FIND_ALL,new Object[] {limit,offset});
				break;
			case "nameDESC":
				 rows = jdbcTemplate.queryForList(SQL_FIND_ALL_ORDERED_BY_NAME_DESC,new Object[] {limit,offset});
				break;

			case "introduced":
				 rows = jdbcTemplate.queryForList(SQL_FIND_ALL_ORDERED_BY_INTRODUCED,new Object[] {limit,offset});
				
				break;

			case "introducedDESC":
				 rows = jdbcTemplate.queryForList(SQL_FIND_ALL_ORDERED_BY_INTRODUCED_DESC,new Object[] {limit,offset});
				
				break;
			case "discontinued":
				//statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_DISCONTINUED);
				break;

			case "discontinuedDESC":
			//	statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_DISCONTINUED_DESC);
				break;
			case "companyDESC":
				//statement = connection.prepareStatement(SQL_FIND_ALL_ORDERED_BY_COMPANY_DESC);
				break;
			case "id":
				//statement = connection.prepareStatement(SQL_FIND_ALL_PAGINED);
				break;
			default:
				
			}
				
			for (Map row : rows) {
				Computer computer = new Computer.Builder()
						.withId((long)row.get("id"))
						.withName((String)(row.get("name")))
						.withIntroduced(LocalDate.parse((CharSequence) (row.get("introduced"))))
						.withDiscontinued(LocalDate.parse((CharSequence) (row.get("discontinued"))))
						.withCompanyID((long)row.get("company_id"))
				.build();
						
						computers.add(computer);

			}
			
			return computers;
	}

	public List<Computer> getSearchComputer(String search) throws InvalidDateChronology {
		List<Computer> computers = new ArrayList<Computer>();
		String searchQuery = "%" + search + "%";				
		
		List<Map<String, Object>> rows = new JdbcTemplate(mysqDataSource).queryForList(SQL_SEARCH_COMPUTER,new Object[] {searchQuery,searchQuery});
		for (Map row : rows) {
			Computer computer = new Computer.Builder()
					.withId((long)row.get("id"))
					.withName((String)(row.get("name")))
					.withIntroduced(LocalDate.parse((CharSequence) (row.get("introduced"))))
					.withDiscontinued(LocalDate.parse((CharSequence) (row.get("discontinued"))))
					.withCompanyID((long)row.get("company_id"))
			.build();
					
					computers.add(computer);
					
					
					

		}
		return computers;
	}

	public int getNbOfComputer() throws SQLException {
		int nbOfComputer = 0;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(mysqDataSource);
		nbOfComputer = vJdbcTemplate.queryForObject(SQL_COUNT_ALL, Integer.class);
		return nbOfComputer;
	}

}