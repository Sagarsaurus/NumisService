package io.numis.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class App {
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
    
    private static Connection getConnection() throws URISyntaxException, SQLException {
    	String testURI = "qwnftoedixnoiy:mPElNOqHA_kY9hIR0HdDvCeC_t@"
    			+ "ec2-23-21-55-25.compute-1.amazonaws.com:5432/da4sj8g02keohe";
    	URI numisDbUri = new URI(System.getenv(testURI));
    	
    	String username = numisDbUri.getUserInfo().split(":")[0];
    	String password = numisDbUri.getUserInfo().split(":")[1];
    	String numisDbUrl = "jdbc:postgresql://" + numisDbUri.getHost() + ':' 
    			+ numisDbUri.getPort() + numisDbUri.getPath();
    	
    	return DriverManager.getConnection(numisDbUrl, username, password);
    }
}
