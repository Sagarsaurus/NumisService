package io.numis.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

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
public class NumisApp {
    public static void main( String[] args ) throws Exception {
    	Connection connection = getConnection();
    	
    	Statement stmt = connection.createStatement();
    	stmt.executeUpdate("DROP TABLE IF EXISTS users");
    	stmt.executeUpdate("CREATE TABLE users (tick timestamp)");
    	stmt.executeUpdate("INSERT INTO users VALUES (now())");
    	ResultSet rs = stmt.executeQuery("SELECT tick FROM users");
    	while (rs.next())
    		System.out.println("Read from DB: " + rs.getTimestamp("tick"));
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
    	String uri = "qwnftoedixnoiy:mPElNOqHA_kY9hIR0HdDvCeC_t@ec2-23-21-55-25."
    			+ "compute-1.amazonaws.com:5432/da4sj8g02keohe";
    	URI numisDbUri = new URI(System.getenv(uri));
    	
    	String username = numisDbUri.getUserInfo().split(":")[0];
    	String password = numisDbUri.getUserInfo().split(":")[1];
    	String numisDbUrl = "jdbc:postgresql://" + numisDbUri.getHost() + ':' 
    			+ numisDbUri.getPort() + numisDbUri.getPath();
    	
    	return DriverManager.getConnection(numisDbUrl, username, password);
    }
    
//    private static Connection getConnection() throws URISyntaxException, SQLException {
//    	String numisDbUri = System.getenv("JDBC_DATABASE_URL");
//    	return DriverManager.getConnection(numisDbUri);
//    }
}
