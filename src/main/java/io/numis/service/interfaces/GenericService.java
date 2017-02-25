package io.numis.service.interfaces;

import io.numis.domain.interfaces.DomainNode;
import spark.Request;
import spark.Response;

/**
 * <h1>GenericService</h1>
 * <p>
 * Interface that provides the standard
 * set of CRUD operations for each domain node.
 * </p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public interface GenericService {
	
	/**
	 * REST API call to create a User
	 * 
	 * @param request
	 * @param response
	 */
	public void create(Request request, Response response);
	
	/**
	 * REST API call to delete a User
	 * 
	 * @param request
	 * @param response
	 */
	public void destroy(Request request, Response response);
	
	/**
	 * REST API call to update a User
	 * 
	 * @param request
	 * @param response
	 */
	public void update(Request request, Response response);
	
	/**
	 * REST API call to get a User
	 * 
	 * @return a DomainNode Object user object
	 * @param request
	 * @param response
	 */
	public DomainNode get(Request request, Response response);
}
