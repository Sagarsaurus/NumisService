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
import java.util.Properties;
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
			handler = new FileHandler("/home/sunny/Documents/numis_logging.txt");
			LOGGER.addHandler(handler);
			LOGGER.info("Attempting to establish a connection");
			connection = getConnection();
			
			Statement stmt = connection.createStatement();
	    	ResultSet rs = stmt.executeQuery("SELECT * from SAMPLE");
	    	while (rs.next()) {
	    		LOGGER.info(rs.getString("name"));
	    		str += "\nread from db: " + rs.getString("name");
	    	}
	    	get("/", (request, response) -> str);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Provides a connection to numis.io database on heroku platform.
     * <p>
     * 
     * @return Connection to JDBC_DATABASE_URL
     * @throws URISyntaxException
     * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
     */
	private static Connection getConnection() throws URISyntaxException, SQLException, 
								ClassNotFoundException, InstantiationException, IllegalAccessException {
		String url = "postgres://qwnftoedixnoiy:mPElNOqHA_kY9hIR0HdDvCeC_t@ec2-"
				+ "23-21-55-25.compute-1.amazonaws.com:5432/da4sj8g02keohe";
		
		URI numisDbUri = new URI(System.getenv(url));
		Class.forName("org.postgresql.Driver").newInstance();

		
		String username = numisDbUri.getUserInfo().split(":")[0];
		String password = numisDbUri.getUserInfo().split(":")[1];
		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", password);
		props.setProperty("sslmode", "require");
		String numisUrl = "jdbc:postgresql://" + numisDbUri.getHost() + ':' 
				+ numisDbUri.getPort() + numisDbUri.getPath();
		
		return DriverManager.getConnection(numisUrl, props);
	}
}
