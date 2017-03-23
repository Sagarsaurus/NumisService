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
 * <h1>RestOperation</h1>
 * 
 * Contains method stubs and definitions
 * for each standard REST operation
 * for all domain nodes. i.e Persisting
 * Object, creating object, retrieving object,
 * execute query
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class RestOperation {

    // Class Logger
    private final static Logger LOGGER = Logger.getLogger(RestOperation.class.getName());

    /**
     * Retrieves node object by referencing node id and class.
     *
     * @param map - map object with passed in domain id and class
     * @return - node that is requested
     */
    public static DomainNode getNodeObject(Map<String, Object> map) {
        Long id = (Long) map.get("id");
        // TODO: Handle unchecked cast on domainClass.
        @SuppressWarnings("unchecked")
        Class<DomainNode> domainClass = (Class<DomainNode>) map.get("class");

        Transaction transaction = null;
        DomainNode node = null;
        org.neo4j.ogm.session.Session session;
        try {
            session = DriverFactory.getSessionFactory();
            LOGGER.log(Level.INFO, "Obtain and open session");
            transaction = session.beginTransaction();
            LOGGER.log(Level.INFO, "Begin session transaction");
            node = session.load(domainClass, id);
            LOGGER.log(Level.INFO, "Load node class and id into session");
        } catch (Exception e) {
            // TODO: Custom exception handling
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            if (transaction != null) {
                transaction.close();
                LOGGER.log(Level.INFO, "Clase transaction instance");
            }
        }
        return node;
    }

    /**
     * Create node transaction with session and persist (save) node.
     *
     * @param node - node being persisted through transaction
     * @return - true:  Create transaction and save node
     *           false: exception caught in session
     */
    public static boolean persistObject(Object node) {
        Transaction transaction = null;
        Session session;
        try {
            session = DriverFactory.getSessionFactory();
            LOGGER.log(Level.INFO, "Obtain and open session");
            transaction = session.beginTransaction();
            LOGGER.log(Level.INFO, "Begin session transaction");
            session.save(node);
            LOGGER.log(Level.INFO, "Save node into session");
            return true;
        } catch (Exception e) {
            // TODO: Custom exception handling
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return false;
        } finally {
            if (transaction != null) {
                transaction.close();
                LOGGER.log(Level.INFO, "Close transaction instance");
            }
        }
    }

    /**
     * Run cypher command query to be persisted to the database.
     *
     * @param cypherCommand - Cypher Query
     * @return - true:  Successfully executed statement
     *           false: exception, Session failed to run
     */
    public static boolean executeCypherQuery(String cypherCommand) {
        org.neo4j.driver.v1.Session session = null;
        try {
            Driver driver = DriverFactory.getDriverInstance();
            LOGGER.log(Level.INFO, "Obtain driver instance from DriverFactory");
            session = driver.session();
            LOGGER.log(Level.INFO, "Obtain driver session");
            session.run(cypherCommand);
            LOGGER.log(Level.INFO, "Run session statement: " + session.run(cypherCommand));
            // TODO: Insert logging!
            return true;
        } catch (Exception e) {
            // TODO: Custom exception handling
            LOGGER.log(Level.SEVERE, e.toString(), e);
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
                LOGGER.log(Level.INFO, "Close session");
                DriverFactory.closeConnection();
                LOGGER.log(Level.INFO, "Close DriverFactory connection");
            }
        }
    }

    /**
     * Create object transaction with session and delete nod.
     *
     * @param objectNode - node being deleted
     * @return - true:  delete object node
     *           false: exception caught in session
     */
    public static boolean deleteObject(Object objectNode) {
        Transaction tx = null;
        org.neo4j.ogm.session.Session session;
        try {
            session = DriverFactory.getSessionFactory();
            LOGGER.log(Level.INFO, "Obtain and open session");
            tx = session.beginTransaction();
            LOGGER.log(Level.INFO, "Begin session transaction");
            session.delete(objectNode);
            LOGGER.log(Level.INFO, "Delete node in session");
            tx.commit();
            LOGGER.log(Level.INFO, "Cmmit to transaction");
            return true;
            // TODO: Custom exception handling
        } catch (Exception e) {
            // TODO: Custom exception handling
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return false;
        } finally {
            if (tx != null)
                tx.close();
        }
    }
}
