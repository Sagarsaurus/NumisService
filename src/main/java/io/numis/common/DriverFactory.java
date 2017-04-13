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

import java.net.URISyntaxException;
import java.sql.SQLException;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import io.numis.common.environment.Dotenv;
import io.numis.common.environment.NumisDotenv;
import io.numis.common.exceptions.DotenvException;

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
	
	// NumisDotenv Instance
	private static Dotenv envInstance;

	// Private Empty Constructor
	private DriverFactory() {
	}

	/**
	 * Creates driver object to setup a
	 * connection through BOLT databse URI.
	 *
	 * @return driver - Established connection to the bolt database URI.
	 * @throws SQLException           - invalid SQL
	 * @throws URISyntaxException     - URI syntax invalid
	 * @throws IllegalAccessException - Unauthorized access
	 * @throws InstantiationException - Could not load access
	 * @throws ClassNotFoundException - Could not find bolt driver
	 * @throws DotenvException        - failed to retrieve var from .env file
	 */
	private Driver createConnection() throws URISyntaxException, SQLException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, DotenvException {
		// Prepare dotenv instance var
		envInstance = NumisDotenv.createDotenvInstance();
		// Return GraphDatabase driver instance
		return GraphDatabase.driver(NumisDotenv.retrieveTealBoltURL(envInstance),
				AuthTokens.basic(NumisDotenv.retrieveTealBoltUser(envInstance),
						NumisDotenv.retrieveTealBoltPassword(envInstance)));
	}

	/**
	 * Create driver and establish connection by calling
	 * createConnection() method.
	 *
	 * @throws SQLException           - invalid SQL
	 * @throws URISyntaxException     - URI syntax invalid
	 * @throws IllegalAccessException - Unauthorized access
	 * @throws InstantiationException - Could not load access
	 * @throws ClassNotFoundException - Could not find bolt driver
	 * @throws DotenvException        - failed to retrieve var from .env file 
	 */
	static Driver getDriverInstance() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, URISyntaxException, SQLException, DotenvException {
		if (driverFactory == null) driverFactory = new DriverFactory();
		return driverFactory.createConnection();
	}

	/**
	 * Opens a session with the Application Service.
	 *
	 * @return sessionFactory - open new database session with SessionFactory
	 * @throws DotenvException - failed to retrieve var from .env file
	 */
	static Session getSessionFactory() throws DotenvException {
		// Prepare dotenv instance var
		envInstance = NumisDotenv.createDotenvInstance();
		// Create Configuration
		Configuration sessionConfig = new Configuration();
		// Set parameters
		sessionConfig.driverConfiguration()
				.setDriverClassName(NumisDotenv.retrieveDriverName(envInstance))
				.setURI(NumisDotenv.retrieveURI(envInstance))
				.setCredentials(NumisDotenv.retrieveUsername(envInstance), 
						NumisDotenv.retrievePassword(envInstance));
		// Create new session with package name and configuration.
		SessionFactory sessionFactory = new SessionFactory(sessionConfig, "io.numis");
		return sessionFactory.openSession();
	}

	/**
	 * Close driver instance session with DriverFactory.
	 */
	static void closeConnection() {
		Driver driver;
		try {
			driver = getDriverInstance();
			driver.close();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | URISyntaxException
				| SQLException | DotenvException e) {
			e.printStackTrace();
		} finally {
			driver = null;
		}
	}
}