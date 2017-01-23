package io.numis.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import io.numis.domain.User;
import io.numis.persistence.interfaces.Persistence;

public class UserPresistenceImpl implements Persistence {
	
	private final static Logger LOGGER = Logger.getLogger(UserPresistenceImpl.class.getName());
	public UserPresistenceImpl() {}
	
	public User getUser(){
		return null;
	}
	
	@Override
	public boolean createUser(Properties properties) {
		LOGGER.info("in save user");
		User user = new User(properties);
		LOGGER.info("created user");
		String createStatement = getInsertStatement(user);
		return runCypherCommand(createStatement);
	}
	
	private String getInsertStatement(User user) {
	    String username = user.getUsername();
	    String encrypted_password = user.getEncryptedPassword();
	    String email = user.getEmail();
	    String birth_date = formatDate(user.getBirthDate());
	    String first_name = user.getFirstName();
	    String last_name = user.getLastName();
	    String phone_number = user.getPhoneNumber();

	    String create_statement = "CREATE (u:User {"
	    		+ "username: '" + username + "'"
	    		+ ", encrypted_password: '" + encrypted_password + "'"
	    		+ ", email: '" + email + "'"
	    		+ ", birth_date: '" + birth_date + "'"
	    		+ ", first_name: '" + first_name + "'"
	    		+ ", last_name: '" + last_name + "'"
	    		+ ", phone_number: '" + phone_number + "'"
	    		+ ", account_number:-1, routing_number:-1, account_balance:0})";

	    return create_statement;
	  }
	
	private String formatDate(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
		String date_string = df.format(date);
		return date_string;
	}

	@Override
	public boolean deleteUser(Properties properties) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editUser(Properties properties) {
		LOGGER.info("Editing user");
		String editStatement = getEditStatement(properties);
		LOGGER.info("Established edit statement");
		return runCypherCommand(editStatement);
	}
	
	/**
	 * Helper method to build strings in this format:<br>
	 *   <p>MATCH(s) WHERE id(s) = 25 SET s.encrypted_password = '12345890', s.last_name = 'last name',<br>
	 *  	s.email = 'karan@numis.io', s.phone_number = '1234567890', s.first_name = 'some user', <br>
	 *  	s.birth_date = '02/13/1993', s.username = 'username' RETURN s;
	 * <p> The user is selected based off of the id
	 * 
	 * @param properties - the properties to update in the selected user
	 * @return the updated edit statement which can be run from the driver. 
	 */
	private String getEditStatement(Properties properties) {
		
		String id = properties.getProperty("id");
		String update_statement = " MATCH(user) WHERE id(user) = " + id + " SET";
		String buildString = "";
		
		for (Object property : properties.keySet()) {
			if(!property.equals("id")) {
				buildString = " user." + property + " = '" + properties.getProperty((String) property) + "',";
				update_statement += buildString;
			}
		}
		update_statement = update_statement.substring(0, update_statement.length()-1); // removes trailing comma
		update_statement += " RETURN user;";
		return update_statement;
	}
	
	@Override
	public User getUser(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean runCypherCommand(String cyperStatement) {
		Session session = null;
	    try {
	    	Driver driver = DriverFactory.getInstance();
	    	LOGGER.info("got driver isntance");
	    	session = driver.session();
	    	LOGGER.info("got session");
	    	StatementResult a = session.run(cyperStatement);
	    	LOGGER.info("ran session statement" + a.toString());
	    	return true;
	    } catch (Exception e) {
	    	return false;
	    } finally {
	    	if (session != null) {
	    		session.close();
	    		LOGGER.info("session closed");
	    		DriverFactory.closeConnection();
	    	}
	    }
	}
}
