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

import io.numis.common.exceptions.DotenvException;

/**
 * <h1>DotenvDriver</h1>
 * 
 * Dotenv Driver class for establishing
 * a connection with the .env file and
 * read the properties.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class DotenvDriver implements Dotenv {

	// Class Logger
	// private final static Logger LOGGER = Logger.getLogger(DotenvDriver.class.getName());
	
	/**
	 * Load environment variable from .env file locally.
	 * 
	 * Environment variable file should not be committed 
	 * to the repository!
	 * 
	 * @param envVar - File environment variable name
	 * @return env - environment variable value or null
	 * @throws DotenvException - Environment variable can't be loaded
	 */
	@Override
	public String loadEnvVar(String envVar) throws DotenvException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Validates integrity of .env file.
	 * Make sure that the file exists and key-value
	 * pair entries are valid.
	 * 
	 * @throws DotenvException - .env file is corrupted or invalid
	 */
	@Override
	public void validateEnvFile() throws DotenvException {
		// TODO Auto-generated method stub
		
	}
	
}
