package io.numis.service;

import java.util.Properties;
import java.util.logging.Logger;

import io.numis.domain.interfaces.DomainNode;
import io.numis.persistence.UserPresistenceImpl;
import io.numis.service.interfaces.GenericService;
import spark.Request;
import spark.Response;

public class UserService implements GenericService {
	
	private final static Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private UserPresistenceImpl userImpl = new UserPresistenceImpl();
	
	
	/**
	 * REST API call to create a User
	 */
	@Override
	public void create(Request request, Response response) {
		
		Properties properties = getProperties(request);
		boolean created = this.userImpl.createUser(properties);
		
		if (created) {
			LOGGER.info("created successfully");
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
		boolean delete = this.userImpl.deleteUser(properties);
		
		// TODO: update status to 202 (accepted) if queued
		
		if (delete) {
			LOGGER.info("deleted user");
			response.body("deleted user");
			// (OK)
			response.status(200);
		} else {
			response.body("failed to delete");
			// (No Content)
			response.status(204);
		}
	}
	
	/**
	 * REST API call to update a User
	 */
	@Override
	public void update(Request request, Response response) {
		
		Properties properties = getProperties(request);
		boolean updated = this.userImpl.editUser(properties);
		if (updated) {
			LOGGER.info("updated successfully");
			response.body("updated user successfully");
		} else {
			response.body("issues with updating the user");
		}
	}
	
	/**
	 * REST API call to get a User
	 * 
	 * @return a DomainNode Object which is in this case a user
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
