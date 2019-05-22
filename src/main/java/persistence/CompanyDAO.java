package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mapper.CompanyMapper;
import model.Company;
import model.Company.Builder;

@Component("CompanyDAO")
public class CompanyDAO {

	Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	private final CompanyMapper companyMapper;

	private final DataSource dataSource;

	public CompanyDAO(CompanyMapper companyMapper, DataSource dataSource) {
		super();
		this.companyMapper = companyMapper;
		this.dataSource = dataSource;
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
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(dataSource);
		companies = vJdbcTemplate.query(SQL_FIND_ALL, new BeanPropertyRowMapper<Company>(Company.class));

		return companies;
	}

	public Company findById(long id) {
		Company company;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		if (id == 0)
			company = new Company.Builder().withParameter(0, null).build();
		else
			company = (Company) jdbcTemplate.queryForObject(SQL_FIND_WITH_ID, new Object[] { id }, companyMapper);
		return company;
	}

	public long findByName(String name) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		long id = (long) jdbcTemplate.queryForObject(SQL_FIND_WITH_NAME, new Object[] { name }, Long.class);
		return id;
	}

	public List<Company> getAll(int limit, int offset) {
		List<Company> companies = new ArrayList<Company>();
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(dataSource);
		companies = vJdbcTemplate.query(SQL_FIND_ALL_PAGINED, new Object[] { limit, offset },
				new BeanPropertyRowMapper<Company>(Company.class));

		return companies;
	}

	@Transactional("TransactionManager")
	public void deleteCompanyById(long idL) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(SQL_DELETE_COMPUTER_BY_ID, new Object[] { idL });
		jdbcTemplate.update(SQL_DELETE_COMPANY_BY_ID, new Object[] { idL });
		logger.info("company effacer");
	}
}
