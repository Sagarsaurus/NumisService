package io.numis.service;

import java.util.Properties;
import java.util.function.Supplier;
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
		String username = request.queryParams("username");
		String encrypted_password = request.queryParams("encrypted_password");
		String email = request.queryParams("email");
		String birth_date = request.queryParams("birth_date");
		String first_name = request.queryParams("first_name");
		String last_name = request.queryParams("last_name");
		String phone_number = request.queryParams("phone_number");
		
		Properties properties = new Properties();
		properties.setProperty("username", username);
		properties.setProperty("encrypted_password", encrypted_password);
		properties.setProperty("email", email);
		properties.setProperty("birth_date", birth_date);
		properties.setProperty("first_name", first_name);
		properties.setProperty("last_name", last_name);
		properties.setProperty("phone_number", phone_number);
		
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
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * REST API call to update a User
	 */
	@Override
	public void update(Request request, Response response) {
		// TODO Auto-generated method stub
		
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

	

	
}
