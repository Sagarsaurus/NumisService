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

import io.numis.common.INode;
import spark.Request;
import spark.Response;

/**
 * <h1>ServiceContract</h1>
 *
 * Interface contract implemented by
 * all domain nodes.
 * <p>
 * getAll:     Return a list of all node(s)   (GET)
 * getNode:    Get node object and properties (GET)
 * createNode: Create node with properties    (POST)
 * updateNode: Destroy node and relationships (DELETE)
 * deleteNode: Update node property(s)        (PUT)
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public interface ServiceContract {

	/**
	 * READ request
	 * REST API call to get all nodes
	 * <p>
	 *
	 * @return - List of all node(s)
	 */
	Iterable<INode> getAll();

	/**
	 * READ request
	 * REST API call to get a node
	 * <p>
	 *
	 * @param request       - Get request
	 * @param response      - Get response body (node requested)
	 */
	void getNode(Request request, Response response);

	/**
	 * POST request
	 * REST API call to create a node
	 * <p>
	 *
	 * @param request  - Create request
	 * @param response - Create response body
	 */
	void createNode(Request request, Response response);

	/**
	 * POST request
	 * Note: POST request instead of PUT request
	 *       because POST is not idempotent and thus
	 *       multiple requests can occur at the same time.
	 * Warning: Must consider PUT because OGM SET operations are idempotent.
	 * REST API call to update a node
	 * <p>
	 *
	 * @param request  - Update request
	 * @param response - Update response body
	 */
	void updateNode(Request request, Response response);

	/**
	 * DELETE request
	 * REST API call to delete a node
	 * <p>
	 *
	 * @param request  - Delete request
	 * @param response - Delete response body
	 */
	void deleteNode(Request request, Response response);
}
