package io.numis.persistence;

import io.numis.domain.User;
import io.numis.persistence.interfaces.Persistence;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * <h1>PersistenceImpl</h1>
 * Abstract persistence implementation class that
 * modularizes between persistence interface
 * and model classes. Implements {@link Persistence}
 * <p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public abstract class PersistenceImpl implements Persistence {

    private final static Logger LOGGER = Logger.getLogger(PersistenceImpl.class.getName());

    public PersistenceImpl() {}
    
    @Override
    public boolean create(Properties properties) {
        Object obj = getObject(properties);
        String createStatement = getInsertStatement(obj);
        return runCypherCommand(createStatement);

    }

    public boolean delete(Properties properties) {
    	LOGGER.info("Deleting Object");
		String delete = getDeleteStatement(properties);
		LOGGER.info("Set delete statement");
		return runCypherCommand(delete);
    }

    public boolean edit(Properties properties) {
    	LOGGER.info("Editing Object");
		String editStatement = getEditStatement(properties);
		LOGGER.info("Established edit statement");
		return runCypherCommand(editStatement);
    }

    //TODO: Change the return type to NodeType that is implemented by all the different types of nodes
    public User get(Properties properties) {
        return null;
    }
    
    abstract public String getInsertStatement(Object obj);
    abstract public Object getObject(Properties properties);
    abstract public String getDeleteStatement(Properties properties);
    abstract public String getEditStatement(Properties properties);
    
    private boolean runCypherCommand(String cyperStatement) {
        Session session = null;
        try {
            Driver driver = DriverFactory.getInstance();
            LOGGER.info("got driver isntance");
            session = driver.session();
            LOGGER.info("got session");
            StatementResult a = session.run(cyperStatement);
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
}
