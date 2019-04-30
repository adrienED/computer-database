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
	
	static Logger logger = LoggerFactory.getLogger(ConnectionDAO.class);

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			logger.error("Erreur Driver jdbc not found", ex);
			
		}
		        
        String user = "admincdb";
        String password = "qwerty1234";
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", user, password);
        return connection;
    }
 
}
