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
package io.numis.service;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.numis.common.Routes;
import spark.servlet.SparkApplication;

/**
 * <h1>Application</h1>
 * 
 * Primary entry point for Numis Application
 * service context
 * <p>
 * Implements {@link SparkApplication}
 * and contains the routing methods for
 * all domain nodes.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class App implements SparkApplication {

	// Class Logger
	private final static Logger LOGGER = Logger.getLogger(App.class.getName());
	
	// Domain Service Handlers
	private static UserService userService = new UserService();
	
	/**
	 * Invoked from the SparkFilter. Add routes here.
	 */
	@Override
	public void init() {
		// User Domain
		/**
		 * GET user referenced at id
		 * 
		 * @param path - /api/v1/users/get
		 */
		get(Routes.GETUSER.URI(), (request, response) -> {
			// TODO: Append node id if it exists
			LOGGER.log(Level.INFO, "GET user");
			userService.getNode(request, response);
			return response.body();
		});

		/**
		 * CREATE user from request
		 * 
		 * @param path - /api/v1/user/new
		 */
		post(Routes.CREATEUSER.URI(), (request, response) -> {
			LOGGER.log(Level.INFO, "CREATE user");
			userService.createNode(request, response);
			return response.body();
		});
		
		/**
		 * UPDATE user properties referenced from id
		 * 
		 * @param path - /api/v1/user/update
		 */
		put(Routes.UPDATEUSER.URI(), (request, response) -> {
			// TODO: Append properties that were updated
			LOGGER.log(Level.INFO, "UPDATE user");
			userService.updateNode(request, response);
			return response.body();
		});
		
		/**
		 * DELETE user referenced from id
		 * 
		 * @param path - /api/v1/user/delete
		 */
		delete(Routes.DELETEUSER.URI(), (request, response) -> {
			// TODO: Append user id that was deleted
			LOGGER.log(Level.INFO, "DELETE user");
			userService.deleteNode(request, response);
			return response.body();
		});
		// User Domain
	}
}
