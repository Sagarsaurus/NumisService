package io.numis.persistence.interfaces;

import java.util.Properties;

import io.numis.domain.interfaces.DomainNode;

/**
 * <h1>Persistence Interface</h1>
 * <p>
 * Persistence Interface that adds method stubs
 * for User modification properties.
 * </p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public interface Persistence {
	
	/**
     * <p>
     * Create a new domain node with list of properties.
     * </p>
     * 
     * @param properties Attributes of the domain node.
     * @return true: new node created
     *         false: exception thrown
     */
	public boolean create(Properties properties);
	
	/**
     * <p>
     * Delete node through id and delete request.
     * </p>
     * 
     * @param obj Domain Node object
     * @return true: deleted node
     *         false: failed to delete, status error
     */
	public boolean delete(Properties properties);
	
	/**
     * <p>
     * Modify property(s) of domain node referenced by id.
     * </p>
     * 
     * @param properties
     * @return true: modified/updated node property(s)
     *         false: exception thrown, status error
     */
	public boolean edit(Properties properties);
	
	/**
     * <p>
     * Retrieve specific node by id and related information.
     * </p>
     * 
     * TODO: Change the return type to NodeType 
     * that is implemented by all the different types of nodes.
     * 
     * @param properties (TODO: Change return type to NodeType!)
     * @return node Domain node
     */
	public DomainNode get(Properties properties);
}
