package io.numis.persistence;

import java.net.URISyntaxException;
import java.sql.SQLException;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class DriverFactory {
	private static DriverFactory driverFactory;
	
	private DriverFactory() {}
	
	public Driver createConnection() throws URISyntaxException, SQLException, 
		ClassNotFoundException, InstantiationException, IllegalAccessException {
	
		Class.forName("org.neo4j.jdbc.Driver");
		
		String graphenedbURL = System.getenv("GRAPHENEDB_TEAL_BOLT_URL");
		String graphenedbUser = System.getenv("GRAPHENEDB_TEAL_BOLT_USER");
		String graphenedbPass = System.getenv("GRAPHENEDB_TEAL_BOLT_PASSWORD");
		Driver driver = GraphDatabase.driver( graphenedbURL, AuthTokens.basic( graphenedbUser, graphenedbPass ) );
		return driver;
	}
	
	public static Driver getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, URISyntaxException, SQLException {
		if (driverFactory == null) driverFactory = new DriverFactory();
		return driverFactory.createConnection();
	}
	
	public static void closeConnection() {
		Driver driver;
		try {
			driver = getInstance();
			driver.close();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | URISyntaxException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver = null;
		}
	}
}
