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

import io.numis.common.SNode;
import io.numis.common.templates.ServiceContract;
import io.numis.persistence.GenericPersistence;
import io.numis.persistence.UserPersistence;
import io.numis.service.templates.UserTemplate;
import spark.Request;
import spark.Response;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>UserService</h1>
 * 
 * Service class for User domain
 * that implements {@link ServiceContract}
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class UserService extends UserTemplate {

    // Class Logger
    private final static Logger LOGGER = Logger.getLogger(UserService.class.getName());

    // UserPersistence
    private GenericPersistence persistence = new UserPersistence();

    /**
     * READ request
     * REST API call to get a node
     *
     * @param request  - Get request
     * @param response - Get response body (node requested)
     * @return - Node object
     */
    public SNode getNode(Request request, Response response) {
        SNode userNode = persistence.retrieveNode(getProperties(request));
        if (userNode != null) {
            // Set response body in json format
            response.body();
            LOGGER.log(Level.INFO, "Set response body (get)");
            // content-type
            response.type("application/json");
            LOGGER.log(Level.INFO, "Set response type to json (get)");
            // status code
            response.status(202);
            LOGGER.log(Level.INFO, "Success status code (get)");
            return userNode;
        } else {
            // Failed to get user node
            response.body("Error in request");
            LOGGER.log(Level.INFO, "Failed (get)");
            // User does not exist
            response.status(404);
            LOGGER.log(Level.INFO, "Failed status code (get)");
            // TODO: Perform redirect
            return null;
        }
    }

    /**
     * POST request
     * REST API call to create a node
     *
     * @param request  - Create request
     * @param response - Create response body
     */
    public void createNode(Request request, Response response) {
        if (persistence.createNode(getProperties(request))) {
            // Set response body in json format
            response.body();
            LOGGER.log(Level.INFO, "Set response body (create)");
            // content-type
            response.type("application/json");
            LOGGER.log(Level.INFO, "Set response type to json (create)");
            // status code
            response.status(201);
            LOGGER.log(Level.INFO, "Success status code (create)");
        } else {
            // Failed to create user node
            response.body("Error in request");
            LOGGER.log(Level.INFO, "Failed (create)");
            // status code
            response.status(400);
            LOGGER.log(Level.INFO, "Failed status code (create)");
            // TODO: Perform redirect
        }
    }

    /**
     * POST request
     * Note: POST request instead of PUT request
     * because POST is not idempotent and thus
     * multiple requests can occur at the same time.
     * Warning: Must consider impact vs PUT through application runtime.
     * REST API call to update a node
     *
     * @param request  - Update request
     * @param response - Update response body
     */
    public void updateNode(Request request, Response response) {
        if (persistence.updateNode(getProperties(request))) {
            // Set response body in json format
            response.body();
            LOGGER.log(Level.INFO, "Set response body (update)");
            // content-type
            response.type("application/json");
            LOGGER.log(Level.INFO, "Set response type to json (update)");
            // status code
            response.status(200);
            LOGGER.log(Level.INFO, "Success status code (update)");
        } else {
            // Failed to update user node
            response.body("Error in request");
            LOGGER.log(Level.INFO, "Failed (update)");
            // status code
            response.status(400);
            LOGGER.log(Level.INFO, "Failed status code (update)");
            // TODO: Perform redirect
        }
    }

    /**
     * DELETE request
     * REST API call to delete a node
     *
     * @param request  - Delete request
     * @param response - Delete response body
     */
    public void deleteNode(Request request, Response response) {
        if (persistence.deleteNode(getProperties(request))) {
            // Set response body in json format
            response.body();
            LOGGER.log(Level.INFO, "Set response body (delete)");
            // content-type
            response.type("application/json");
            LOGGER.log(Level.INFO, "Set response type to json (delete)");
            // status code
            response.status(200);
            LOGGER.log(Level.INFO, "Success status code (delete)");
        } else {
            // Failed to delete user node
            response.body("Error in request");
            LOGGER.log(Level.INFO, "Failed (delete)");
            // statud code (No Content)
            response.status(400);
            LOGGER.log(Level.INFO, "Failed status code (update)");
            // TODO: Perform redirect
        }
    }

    /**
     * Private helper method to return a list of properties from a given request.
     *
     * @param request - the incoming request to extract all the parameters from
     * @return a properties object which has the updated query parameters from the request body
     */
    private Properties getProperties(Request request) {
        Properties properties = new Properties();
        for (String param : request.queryParams())
            properties.setProperty(param, request.queryParams(param));
        return properties;
    }
}
