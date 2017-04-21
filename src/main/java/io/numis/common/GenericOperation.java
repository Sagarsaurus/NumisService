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

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

/**
 * <h1>GenericOperation</h1>
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
public class GenericOperation {

	// Class Logger
	private final static Logger LOGGER = Logger.getLogger(GenericOperation.class.getName());
	
	/**
	 * Retrieves node object by referencing node id and class.
	 *
	 * @param domainMap - map object with passed in domain id and class
	 * @return          - node that is requested
	 */
	public static DomainObject getNodeObject(Map<String, Object> domainMap) {
		LOGGER.log(Level.INFO, "Start getting the node and it's properties.");
		Long domainId = (Long) domainMap.get("id");
		@SuppressWarnings("unchecked")
		Class<DomainObject> domainClass = (Class<DomainObject>) domainMap.get("class");
		Transaction tx = null;
		DomainObject objectNode = null;
		Session session;
		try {
			session = DriverFactory.getSessionFactory();
			LOGGER.log(Level.INFO, "Obtain and open session instance.");
			tx = session.beginTransaction();
			LOGGER.log(Level.INFO, "Begin session transaction");
			objectNode = session.load(domainClass, domainId);
			LOGGER.log(Level.INFO, "Load node class and id");
		} catch (Exception e) {
			// TODO: Custom exception handling
			LOGGER.log(Level.SEVERE, e.toString(), e);
		} finally {
			if (tx != null) {
				tx.close();
				LOGGER.log(Level.INFO, "Close transaction instance");
			}
		}
		return objectNode;
	}
	
	/**
	 * Create node transaction with session and persist (save) node.
	 *
	 * @param node - node being persisted through transaction
	 * @return     - true:  Create transaction and save node
	 *               false: exception caught in session
	 */
	public static boolean persistObject(Object domainNode) {
		Transaction tx = null;
		Session session;
		try {
			session = DriverFactory.getSessionFactory();
			LOGGER.log(Level.INFO, "Obtain and open session");
			session.beginTransaction();
			LOGGER.log(Level.INFO, "Begin session transaction");
			session.save(domainNode);
			LOGGER.log(Level.INFO, "Save node into session");
			return true;
		} catch(Exception e) {
			// TODO: Custom exception handling
			LOGGER.log(Level.SEVERE, e.toString(), e);
			return false;
		} finally {
			if (tx != null) {
				tx.close();
				LOGGER.log(Level.INFO, "Close transaction instance");
			}
		}
	}
	
	/**
	 * Run cypher command query to be persisted to the database.
	 *
	 * @param cypherCommand - Cypher Query
	 * @return              - true:  Successfully executed statement
	 *                        false: exception, Session failed to run
	 */
	public static boolean executeCypherQuery(String cypherCommand) {
		return false;
	}
	
	/**
	 * Create object transaction with session and delete nod.
	 *
	 * @param objectNode - node being deleted
	 * @return           - true:  delete object node
	 *                     false: exception caught in session
	 */
	public static boolean deleteObject(Object objectNode) {
		return false;
	}
}
