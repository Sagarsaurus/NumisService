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
package io.numis.persistence;

import java.util.HashMap;
import java.util.Properties;

/**
 * <h1>GroupPersistence</h1>
 * 
 * Group Persistence implementation class contains
 * methods to run the respective CRUD queries
 * for the Group node api. Extends {@link GenericPersistence}
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class GroupPersistence extends GenericPersistence {

	/**
	 * Get node object with given properties.
	 * i.e: return Node properties
	 *
	 * @param properties - Node properties
	 * @return node - the created object with its repsective properties
	 */
	@Override
	public Object getNode(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get node class id and name parameters.
	 *
	 * @param properties - Node properties
	 * @return HashMap - class id and name
	 */
	@Override
	public HashMap<String, Object> getClassParameters(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get update query statement.
	 * <p>
	 * Helper method to build strings in this format:<br>
	 * <p>MATCH(s) WHERE id(s) = 25 SET s.encrypted_password = '12345890', s.last_name = 'last name',<br>
	 * s.email = 'karan@numis.io', s.phone_number = '1234567890', s.first_name = 'some user', <br>
	 * s.birth_date = '02/13/1993', s.username = 'username' RETURN s;
	 * <p> The user is selected based off of the id
	 *
	 * @param properties - Node properties
	 * @return update query - the update node cypher query
	 */
	@Override
	public String updateStatement(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

}
