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
import io.numis.persistence.interfaces.UserPersistence;

public class UserPresistenceImpl implements UserPersistence {
	
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
		Session session = null;
		LOGGER.info("session is null");
	    try {
	    	Driver driver = DriverFactory.getInstance();
	    	LOGGER.info("got driver isntance");
	    	session = driver.session();
	    	LOGGER.info("got session");
	    	StatementResult a = session.run(getInsertStatement(user));
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}
}
