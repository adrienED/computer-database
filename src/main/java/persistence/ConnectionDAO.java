package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionDAO {
	
	static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
 
        try (FileInputStream f = new FileInputStream("src/main/resources/db.properties")) {
 
            
            Properties properties = new Properties();
            properties.load(f);
 
            
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            
            // create a connection to the database
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
        	logger.error("erreur IOgetConnection", e);
        }
        return connection;
    }
 
}
