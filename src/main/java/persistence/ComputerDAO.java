package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import mapper.ComputerMapper;
import model.Computer;

@Repository("ComputerDAO")
public class ComputerDAO {

	Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	protected ComputerMapper computerMapper;
	

	protected DataSource dataSource;
	


	public ComputerDAO(ComputerMapper computerMapper, DataSource dataSource) {
		this.computerMapper = computerMapper;
		this.dataSource = dataSource;	
	
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



	public long create(Computer computer) throws SQLException {
		Long lastInsertedId = 1L;
		
		
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(dataSource);
		vJdbcTemplate.update(SQL_CREATE, new Object[] {computer.getName(),
														computer.getIntroduced(),
														computer.getDiscontinued(),
														computer.getCompanyID()});
		return lastInsertedId;
	}

	public boolean delete(Computer computer) throws SQLException, ComputerNotFoundException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int id =jdbcTemplate.update(SQL_DELETE, new Object[] {computer.getId(),Integer.class});
		
		logger.info("computer id ="+id+"delete");
		
		
		return true;
	}

	public void update(Computer computer) throws SQLException, ComputerNotFoundException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int nbOfRowAffected = jdbcTemplate.update(SQL_UPDATE, new Object[] {computer.getName(),
														computer.getIntroduced(),
														computer.getDiscontinued(),
														computer.getCompanyID(),
														computer.getId()});
		
		if(nbOfRowAffected==0)
			throw new ComputerNotFoundException(computer.getId());
	}

	public Computer findById(long id) throws ComputerNotFoundException  {

		JdbcTemplate vJdbcTemplate = new JdbcTemplate(dataSource);
		Computer computer ;
		try {
		computer = 
		vJdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] {id},computerMapper );
		}
		catch (EmptyResultDataAccessException e) {
			throw new ComputerNotFoundException(id);
		}
		
		return computer;
	}

	public List<Computer> getAll(int limit, int offset) throws InvalidDateChronology {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		List<Computer> computers = new ArrayList<Computer>();
		 computers = jdbcTemplate.query(SQL_FIND_ALL_PAGINED,new Object[] {limit,offset},computerMapper);
		 return computers;
	}
	
	
	public List<Computer> getAllOrderedBy(int limit, int offset, String orderByParameter) throws InvalidDateChronology {
		List<Computer> computers = new ArrayList<Computer>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			switch (orderByParameter) {
			case "name":
			 computers = jdbcTemplate.query(SQL_FIND_ALL_ORDERED_BY_NAME,new Object[] {limit,offset},computerMapper);
				break;
			case "nameDESC":
				 computers = jdbcTemplate.query(SQL_FIND_ALL_ORDERED_BY_NAME_DESC,new Object[] {limit,offset},computerMapper);
				break;

			case "introduced":
				 computers = jdbcTemplate.query(SQL_FIND_ALL_ORDERED_BY_INTRODUCED,new Object[] {limit,offset},computerMapper);
				
				break;

			case "introducedDESC":
				computers = jdbcTemplate.query(SQL_FIND_ALL_ORDERED_BY_INTRODUCED_DESC,new Object[] {limit,offset},computerMapper);
				
				break;
			case "discontinued":
				computers = jdbcTemplate.query(SQL_FIND_ALL_ORDERED_BY_DISCONTINUED,new Object[] {limit,offset},computerMapper);
				break;

			case "discontinuedDESC":
				computers = jdbcTemplate.query(SQL_FIND_ALL_ORDERED_BY_DISCONTINUED_DESC,new Object[] {limit,offset},computerMapper);
				break;
			case "companyDESC":
				computers = jdbcTemplate.query(SQL_FIND_ALL_ORDERED_BY_COMPANY_DESC,new Object[] {limit,offset},computerMapper);
				break;
			case "id":
				computers = jdbcTemplate.query(SQL_FIND_ALL_PAGINED,new Object[] {limit,offset},computerMapper);

				break;
			default:
				
			}
			
			return computers;
	}

	public List<Computer> getSearchComputer(String search) throws InvalidDateChronology {
		List<Computer> computers = new ArrayList<Computer>();
		String searchQuery = "%" + search + "%";				
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		computers = jdbcTemplate.query(SQL_SEARCH_COMPUTER,new Object[] {searchQuery,searchQuery},computerMapper);

		return computers;
	}

	public int getNbOfComputer() throws SQLException {
		int nbOfComputer = 0;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(dataSource);
		nbOfComputer = vJdbcTemplate.queryForObject(SQL_COUNT_ALL, Integer.class);
		return nbOfComputer;
	}

}