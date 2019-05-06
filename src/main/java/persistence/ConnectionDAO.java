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

import ch.qos.logback.classic.db.DBAppender;

public class ConnectionDAO {

	static Logger logger = LoggerFactory.getLogger(ConnectionDAO.class);

	private HikariConfig config = new HikariConfig("/dbHikari.properties");
	private HikariDataSource ds = new HikariDataSource(config);

	public Connection getConnection() throws SQLException {

		return ds.getConnection();

	}

}
