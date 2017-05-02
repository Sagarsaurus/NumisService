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
package io.numis.persistence;

import io.numis.common.GenericOperation;
import io.numis.common.INode;
import io.numis.common.templates.PersistenceContract;

import java.util.HashMap;
import java.util.Properties;

/**
 * <h1>GenericPersistence</h1>
 *
 * Abstract persistence implementation class that
 * modularizes between persistence interfaces
 * and model classes. Implements {@link io.numis.common.templates.PersistenceContract}
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class GenericPersistence implements PersistenceContract {

    // Hashmap var used to retrieve class and id.
    static HashMap<String, Object> domainMap;

    // Empty Constructor
    GenericPersistence() {}

    /**
     * GET request
     * Retrieve node and its properties.
     * <p>
     *
     * @param properties - Node properties
     * @return           - true:  retrieve node
     *                     false: exception thrown, failed to retrieve node
     */
    public INode retrieveNode(Properties properties) {
        return GenericOperation.getNodeObject(getClassParameters(properties));
    }

    /**
     * POST request
     * Create new node with respective properties.
     * <p>
     *
     * @param properties - Node properties
     * @return           - true:  new node created
     *                     false: exception thrown, failed to create node
     */
    public boolean createDomainNode(Properties properties) {
        return GenericOperation.persistObject(getNode(properties));
    }

    /**
     * POST request
     * Note: POST request instead of PUT request
     *       because POST is not idempotent and thus
     *       multiple requests can occur at the same time.
     * Warning: Must consider PUT because OGM SET operations are idempotent.
     * Update node property(s)
     *
     * @param properties - Node properties
     * @return           - true:  Update node
     *                     false: exception thrown, failed to update node
     */
    public boolean updateDomainNode(Properties properties) {
        return GenericOperation.executeCypherQuery(updateQuery(properties));
    }

    /**
     * DELETE request
     * Delete the node and properties as well
     * as rspective relationships.
     * <p>
     *
     * @param properties - Node properties
     * @return           - true:  delete node
     *                     false: exception thrown, failed to delete node
     */
    public boolean deleteDomainNode(Properties properties) {
        return GenericOperation.
                deleteObject(GenericOperation.
                        getNodeObject(getClassParameters(properties)));
    }

    /**
     * Get update query statement.
     * <p>
     * Helper method to build strings in this format:<br>
     * MATCH(s) WHERE id(s) = 25 SET s.encrypted_password = '12345890', s.last_name = 'last name',<br>
     * s.email = 'karan@numis.io', s.phone_number = '1234567890', s.first_name = 'some user', <br>
     * s.birth_date = '02/13/1993', s.username = 'username' RETURN s;
     * <p>
     * The user is selected based off of the id
     * <p>
     *
     * @param properties    - Node properties
     * @return update query - the update node cypher query
     */
    public abstract String updateQuery(Properties properties);

    /**
     * Get node object with given properties.
     * (i.e: return Node properties)
     * <p>
     *
     * @param properties - Node properties
     * @return node      - the created object with its repsective properties
     */
    public abstract Object getNode(Properties properties);

    /**
     * Get node class id and name parameters.
     * <p>
     *
     * @param properties - Node properties
     * @return HashMap   - class id and name
     */
    public abstract HashMap<String, Object> getClassParameters(Properties properties);
}
