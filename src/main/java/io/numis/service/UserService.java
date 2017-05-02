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

import com.google.gson.Gson;
import io.numis.common.INode;
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
 * that extends {@link io.numis.service.templates.UserTemplate}
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
     * REST API call to get all nodes
     * <p>
     *
     * @return - List of all node(s)
     */
    @Override
    public Iterable<INode> getAll() {
        return null;
    }

    /**
     * READ request
     * REST API call to get a node
     * <p>
     *
     * @param request  - Get request
     * @param response - Get response body (node requested)
     */
    @Override
    public void getNode(Request request, Response response) {
        INode userNode = persistence.retrieveNode(getProperties(request));
        String userJson = new Gson().toJson(userNode);
        if (userNode != null) {
            // content-type
            response.type("application/json");
            LOGGER.log(Level.INFO, "Set content type to json. (GET)");
            // set status
            response.status(200);
            LOGGER.log(Level.INFO, "Set response status to 200 accepted (GET).");
            // Response Content
            response.body(userJson);
            LOGGER.log(Level.INFO, "Set response body to content of domain node retrieved.");
        } else {
            // Failed to get user node
            response.body("Error in retrieve request.");
            LOGGER.log(Level.INFO, "Set response error. Failed (get)");
            // User does not exist
            response.status(404);
            LOGGER.log(Level.INFO, "Failed status code 404 (GET)");
            // TODO: Perform redirect
        }
    }

    /**
     * POST request
     * REST API call to create a node
     * <p>
     *
     * @param request  - Create request
     * @param response - Create response body
     */
    @Override
    public void createNode(Request request, Response response) {
        if (persistence.createDomainNode(getProperties(request))) {
            // TODO: Properly set response body
            // content-type
            response.type("application/json");
            LOGGER.log(Level.INFO, "Set content type to json. (POST)");
            // set status
            response.status(201);
            LOGGER.log(Level.INFO, "Set response status to 201 created (POST).");
            // Response Content
            response.body();
            LOGGER.log(Level.INFO, "Set response body to content of domain node being created.");
        } else {
            // Failed to create user node
            response.body("Error in create request.");
            LOGGER.log(Level.INFO, "Set response error. Failed (POST)");
            // Did no create user
            response.status(400);
            LOGGER.log(Level.INFO, "Failed status code 400 (POST)");
            // TODO: Perform redirect
        }
    }

    /**
     * POST request
     * Note: POST request instead of PUT request
     * because POST is not idempotent and thus
     * multiple requests can occur at the same time.
     * Warning: Must consider PUT because OGM SET operations are idempotent.
     * REST API call to update a node
     * <p>
     *
     * @param request  - Update request
     * @param response - Update response body
     */
    @Override
    public void updateNode(Request request, Response response) {
        if (persistence.updateDomainNode(getProperties(request))) {
            // TODO: Properly set response body
            // content-type
            response.type("application/json");
            LOGGER.log(Level.INFO, "Set content type to json. (PUT)");
            // set status
            response.status(200);
            LOGGER.log(Level.INFO, "Set response status to 200 updated (PUT).");
            // Response Cntent
            response.body();
            LOGGER.log(Level.INFO, "Set response body to content of updated domain node.");
        } else {
            // Failed to update user node
            response.body("Error in update request.");
            LOGGER.log(Level.INFO, "Set response error. Failed (PUT)");
            // Did not update user
            response.status(400);
            LOGGER.log(Level.INFO, "Failed status code 400 (PUT)");
            // TODO: Perform redirect
        }
    }

    /**
     * DELETE request
     * REST API call to delete a node
     * <p>
     *
     * @param request  - Delete request
     * @param response - Delete response body
     */
    @Override
    public void deleteNode(Request request, Response response) {
        if (persistence.deleteDomainNode(getProperties(request))) {
            // TODO: Properly set response body
            // content-type
            response.type("application/json");
            LOGGER.log(Level.INFO, "Set content type to json. (DELETE)");
            // set status
            response.status(204);
            LOGGER.log(Level.INFO, "Set response status to 204 no-content (DELETE).");
            // Response Content
            response.body();
            LOGGER.log(Level.INFO, "Set response body to status of deleted node.");
        } else {
            // Failed to delete user node
            response.body("Error in delete request.");
            LOGGER.log(Level.INFO, "Set response error. Failed (DELETE)");
            // Did not delete user
            response.status(400);
            LOGGER.log(Level.INFO, "Failed status code 400 (DELETE)");
            // TODO: Perform redirect
        }
    }

    /**
     * Private helper method to return a list of properties from a given request.
     * <p>
     *
     * @param request     - the incoming request to extract all the parameters from
     * @return properties - updated properties param
     */
    private Properties getProperties(Request request) {
        Properties properties = new Properties();
        for (String param : request.queryParams())
            properties.setProperty(param, request.queryParams(param));
        return properties;
    }
}
