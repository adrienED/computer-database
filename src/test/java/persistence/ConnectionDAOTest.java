package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class ConnectionDAOTest {

	static Logger logger = LoggerFactory.getLogger(ConnectionDAO.class);

	private HikariConfig config = new HikariConfig("/db.properties");
	private HikariDataSource ds = new HikariDataSource(config);

	public Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException ex) {
			logger.error("Erreur Driver jdbc not found", ex);
			
		}
		System.out.println("serveur ok");

		return ds.getConnection();

	}

}