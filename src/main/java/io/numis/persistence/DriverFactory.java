package io.numis.persistence;

import java.net.URISyntaxException;
import java.sql.SQLException;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * <h1>DriverFactory</h1>
 * <p>
 * DriverFactory sets up the connection
 * and creates a session between the service
 * and neo4j database.
 * </p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class DriverFactory {
	private static DriverFactory driverFactory;
	
	private DriverFactory() {}
	
	/**
	 * <p>
	 * Creates a driver object to retrieve database BOLT URL
	 * and establishes a connection.
	 * </p>
	 * 
	 * @return driver Established connection to the bolt database URL.
	 * @throws URISyntaxException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Driver createConnection() throws URISyntaxException, SQLException, 
		ClassNotFoundException, InstantiationException, IllegalAccessException {
	
		Class.forName("org.neo4j.jdbc.Driver");
		
		String graphenedbURL = System.getenv("GRAPHENEDB_TEAL_BOLT_URL");
		String graphenedbUser = System.getenv("GRAPHENEDB_TEAL_BOLT_USER");
		String graphenedbPass = System.getenv("GRAPHENEDB_TEAL_BOLT_PASSWORD");
		Driver driver = GraphDatabase.driver( graphenedbURL, AuthTokens.basic( graphenedbUser, graphenedbPass ) );
		return driver;
	}
	
	/**
	 * <p>
	 * Ensures a driverFactory instance and creates a connection to the db.
	 * </p>
	 * 
	 * @return driverFactory Connection
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws URISyntaxException
	 * @throws SQLException
	 */
	public static Driver getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, URISyntaxException, SQLException {
		if (driverFactory == null) driverFactory = new DriverFactory();
		return driverFactory.createConnection();
	}
	
	/**
	 * <p>
	 * Opens a session with the Numis application service.
	 * </p>
	 * 
	 * @return session SessionFactory object referencing io.numis package.
	 */
	public static Session getSessionFactory() {
		SessionFactory session = new SessionFactory("io.numis");
		return session.openSession();
	}
	
	/**
	 * <p>
	 * Closes sesssion and sets driver object to null.
	 * </p>
	 */
	public static void closeConnection() {
		Driver driver;
		try {
			driver = getInstance();
			driver.close();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | URISyntaxException
				| SQLException e) {
			e.printStackTrace();
		} finally {
			driver = null;
		}
	}
}
