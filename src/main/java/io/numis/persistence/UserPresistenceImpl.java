package io.numis.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;

import io.numis.domain.User;

public class UserPresistenceImpl {
	
	public UserPresistenceImpl() {}
	
	public User getUser(){
		return null;
	}
	
	public boolean saveUser(Properties properties) {
		User user = new User(properties);
		Session session = null;
	    try {
	    	Driver driver = DriverFactory.getInstance();
	    	session = driver.session();
	    	session.run(getInsertStatement(user));
	    	return true;
	    } catch (Exception e) {
	    	return false;
	    } finally {
	    	if (session != null) {
	    		session.close();	
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
	    
	    String create_statement = "CREATE (n:Person {"
	    		+ "username=" + username
	    		+ ", encrypted_password=" + encrypted_password
	    		+ ", email=" + email
	    		+ ", birth_date=" + birth_date
	    		+ ", first_name=" + first_name
	    		+ ", list_name=" + last_name
	    		+ ", phone_number" + phone_number
	    		+ ", account_number=0, routing_number=0, account_balance=0})";
	    
	    return create_statement;
	  }
	
	private String formatDate(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
		String date_string = df.format(date);
		return date_string;
	}
}
