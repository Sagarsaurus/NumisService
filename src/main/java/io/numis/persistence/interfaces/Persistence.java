package io.numis.persistence.interfaces;

import java.util.Properties;
import io.numis.domain.User;

/**
 * <h1>Persistence Interface</h1>
 * Persistence Interface that adds method stubs
 * for User modification properties.
 * <p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public interface Persistence {
	
	/**
	 * Create a new user.
	 * 
	 * @param properties
	 * @return true: new user created
	 *         false: failed to create user
	 */
	public boolean create(Properties properties);
	
	/**
	 * Detach and delete a user by user id.
	 * 
	 * @param properties
	 * @return true: user deleted and detached from groups
	 *         false: failed tp delete user or detach from related groups
	 */
	public boolean delete(Properties properties);
	
	/**
	 * Modify property(s) of a specific user referenced by user id.
	 * 
	 * @param properties
	 * @return true: succesfully modified user information(s)
	 *         false: failed to edit user information
	 */
	public boolean edit(Properties properties);
	
	/**
	 * Retrieve specific user by id and related information.
	 * 
	 * TODO: Change the return type to NodeType 
	 * that is implemented by all the different types of nodes.
	 * 
	 * @param properties (TODO: Change return type to NodeType!)
	 * @return user User id that is searched
	 */
	public User get(Properties properties);
	
}
