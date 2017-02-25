package io.numis.persistence;

import io.numis.domain.interfaces.DomainNode;
import io.numis.persistence.interfaces.Persistence;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.ogm.transaction.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * <h1>PersistenceImpl</h1>
 * <p>
 * Abstract persistence implementation class that
 * modularizes between persistence interface
 * and model classes. Implements {@link Persistence}
 * </p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public abstract class PersistenceImpl implements Persistence {

	// Class Logger
    private final static Logger LOGGER = Logger.getLogger(PersistenceImpl.class.getName());
	private Class<DomainNode> domainNodeClass;

    public PersistenceImpl() {}
    
    /**
     * <p>
     * Create a new domain node with list of properties.
     * </p>
     * 
     * @param properties Attributes of the domain node.
     * @return true: new node created
     *         false: exception thrown
     */
    @Override
    public boolean create(Properties properties) {
        LOGGER.info("Get related object with set of properties.");
    	Object obj = getObject(properties);
        return persistObject(obj);
    }

    /**
     * <p>
     * Delete node through id and delete request.
     * </p>
     * 
     * @param obj Domain Node object
     * @return true: deleted node
     *         false: failed to delete, status error
     */
    public boolean delete(Properties properties) {
        LOGGER.info("Deleting Object");
    	Map<String, Object> map = getReadParameters(properties);
    	DomainNode node = getDomainNode(map);
    	return deleteObject(node);
    }

    /**
     * <p>
     * Modify property(s) of domain node referenced by id.
     * </p>
     * 
     * @param properties
     * @return true: modified/updated node property(s)
     *         false: exception thrown, status error
     */
    public boolean edit(Properties properties) {
        LOGGER.info("Updating Object");
        String editStatement = getEditStatement(properties);
        LOGGER.info("Established edit statement");
        return runCypherCommand(editStatement);
    }

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
    public DomainNode get(Properties properties) {
    	LOGGER.info("Get specified domain node");
    	Map<String, Object> map = getReadParameters(properties);
    	DomainNode node = getDomainNode(map);
    	return node;
    }
    
    /**
     * 
     * @param properties Node properties
     * @return the created object with its respective properties
     */
    abstract public Object getObject(Properties properties);

    /**
     * 
     * @param properties Node properties
     * @return deleteStatement Create node query
     */
    abstract public String getDeleteStatement(Properties properties);

    /**
     * 
     * @param properties - the properties to update in the selected user
     * @return the updated edit statement which can be run from the driver. 
     */
    abstract public String getEditStatement(Properties properties);
    
    /**
     * 
     * @param properties Node properties
     * @return map HashMap<String, Object> containing node id and class
     */
    abstract public HashMap<String, Object> getReadParameters(Properties properties);
    
    /**
     * 
     * @param cyperStatement string to execute
     * @return true: success, false: failed to execute command
     */
    private boolean runCypherCommand(String cypherStatement) {
        Session session = null;
        try {
            Driver driver = DriverFactory.getInstance();
            LOGGER.info("got driver isntance");
            session = driver.session();
            LOGGER.info("got session");
            StatementResult a = session.run(cypherStatement);
            LOGGER.info("ran session statement" + a.toString());
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (session != null) {
                session.close();
                LOGGER.info("session closed");
                DriverFactory.closeConnection();
            }
        }
    }
    
    /**
     * <p>
     * Create object transaction and save node.
     * </p>
     * 
     * @param o Node object with properties.
     * @return true: Create transaction and save node.
     *         false: exception caught 
     */
    private boolean persistObject(Object o) {
    	org.neo4j.ogm.session.Session session = null;
    	Transaction tx = null;
        try {
    		LOGGER.info("in the try for persist object");
            session = DriverFactory.getSessionFactory();
            LOGGER.info("begin transaction instance");
            tx = session.beginTransaction();
            LOGGER.info("got session");
            session.save(o);
            LOGGER.info("saved domain node " + o.getClass());
            return true;
        } catch (Exception e) {
        	LOGGER.info("Caught Exception " + e);
        	e.printStackTrace();
            return false;
        } finally {
            if (tx != null) {
            	LOGGER.info("Transaction closed");
                tx.close();
            }
    		LOGGER.info("finished with persist object method");
        }
    }
    
    /**
     * <p>
     * Create object transaction and save node.
     * </p>
     * 
     * @param o Node object with properties.
     * @return true: Create transaction and save node.
     *         false: exception caught 
     */
    private boolean deleteObject(Object o) {
    	org.neo4j.ogm.session.Session session = null;
    	Transaction tx = null;
        try {
    		LOGGER.info("in the try for delete object");
            session = DriverFactory.getSessionFactory();
            LOGGER.info("begin transaction instance");
            tx = session.beginTransaction();
            LOGGER.info("got session");
            session.delete(o);
            LOGGER.info("deleted domain node " + o.getClass());
            return true;
        } catch (Exception e) {
        	LOGGER.info("Caught Exception " + e);
        	e.printStackTrace();
            return false;
        } finally {
            if (tx != null) {
            	LOGGER.info("Transaction closed");
                tx.close();
            }
    		LOGGER.info("finished with delete object method");
        }
    }
    
    
    /**
     * <p>
     * Gets the the loaded session of the domain node id and class.
     * </p>
     * 
     * @param map Node mapping of node class and object
     * @return returnedObject DomainNode with loaded class and id
     */
    private DomainNode getDomainNode(Map<String, Object> map) {
    	Long id = (Long) map.get("id");
    	domainNodeClass = null;
    	// Addressing Type Safety warning
    	List<?> mapClass = (List<?>) map.get("class");
    	for (Object o : mapClass) {
    		if (o instanceof DomainNode) {
    			domainNodeClass.cast(map.get(o));
    		}
    	}
    	org.neo4j.ogm.session.Session session = null;
    	Transaction tx = null;
    	DomainNode returnedObject = null;
    	try {
    		LOGGER.info("in the try for get domain node");
    		session = DriverFactory.getSessionFactory();
            LOGGER.info("got session");
            tx = session.beginTransaction();
            LOGGER.info("begin transaction instance");
            returnedObject = session.load(domainNodeClass, id);
    	} catch (Exception e) {
			LOGGER.info("Could not load of type class: " + domainNodeClass.toString());
    	} finally {
    		if(tx != null) {
    			tx.close();
    			LOGGER.info("transaction closed");
    		}
    		LOGGER.info("finished with get domain node");
    	}
    	return returnedObject;
    }
}
    