/**
 * Copyright {2017} Numis.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.numis.common;

import org.neo4j.driver.v1.Driver;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>GenericOperation</h1>
 *
 * Contains method stubs and definitions
 * for each standard REST operation
 * for all domain nodes. (i.e Persisting
 * Object, creating object, retrieving object,
 * execute cypher query)
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class GenericOperation {

    // Class Logger
    private static final Logger LOGGER = Logger.getLogger(GenericOperation.class.getName());

    /**
     * Retrieves node object by referencing node id and class.
     * <p>
     *
     * @param domainMap - map object with passed in domain id and class
     * @return          - node that is requested
     */
    public static DomainObject getNodeObject(Map<String, Object> domainMap) {
        Long id = (Long) domainMap.get("id");
        // TODO: Handle unchecked cast on domainClass.
        @SuppressWarnings("unchecked")
        Class<DomainObject> domainClass = (Class<DomainObject>) domainMap.get("class");
        // Start transaction
        Transaction tx = null;
        DomainObject objectNode = null;
        org.neo4j.ogm.session.Session session;
        try {
            session = DriverFactory.getSessionFactory();
            LOGGER.log(Level.INFO, "Obtain and open session instance.");
            tx = session.beginTransaction();
            LOGGER.log(Level.INFO, "Begin session transaction.");
            objectNode = session.load(domainClass, id);
            LOGGER.log(Level.INFO, "Load class node and id.");
            tx.commit();
            LOGGER.log(Level.INFO, "Explicitly commit the session transaction.");
        } catch (Exception e) {
            // TODO: Handle custom exception.
            LOGGER.log(Level.SEVERE, "Could not load class: " + domainClass.toString(), e);
        } finally {
            if (tx != null)
                tx.close();
            LOGGER.log(Level.INFO  , "Close transaction instance.");
        }
        return objectNode;
    }

    /**
     * Create node transaction with session and persist (save) node.
     * <p>
     *
     * @param domainNode - node being persisted through transaction
     * @return           - true:  Create transaction and save node
     *                     false: exception caught in session
     */
    public static boolean persistObject(Object domainNode) {
        Session session;
        Transaction tx = null;
        try {
            session = DriverFactory.getSessionFactory();
            LOGGER.log(Level.INFO, "Obtain and open session instance.");
            tx = session.beginTransaction();
            LOGGER.log(Level.INFO, "Begin session transaction.");
            session.save(domainNode);
            LOGGER.log(Level.INFO, "Saving node");
            tx.commit();
            LOGGER.log(Level.INFO, "Explicitly commit the saved session transaction.");
            return true;
        } catch (Exception e) {
            // TODO: Handle custom exception.
            LOGGER.log(Level.SEVERE, "Could not obtain session factory.", e);
        } finally {
            if (tx != null)
                tx.close();
            LOGGER.log(Level.INFO, "Close transaction instance.");
        }
        return false;
    }

    /**
     * Run cypher command query to be persisted to the database.
     * <p>
     *
     * @param cypherCommand - Cypher Query
     * @return              - true:  Successfully executed statement
     *                        false: exception, Session failed to run
     */
    public static boolean executeCypherQuery(String cypherCommand) {
        Driver driver;
        org.neo4j.driver.v1.Session session = null;
        try {
            driver = DriverFactory.getDriverInstance();
            LOGGER.log(Level.INFO, "Obtain driver instance and createConnection.");
            session = driver.session();
            LOGGER.log(Level.INFO, "Obtain driver session instance.");
            session.run(cypherCommand);
            LOGGER.log(Level.INFO, "Run query: " + cypherCommand);
            return true;
        } catch (Exception e) {
            // TODO: Handle custom exception.
            LOGGER.log(Level.SEVERE, "Failed to create driver instace.", e);
            e.printStackTrace();
            return false;
        } finally {
            if (session != null)
                session.close();
            LOGGER.log(Level.INFO, "Close session instance.");
            DriverFactory.closeConnection();
            LOGGER.log(Level.INFO, "Close driver connection.");
        }
    }

    /**
     * Create object transaction with session and delete node.
     * <p>
     *
     * @param domainNode - node being deleted
     * @return           - true:  delete object node
     *                     false: exception caught in session
     */
    public static boolean deleteObject(Object domainNode) {
        Session session;
        Transaction tx = null;
        try {
            session = DriverFactory.getSessionFactory();
            LOGGER.log(Level.INFO, "Obtain and open session instance.");
            tx = session.beginTransaction();
            LOGGER.log(Level.INFO, "Begin session transaction.");
            session.delete(domainNode);
            LOGGER.log(Level.INFO, "Deleting node");
            tx.commit();
            LOGGER.log(Level.INFO, "Explicitly commit the delted session transaction.");
            return true;
        } catch (Exception e) {
            // TODO: Handle custom exception.
            LOGGER.log(Level.SEVERE, "Could not obtain session transaction.", e);
        } finally {
            if (tx != null)
                tx.close();
            LOGGER.log(Level.INFO, "Close transaction instance.");
        }
        return false;
    }
}
