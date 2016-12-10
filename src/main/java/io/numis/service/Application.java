package io.numis.service;

import static spark.Spark.*;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import io.numis.formatter.FormatHTML;
import io.numis.persistence.DriverFactory;
import io.numis.persistence.UserPresistenceImpl;
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

	private final static Logger LOGGER = Logger.getLogger(Application.class.getName());

	public void init() {
		Properties properties = new Properties();
		properties.setProperty("username", "sample_user");
		properties.setProperty("encrypted_password", "123");
		properties.setProperty("email", "email@email.com");
		properties.setProperty("birth_date","23/06/1993");
		properties.setProperty("first_name","Bob");
		properties.setProperty("last_name","Loblaw");
		properties.setProperty("phone_number","1234567890");
		
		UserPresistenceImpl userImpl = new UserPresistenceImpl();
		boolean worked = userImpl.saveUser(properties);
		LOGGER.info("" + worked);
		get("/", (request, response) -> worked);
		try {
			DriverFactory.closeConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
