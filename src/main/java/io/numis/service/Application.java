package io.numis.service;

import static spark.Spark.*;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import com.fasterxml.jackson.databind.util.JSONPObject;

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
		testGet();
		testPost();	
	}
	
	private void testGet() {
		get("/", (request, response) -> "worked");
	}
	
	private void testPost() {
		UserPresistenceImpl userImpl = new UserPresistenceImpl();
		post("/api/v1/user/new", (request, response) -> {
			String username = request.queryParams("username");
			String encrypted_password = request.queryParams("encrypted_password");
			String email = request.queryParams("email");
			String birth_date = request.queryParams("birth_date");
			String first_name = request.queryParams("first_name");
			String last_name = request.queryParams("last_name");
			String phone_number = request.queryParams("phone_number");

			Properties test_post_properties = new Properties();
			test_post_properties.setProperty("username", username);
			test_post_properties.setProperty("encrypted_password", encrypted_password);
			test_post_properties.setProperty("email", email);
			test_post_properties.setProperty("birth_date", birth_date);
			test_post_properties.setProperty("first_name", first_name);
			test_post_properties.setProperty("last_name", last_name);
			test_post_properties.setProperty("phone_number", phone_number);
			// TODO: RUN DATA VALIDATION HERE
			boolean created = userImpl.saveUser(test_post_properties);
			// TODO: Create custom body response here. 
			if (created) {
				LOGGER.info("created successfully");
				response.body("done with creation of new user");
			} else {
				response.body("your shit wrong");				
			}

			return "posted";
		});
		
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
