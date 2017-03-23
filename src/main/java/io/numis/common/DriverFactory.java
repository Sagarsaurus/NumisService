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

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * <h1>DriverFactory</h1>
 * 
 * Factory class to establish connection
 * with Graph Database through the URL's.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
class DriverFactory {

    // DriverFactory instance
    private static DriverFactory driverFactory;

    // Private Empty Constructor
    private DriverFactory() {
    }

    /**
     * Creates driver object to setup a
     * connection through BOLT databse URI.
     *
     * @return driver - Established connection to the bolt database URI.
     */
    private Driver createConnection() {
        // Prepare Bolt URL variables
        // TODO: Prepare dotenv resource package to preserve environment variables.
        String graphenedbURL = System.getenv("GRAPHENEDB_TEAL_BOLT_URL");
        String grapheneDbUser = System.getenv("GRAPHENEDB_TEAL_BOLT_USER");
        String grapheneDbPassword = System.getenv("GRAPHENEDB_TEAL_BOLT_PASSWORD");

        return GraphDatabase.driver(graphenedbURL, AuthTokens.basic(grapheneDbUser, grapheneDbPassword));
    }

    /**
     * Create driver and establish connection by calling
     * createConnection() method.
     *
     * @return driverFactory.createConnection() Open new GraphDB connection.
     */
    static Driver getDriverInstance() {
        if (driverFactory == null) driverFactory = new DriverFactory();
        return driverFactory.createConnection();
    }

    /**
     * Opens a session with the Application Service.
     *
     * @return sessionFactory.openSession() - open new database session with SessionFactory
     */
    static Session getSessionFactory() {
        SessionFactory sessionFactory = new SessionFactory("io.numis");
        return sessionFactory.openSession();
    }

    /**
     * Close driver instance session with DriverFactory.
     */
    static void closeConnection() {
        Driver driver = getDriverInstance();
        driver.close();
    }
}
