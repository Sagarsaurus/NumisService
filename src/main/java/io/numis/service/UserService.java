package io.numis.service;

import java.util.Properties;
import java.util.logging.Logger;

import io.numis.domain.interfaces.DomainNode;
import io.numis.persistence.PersistenceImpl;
import io.numis.persistence.UserPersistenceImpl;
import io.numis.service.interfaces.GenericService;
import spark.Request;
import spark.Response;

public class UserService implements GenericService {
	
	private final static Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private PersistenceImpl persistence = new UserPersistenceImpl();
	
	/**
	 * REST API call to create a User
	 */
	@Override
	public void create(Request request, Response response) {
		
		Properties properties = getProperties(request);
		LOGGER.info("Creating User");
		boolean created = persistence.create(properties);


		if (created) {
			LOGGER.info("User Created successfully");
			response.body("done with creation of new user");
		} else {
			response.body("your shit wrong");				
		}
			
	}
	
	/**
	 * REST API call to delete a User
	 */
	@Override
	public void destroy(Request request, Response response) {
		Properties properties = getProperties(request);
		boolean delete = persistence.delete(properties);
		
		// TODO: update status to 202 (accepted) if queued
		
		if (delete) {
			LOGGER.info("deleted user");
			response.body("deleted user");
			// (OK)
			response.status(200);
		} else {
			response.body("failed to delete");
			// internal server error
			response.status(500);
		}
	}
	
	/**
	 * REST API call to update a User
	 */
	@Override
	public void update(Request request, Response response) {
		
		Properties properties = getProperties(request);
		boolean updated = persistence.edit(properties);
		if (updated) {
			LOGGER.info("updated successfully");
			response.body("updated user successfully");
			response.status(200);
		} else {
			response.body("issues with updating the user");
			// 500 is internal server error
			response.status(500);
		}
	}
	
	/**
	 * REST API call to get a User
	 * 
	 * @return a DomainNode Object user object
	 */
	@Override
	public DomainNode get(Request request, Response response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Private helper method to return a list of properties from a given request. 
	 * 
	 * @param request - the incoming request to extract all the parameters from
	 * @return a properties object which has the updated query parameters from the request body
	 */
	private Properties getProperties(Request request) {
		Properties properties = new Properties();
		for (String qParam: request.queryParams()) {
			properties.setProperty(qParam, request.queryParams(qParam));
		}
		return properties;
	}

	

	
}
