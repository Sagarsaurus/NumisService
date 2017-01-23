package io.numis.persistence;

import io.numis.domain.User;
import io.numis.domain.Utils;
import io.numis.persistence.interfaces.Persistence;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import static org.apache.http.client.utils.DateUtils.formatDate;

/**
 * Created by yuetinggg on 1/19/17.
 */



//TODO: Set it back to implementing Persistence
public abstract class PersistenceImpl {

    private final static Logger LOGGER = Logger.getLogger(PersistenceImpl.class.getName());

    public PersistenceImpl() {}

    //TODO: Change names of methods after Persistence is changed
    //@Override
    public boolean create(Properties properties) {

        Object obj = getObject(properties);
        String createStatement = getInsertStatement(obj);
        return runCypherCommand(createStatement);

    }

    abstract public String getInsertStatement(Object obj);
    abstract public Object getObject(Properties properties);

    private String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        String date_string = df.format(date);
        return date_string;
    }

    //@Override
    public boolean delete(Properties properties) {
        return false;
    }

    //@Override
    public boolean edit(Properties properties) {
        return false;
    }

    //@Override
    //TODO: Change the return type to NodeType that is implemented by all the different types of nodes
    public User get(Properties properties) {
        return null;
    }

    //Exactly the same method as Karan's from UserPresistenceImpl
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
