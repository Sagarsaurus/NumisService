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

import io.numis.common.Routes;

import static spark.Spark.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Numis</h1>
 *
 * Primary entry point for Numis Numis
 * service context
 * <p>
 * Contains the routing methods for
 * all the domain nodes. Will be run
 * and tested on local jetty server
 * and heroku domain for production use.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class Numis {

    // Class Logger
    private final static Logger LOGGER = Logger.getLogger(Numis.class.getName());

    // Domain Service Handlers
    private static UserService userService = new UserService();

    // Primary
    public static void main(String[] args) {
        // test
        // get("/hello", ((request, response) -> "Hello World"));

        //========================
        // User Routes
        //========================

        /**
         * GET user referenced at id
         * <p>
         *
         * @param path - /api/v1/users/get
         */
        get(Routes.GETUSER.URI(), ((request, response) -> {
            // TODO: Append node ID
            LOGGER.log(Level.INFO, "Retrieve User");
            userService.getNode(request, response);
            return response.body();
        }));

        /**
         * CREATE user from request
         * <p>
         *
         * @param path - /api/v1/user/new
         */
        post(Routes.CREATEUSER.URI(), ((request, response) -> {
            LOGGER.log(Level.INFO, "Create User");
            userService.createNode(request, response);
            return response.body();
        }));

        /**
         * UPDATE user properties referenced from id
         * <p>
         *
         * @param path - /api/v1/user/update
         */
        put(Routes.UPDATEUSER.URI(), ((request, response) -> {
            LOGGER.log(Level.INFO, "Update User");
            userService.updateNode(request, response);
            return response.body();
        }));

        /**
         * DELETE user referenced from id
         * <p>
         *
         * @param path - /api/v1/user/delete
         */
        delete(Routes.DELETEUSER.URI(), ((request, response) -> {
            LOGGER.log(Level.INFO, "Delete User");
            userService.deleteNode(request, response);
            return response.body();
        }));


        //========================
        // Group Routes
        //========================

        get(Routes.GETGROUP.URI(), ((request, response) -> "Hello World"));

        //========================
        // Contribution Routes
        //========================

        get(Routes.GETCONTRIBUTION.URI(), ((request, response) -> "Hello World"));

        //========================
        // Transaction Routes
        //========================

        get(Routes.GETTRANSACTION.URI(), ((request, response) -> "Hello World"));
    }
}
