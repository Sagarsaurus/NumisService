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
package io.numis.common.environment;

import io.numis.common.Variables;
import io.numis.common.exceptions.DotenvException;

/**
 * <h1>NumisDotenv</h1>
 *
 * Helper class that creates a {@link DotenvFactory} instance.
 * Provides helper methods to retrieve each key values.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class NumisDotenv {
	
	// DotenvFactory Instance
	private static Dotenv numisDotenv;
	
	// Default Constructor
	public NumisDotenv() {}
	
	/**
	 * Creates a DotenvFactory instance with the correct
	 * location of the .env file.
	 * 
	 * @return dotenv - configured instance of DotenvFactory 
	 */
	public static Dotenv createDotenvInstance() {
		try {
			numisDotenv = new DotenvFactory()
					.envLocation("src/main/resources")
					.createInstance();
			return numisDotenv;
		} catch (DotenvException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param env - cnfigured DotenvFactory Instance
	 * @return - GRAPHENEDB_TEAL_URL value
	 * @throws DotenvException - invalid value
	 */
	public static String retrieveTealURL(Dotenv env) throws DotenvException {
		return env.retrieveVariable(Variables.GRAPHENEDB_TEAL_URL.getValue());
	}
	
	/**
	 * 
	 * @param env - cnfigured DotenvFactory Instance
	 * @return - GRAPHENEDB_TEAL_BOLT_URL value
	 * @throws DotenvException - invalid value
	 */
	public static String retrieveTealBoltURL(Dotenv env) throws DotenvException {
		return env.retrieveVariable(Variables.GRAPHENEDB_TEAL_BOLT_URL.getValue());
	}
	
	/**
	 * 
	 * @param env - cnfigured DotenvFactory Instance
	 * @return - GRAPHENEDB_TEAL_BOLT_USER value
	 * @throws DotenvException - invalid value
	 */
	public static String retrieveTealBoltUser(Dotenv env) throws DotenvException {
		return env.retrieveVariable(Variables.GRAPHENEDB_TEAL_BOLT_USER.getValue());
	}
	
	/**
	 * 
	 * @param env - cnfigured DotenvFactory Instance
	 * @return - GRAPHENEDB_TEAL_BOLT_PASSWORD value
	 * @throws DotenvException - invalid value
	 */
	public static String retrieveTealBoltPassword(Dotenv env) throws DotenvException {
		return env.retrieveVariable(Variables.GRAPHENEDB_TEAL_BOLT_PASSWORD.getValue());
	}
	
	/**
	 * 
	 * @param env - cnfigured DotenvFactory Instance
	 * @return - USERNAME value
	 * @throws DotenvException - invalid value
	 */
	public static String retrieveUsername(Dotenv env) throws DotenvException {
		return env.retrieveVariable(Variables.USERNAME.getValue());
	}
	
	/**
	 * 
	 * @param env - cnfigured DotenvFactory Instance
	 * @return - PASSWORD value
	 * @throws DotenvException - invalid value
	 */
	public static String retrievePassword(Dotenv env) throws DotenvException {
		return env.retrieveVariable(Variables.PASSWORD.getValue());
	}
	
	/**
	 * 
	 * @param env - cnfigured DotenvFactory Instance
	 * @return - DRIVER value
	 * @throws DotenvException - invalid value
	 */
	public static String retrieveDriverName(Dotenv env) throws DotenvException {
		return env.retrieveVariable(Variables.DRIVER.getValue());
	}
	
	/**
	 * 
	 * @param env - cnfigured DotenvFactory Instance
	 * @return - URI value
	 * @throws DotenvException - invalid value
	 */
	public static String retrieveURI(Dotenv env) throws DotenvException {
		return env.retrieveVariable(Variables.URI.getValue());
	}
}
