package io.numis.persistence;

import io.numis.domain.User;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * <h1>UserPersistenceImpl</h1>
 * <p>
 * User Persistence implementation class contains
 * methods to run the respective CRUD queries
 * for the User API. extends {@link PersistenceImpl}
 * </p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class UserPersistenceImpl extends PersistenceImpl {
    private final static Logger LOGGER = Logger.getLogger(UserPersistenceImpl.class.getName());

    /**
     * <p>
     * Get user object with properties.
     * </p>
     * 
     * @param properties
     * @return user User object with respective properties
     */
    @Override
    public Object getObject(Properties properties) {
        User user = new User(properties);
        LOGGER.info("Created " + user.getUsername() + " User");
        return user;
    }
	
	/**
	 * <p>
     * Gets the specific user node class and string parameters for ogm.
     * </p>
     * 
     * @param properties Node properties
     * @return map HashMap<String, Object> containing node id and class
     */
	@Override
	public HashMap<String, Object> getReadParameters(Properties properties) {
		Long id = Long.parseLong((String) properties.get("id"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("class", User.class);
		map.put("id", id);
		LOGGER.info("id is " + map.get("id"));
		return map;
	}
	
    /**
     * Helper method to build strings in this format:<br>
     * <p>MATCH(s) WHERE id(s) = 25 SET s.encrypted_password = '12345890', s.last_name = 'last name',<br>
     *     s.email = 'karan@numis.io', s.phone_number = '1234567890', s.first_name = 'some user', <br>
     *     s.birth_date = '02/13/1993', s.username = 'username' RETURN s;
     * <p> The user is selected based off of the id
     * 
     * @param properties - the properties to update in the selected user
     * @return the updated edit statement which can be run from the driver. 
     */
    @Override
    public String getEditStatement(Properties properties) {
        String id = properties.getProperty("id");
        String updateStatement = " MATCH(user) WHERE id(user) = " + id + " SET";
        String buildString = "";

        for (Object property : properties.keySet()) {
            if (!property.equals("id")) {
                buildString = " user." + property + " = '" + properties.getProperty((String) property) + "',";
                updateStatement += buildString;
            }
        }
        updateStatement = updateStatement.substring(0, updateStatement.length()-1); // removes trailing comma
        updateStatement += " RETURN user;";
        return updateStatement;
    }
}
