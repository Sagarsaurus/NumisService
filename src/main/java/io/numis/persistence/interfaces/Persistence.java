package io.numis.persistence.interfaces;

import java.util.Properties;

import io.numis.domain.User;

public interface Persistence {

	//TODO: Change method stubs after Persistence Implementation is done
	
	public boolean createUser(Properties properties);
	
	public boolean deleteUser(Properties properties);
	
	public boolean editUser(Properties properties);
	
	public User getUser(Properties properties);
	
}
