package io.numis.persistence;

import io.numis.domain.interfaces.DomainNode;
import io.numis.persistence.interfaces.Persistence;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.summary.ResultSummary;
import org.neo4j.driver.v1.util.Pair;

import java.sql.ResultSet;
import java.util.ArrayList;
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

    public DomainNode get(Properties properties) {
    	LOGGER.info("getting the object");
    	String readStatement = getReadStatement(properties);
    	runGetCommand(readStatement);
        return null;
    }
    
    abstract public String getInsertStatement(Object obj);
    abstract public Object getObject(Properties properties);
    abstract public String getDeleteStatement(Properties properties);
    abstract public String getEditStatement(Properties properties);
    abstract public String getReadStatement(Properties properties);
    
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
    
    private boolean runGetCommand(String cypherStatement) {
    	Session session = null;
        try {
            Driver driver = DriverFactory.getInstance();
            LOGGER.info("got driver isntance");
            session = driver.session();
            LOGGER.info("got session for get command");
            StatementResult a = session.run(cypherStatement);
            
            LOGGER.info("ran session statement" + a.toString());
            ArrayList<Record> recordList = new ArrayList<Record>();
            while(a.hasNext()) {
            	Record record = a.next();
            	for (Pair<String, Value> list : record.fields()) {
					System.out.println(list.value());
				}
            	recordList.add(record);
            }
            System.out.println(recordList.size());
            return true;
        } catch (Exception e) {
        	LOGGER.info(e.getMessage());
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
