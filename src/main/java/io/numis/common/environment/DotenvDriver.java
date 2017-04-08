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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger LOGGER = LoggerFactory.getLogger(DotenvDriver.class);
	
	// <Key, Value> Pairs in the environment file
	private Map<String, String> variables;
	
	// directory location
	private String loc;
	
	// Empty Constructor
	public DotenvDriver() {
	}
	
	// Field Constructor
	public DotenvDriver(String loc) {
		setLoc(loc);
	}
	
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
		variables = new HashMap<>();
		try {
			variables.put("", "");
			// Catch exception purely for logging.
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			throw new DotenvException(e);
		}
	}

	/**
	 * @return the loc
	 */
	public String getLoc() {
		return loc;
	}

	/**
	 * @param loc the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}
}
