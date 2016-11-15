package io.numis.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class App {
    public static void main( String[] args ) throws Exception {
    	Connection connection = getConnection();
    }
    
    private static Connection getConnection() throws URISyntaxException, SQLException {
    	String testURI = "";
    	URI numisDbUri = new URI(System.getenv(testURI));
    	
    	String username = numisDbUri.getUserInfo().split(":")[0];
    	String password = numisDbUri.getUserInfo().split(":")[1];
    	String numisDbUrl = "jdbc:postgresql://" + numisDbUri.getHost() + numisDbUri.getPath();
    	
    	return DriverManager.getConnection(numisDbUrl, username, password);
    }
}
