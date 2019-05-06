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
    	Properties properties = new Properties();
        Connection connection = null; 
        
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			logger.error("Erreur Driver jdbc not found", ex);
			
		}
      
        FileInputStream f;
		try {
			f = new FileInputStream("src/main/resources/dbHikari.properties");

            

				properties.load(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            HikariConfig hikariConfig = new HikariConfig(properties);

            HikariDataSource ds = new HikariDataSource(hikariConfig);
            connection=ds.getConnection();
            
            // create a connection to the database

	
        return connection;
    }
	
	
}
