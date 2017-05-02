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
import spark.servlet.SparkApplication;

import static spark.Spark.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Numis</h1>
 *
 * Primary entry point for Numis
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
public class Numis implements SparkApplication {

    // Class Logger
    private final static Logger LOGGER = Logger.getLogger(Numis.class.getName());

    // Domain Service Handlers
    private static UserService userService = new UserService();

    /**
     * Invoked from the SparkFilter. Add routes here.
     */
    @Override
    public void init() {
        // test
        get("/hello", ((request, response) -> "Hello World"));
    }
}
