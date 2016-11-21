package io.numis.service;

import static spark.Spark.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import spark.servlet.SparkApplication;

/**
 * <h1>NumisApp</h1>
 * The App class is the starting point for the 
 * NumisService backend for numis.io.
 * <p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 2016-11-14
 *
 */
public class Application implements SparkApplication {
	
	private static String str;
	
	// Logger info
	private final static Logger LOGGER = Logger.getLogger(Application.class.getName());
	
	// FileHandler
	private static FileHandler handler;

	public void init() {
		Connection connection;
		try {
			handler = new FileHandler("/numis_logging.txt");
			LOGGER.addHandler(handler);
			LOGGER.info("Attempting to estab a connection");
			connection = getConnection();
			
			Statement stmt = connection.createStatement();
	    	ResultSet rs = stmt.executeQuery("SELECT * from SAMPLE");
	    	while (rs.next()) {
	    		LOGGER.info(rs.getString("name"));
	    		str = "\nread from db: " + rs.getString("name");
	    	}
	    	get("/", (request, response) -> str);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * Provides a connection to numis.io database on heroku platform.
     * <p>
     * There are two versions of this method. One for when we use
     * the JDBC_DATABASE_URL variable or the Uri directly.
     * (see {@link https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java}})
     * <b>Note:</b> Plans to update. Only for Testing
     * 
     * @return Connection to JDBC_DATABASE_URL
     * @throws URISyntaxException
     * @throws SQLException
     */
    private static Connection getConnection() throws URISyntaxException, SQLException {
    	String uri = "qwnftoedixnoiy:mPElNOqHA_kY9hIR0HdDvCeC_t@ec2-23-21-"
    			+ "55-25.compute-1.amazonaws.com:5432/da4sj8g02keohe";
    	URI numisDbUri = new URI(System.getenv(uri));
    	
    	LOGGER.info("Created URI object");
    	
    	String username = numisDbUri.getUserInfo().split(":")[0];
    	String password = numisDbUri.getUserInfo().split(":")[1];
    	LOGGER.info("username: " + username + ", password: " + password);
    	String numisDbUrl = "jdbc:postgresql://" + numisDbUri.getHost() + ':' 
    			+ numisDbUri.getPort() + numisDbUri.getPath();
    	
    	return DriverManager.getConnection(numisDbUrl, username, password);
    }

}
