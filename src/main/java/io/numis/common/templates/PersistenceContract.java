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
package io.numis.common.templates;

import io.numis.common.SNode;

import java.util.Properties;

/**
 * <h1>PersistenceContract</h1>
 * 
 * Contains standard CRUD operation
 * method stubs and defintions applied
 * to all domain persistence classes.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public interface PersistenceContract {

	/**
	 * GET request
	 * Retrieve node and its properties.
	 *
	 * @param properties - Node properties
	 * @return - true:  retrieve node
	 *           false: exception thrown, failed to retrieve node
	 */
	SNode retrieveNode(Properties properties);

	/**
	 * POST request
	 * Create new node with respective properties.
	 *
	 * @param properties - Node properties
	 * @return - true:  new node created
	 *           false: exception thrown, failed to create node
	 */
	boolean createNode(Properties properties);

	/**
	 * POST request
	 * Note: POST request instead of PUT request
	 *       because POST is not idempotent and thus
	 *       multiple requests can occur at the same time.
	 * Warning: Must consider PUT because OGM SET operations are idempotent.
	 * Update node property(s)
	 *
	 * @param properties - Node properties
	 * @return - true:  Update node
	 *           false: exception thrown, failed to update node
	 */
	boolean updateNode(Properties properties);

	/**
	 *
	 * @param properties - Node properties
	 * @return - true:  delete node
	 *           false: exception thrown, failed to delete node
	 */
	boolean deleteNode(Properties properties);
}
