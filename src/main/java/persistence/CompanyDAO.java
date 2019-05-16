package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import model.Company;
import model.Company.Builder;

@Component("CompanyDAO")
public class CompanyDAO {

	Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	@Autowired
	ConnectionDAO connectionDAO;
	
	@Autowired
	DataSource mysqDataSource;

	public CompanyDAO() {
	}

	private static final String SQL_FIND_ALL = "SELECT id, name FROM company";
	private static final String SQL_FIND_WITH_ID = "SELECT id, name FROM company WHERE id = ?";
	private static final String SQL_FIND_WITH_NAME = "SELECT id FROM company WHERE name = ?";
	private static final String SQL_FIND_ALL_PAGINED = "SELECT id,name FROM company ORDER BY id LIMIT ? OFFSET ?";
	private static final String SQL_DELETE_COMPUTER_BY_ID = "DELETE FROM computer WHERE company_id=?";
	private static final String SQL_DELETE_COMPANY_BY_ID = "DELETE FROM company WHERE id=?";

	public Company populate(ResultSet resultSet) {
		Builder builder = new Company.Builder();
		try {
			builder.withParameter(resultSet.getLong("id"), resultSet.getString("name"));

		} catch (SQLException ex) {
			logger.error("Erreur SQL populate", ex);
		}
		Company company = builder.build();
		return company;
	}

	public List<Company> getAll() {
		List<Company> companies = new ArrayList<Company>();
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(mysqDataSource);
		companies = vJdbcTemplate.query(SQL_FIND_ALL,
				new BeanPropertyRowMapper<Company>(Company.class));
			
		return companies;
	}

	public Company findById(long id) {
		
		Company company = (Company) new JdbcTemplate(mysqDataSource).queryForObject(
				SQL_FIND_WITH_ID, new Object[] { id }, 
				new BeanPropertyRowMapper<Company>(Company.class));
		return company;
	}

	public long findByName(String name) {
		long id = (long) new JdbcTemplate(mysqDataSource).queryForObject(
				SQL_FIND_WITH_NAME, new Object[] { name }, Long.class);
		return id;
	}

	public List<Company> getAll(int limit, int offset) {
		List<Company> companies = new ArrayList<Company>();
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(mysqDataSource);
		companies = vJdbcTemplate.query(SQL_FIND_ALL_PAGINED, new Object[] {limit , offset},
				new BeanPropertyRowMapper<Company>(Company.class));
			
		return companies;
	}

	public void deleteCompanyById(long idL) {
		try {

			Connection connection = connectionDAO.getConnection();
			PreparedStatement deleteComputerPreparedStatement = connection.prepareStatement(SQL_DELETE_COMPUTER_BY_ID);
			PreparedStatement deleteCompanyPreparedStatement = connection.prepareStatement(SQL_DELETE_COMPANY_BY_ID);

			connection.setAutoCommit(false);

			try {

				deleteComputerPreparedStatement.setLong(1, idL);
				deleteComputerPreparedStatement.executeUpdate();

				deleteCompanyPreparedStatement.setLong(1, idL);
				deleteCompanyPreparedStatement.executeUpdate();

				connection.commit();
			} catch (Exception e) {
				connection.rollback();
			}

			connection.setAutoCommit(true);

		} catch (SQLException e) {
			logger.error("Erreur delete Company", e);
		}
	}
}