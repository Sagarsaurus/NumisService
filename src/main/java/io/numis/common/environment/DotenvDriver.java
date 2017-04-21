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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import io.numis.common.exceptions.DotenvException;

/**
 * <h1>DotenvDriver</h1>
 * 
 * Private inner class that provides
 * the instance methods for validating and retrieving
 * the variables from the .env file.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class DotenvDriver implements Dotenv {

	// Class Logger
	private final Logger LOGGER = Logger.getLogger(DotenvDriver.class.getName());
	
	// location of .env folder
	private String location;
	
	// key value pairs in the .env file
	private Map<String, String> variables;
	
	// Default constructor
	public DotenvDriver(String location) {
		setLocation(location);
	}
	
	/**
	 * Validates integrity of .env file.
	 * Make sure that the file exists and key-value
	 * pair entries are valid.
	 * 
	 * @throws DotenvException - .env file is corrupted or invalid
	 */
	@Override
	public void validateFileIntegrity() throws DotenvException {
		variables = new HashMap<>();
		try {
			String allContent = new String(Files.readAllBytes(Paths.get(location + "/numis.env")));
			String[] orderedContent = allContent.split("\n");
			for (String line : orderedContent) {
				String[] lineProp = line.split("=");
				// Invalid format
				if (lineProp.length != 2) {
					LOGGER.log(Level.SEVERE, "Improper format entry");
					throw new DotenvException("Incorrect format entry");
				}
				LOGGER.log(Level.INFO, "Putting key and value in hashmap");
				variables.put(lineProp[0], lineProp[1]);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, 
					ExceptionUtils.getStackTrace(e));
			throw new DotenvException(e);
		}
	}

	/**
	 * Load environment variable from .env file locally.
	 * 
	 * Environment variable file should not be committed 
	 * to the repository!
	 * 
	 * @param envVar           - File environment variable name
	 * @return env             - environment variable value or null
	 * @throws DotenvException - Environment variable can't be loaded
	 */
	@Override
	public String retrieveVariable(String envVar) throws DotenvException {
		if (variables == null)
			validateFileIntegrity();
		String val = variables.get(envVar);
		if (val == null) {
			LOGGER.log(Level.SEVERE, "Key value not found in .env file.");
			throw new DotenvException("Key value not found in numis.env file.");
		} else {
			LOGGER.log(Level.INFO, "Key loaded from .env file");
		}
		return val;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
}
