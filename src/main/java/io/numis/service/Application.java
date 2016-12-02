package io.numis.service;

import static spark.Spark.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import io.numis.formatter.FormatHTML;
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
	
	private static String str = "";
	
	// Logger info
	private final static Logger LOGGER = Logger.getLogger(Application.class.getName());

	public void init() {
		Driver driver;
		Session session;
		try {
			LOGGER.info("Attempting to establish a connection");
			driver = getConnection();
			session = driver.session();
			LOGGER.info("Established connection");
			session.run("CREATE (n:Person {name:'Hello,'})");
			session.run("CREATE (n:Person {name:'Im'})");
			session.run("CREATE (n:Person {name:'Birik'})");
			session.run("CREATE (n:Person {name:'Ibimia'})");
			StatementResult result = session.run("MATCH (n:Person) RETURN n.name AS name");
			String str = "";
			while ( result.hasNext() ){
				Record record = result.next();
				str += "\n" + record.get("name").asString();
			}

	    	String formattedString = FormatHTML.getFormattedString(str);
	    	LOGGER.info(formattedString);
	    	get("/", (request, response) -> formattedString);
	    	session.close();
	    	LOGGER.info("session closed");
	    	driver.close();
	    	LOGGER.info("driver closed");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
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
     * @return Connection to JDBC_DATABASE_URL environment url variable
     * @throws URISyntaxException
     * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
     */
	private static Driver getConnection() throws URISyntaxException, SQLException, 
								ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		Class.forName("org.neo4j.jdbc.Driver");
		
		String graphenedbURL = System.getenv("GRAPHENEDB_TEAL_BOLT_URL");
		LOGGER.info("graphene db url: " + graphenedbURL);
	    String graphenedbUser = System.getenv("GRAPHENEDB_TEAL_BOLT_USER");
	    LOGGER.info("Graphene db user: " + graphenedbUser);
	    String graphenedbPass = System.getenv("GRAPHENEDB_TEAL_BOLT_PASSWORD");
	    LOGGER.info("Graphene db pass: " + graphenedbPass);
	    Driver driver = GraphDatabase.driver( graphenedbURL, AuthTokens.basic( graphenedbUser, graphenedbPass ) );
	    LOGGER.info("Successfully created Driver");
		return driver;
	}
}
