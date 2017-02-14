package io.numis.persistence;

import io.numis.domain.User;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

import static org.apache.http.client.utils.DateUtils.formatDate;

/**
 * <h1>UserPersistenceImpl</h1>
 * User Persistence implementation class contains
 * methods to run the respective CRUD queries
 * for the User api. extends {@link PersistenceImpl}
 * <p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class UserPersistenceImpl extends PersistenceImpl {
    private final static Logger LOGGER = Logger.getLogger(UserPersistenceImpl.class.getName());
    
    //Create user variable block
    private static final String USERNAME = "$username$";
    private static final String ID = "$id$";
    private static final String ENCRYPTED_PASSWORD = "$encrypted_password$";
    private static final String EMAIL = "$email$";
    private static final String BIRTH_DATE = "$birth_date$";
    private static final String FIRST_NAME = "$first_name$";
    private static final String LAST_NAME = "$last_name$";
    private static final String PHONE_NUMBER = "$account_number$";
    private static final String ACCOUNT_NUMBER = "$account_number$";
    private static final String ROUTING_NUMBER = "$routing_number$";
    private static final String ACCOUNT_BALANCE = "$account_balance$";
    private static final String CREATE_USER =  "CREATE (u:User {"
            + "username: " + USERNAME + ", encrypted_password: "+ENCRYPTED_PASSWORD+", email: "+ EMAIL +", birth_date: "+BIRTH_DATE+", " +
            "first_name: "+FIRST_NAME+", last_name: "+LAST_NAME+", phone_number: "+PHONE_NUMBER +", account_number: "+ACCOUNT_NUMBER+", " +
            "routing_number: "+ROUTING_NUMBER+", account_balance:"+ACCOUNT_BALANCE+"})";

    /**
     * 
     * @param obj
     * @return createStatement Create string statement
     */
    @Override
    public String getInsertStatement(Object obj) {
        User user = (User) obj;
        String createUserStatement = CREATE_USER
                                        .replace(USERNAME, user.getUsername())
                                        .replace(ENCRYPTED_PASSWORD, user.getEncryptedPassword())
                                        .replace(EMAIL, user.getEmail())
                                        .replace(BIRTH_DATE, formatDate(user.getBirthDate()))
                                        .replace(FIRST_NAME, user.getFirstName())
                                        .replace(LAST_NAME, user.getLastName())
                                        .replace(PHONE_NUMBER,user.getPhoneNumber())
                                        .replace(ACCOUNT_NUMBER, user.getAccountNumber()+"")
                                        .replace(ROUTING_NUMBER, user.getRoutingNumber()+"")
                                        .replace(ACCOUNT_BALANCE, user.getAccountBalance()+"");
        return createUserStatement;
    }

    /**
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

	@Override
	public String getReadStatement(Properties properties) {
		String id = properties.getProperty("id");
		String readStatement = " Match(user) where id(user) = " + id + " return user;";
		return readStatement;
	}
	
	@Override
	public HashMap<String, Object> getReadParameters(Properties properties) {
		Long id = Long.parseLong((String) properties.get("id"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("class", User.class);
		map.put("id", id);
		return map;
	}
	
	/**
     * Used to delete a user by user id
     * 
     * @param properties - properties of the user to be deleted
     * @return GraphDB statement that deletes the user
     */
    @Override
    public String getDeleteStatement(Properties properties) {
        String objectId = properties.getProperty("id");
        String delete_statement = "MATCH (n { id: '" + objectId + "' }) DETACH DELETE n";
        return delete_statement;
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
