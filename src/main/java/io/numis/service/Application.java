package io.numis.service;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;
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

	private final static Logger LOGGER = Logger.getLogger(Application.class.getName());
	private static UserService userService = new UserService();
	
	@Override
	public void init() {
		
		// create new user
		post("/api/v1/user/new", (request, response) -> {
			LOGGER.info("Create new node");
			userService.create(request, response);
			return response.body();
		});	
		
		get("/", (request, response) -> {
			return "Application loaded";
		});
		
		// update existing user
		post("/api/v1/user/update", (request, response) -> {
			LOGGER.info("Update node and properties");
			userService.update(request, response);
			return response.body();
		});
		
		// delete existing user
		post("/api/v1/user/delete", (request, response) -> {
			LOGGER.info("Delete Node");
			userService.destroy(request, response);
			return response.body(); 
		});
		
		// get existing user
		get("/api/v1/user/get", (request, response) -> {
			LOGGER.info("Get Node");
			userService.get(request, response);
			return response.body();
		});
	}
	
}
