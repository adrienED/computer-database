package persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionDAO {

	static Logger logger = LoggerFactory.getLogger(ConnectionDAO.class);

	private HikariConfig config = new HikariConfig("/db.properties");
	private HikariDataSource ds = new HikariDataSource(config);

	public Connection getConnection() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			logger.error("Erreur Driver jdbc not found", ex);

		}
		System.out.println("serveur ok");

		return ds.getConnection();

	}

}
