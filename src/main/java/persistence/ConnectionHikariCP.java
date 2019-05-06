package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.spec.DSAGenParameterSpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionHikariCP {
	
	static Logger logger = LoggerFactory.getLogger(ConnectionHikariCP.class);
	
	
	
	

    public static Connection getConnection() throws SQLException {
        Connection connection = null; 
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			logger.error("Erreur Driver jdbc not found", ex);
			
		}
      
        FileInputStream f = new FileInputStream("../resources/dbHikari.properties");
        
        
            
            Properties properties = new Properties();
            properties.load(f);
            
            HikariConfig hikariConfig = new HikariConfig(properties);

            HikariDataSource ds = new HikariDataSource(hikariConfig);
            connection=ds.getConnection();
            
            // create a connection to the database

	
        return connection;
    }
	
	
}
