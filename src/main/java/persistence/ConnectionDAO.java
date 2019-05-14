package persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component("ConnectionDAO")
public class ConnectionDAO {
	
	
	public ConnectionDAO(){
		
	}

	static Logger logger = LoggerFactory.getLogger(ConnectionDAO.class);

	private HikariConfig config = new HikariConfig("/db.properties");
	private HikariDataSource ds = new HikariDataSource(config);

	public Connection getConnection() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			logger.error("Erreur Driver jdbc not found", ex);

		}
		return ds.getConnection();

	}

}
