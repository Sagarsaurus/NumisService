package io.numis.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
/**
 * <h1>User</h1>
 * User class extends 
 * {@link AbstractDomainNode} abstract class.
 * User model for User node in the database.
 * <p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
@NodeEntity
public class User extends AbstractDomainNode {
	
	@GraphId
	private Long id;
	private String username;
	private String encrypted_password;
	private String email;
	private Date birth_date; 
	private String first_name;
	private String last_name;
	private String phone_number;
	private int account_number;
	private int routing_number;
	private double account_balance;
	
	private final int NO_ACCOUNT_NUMBER = -1;
	
	public User(){}
	
	public User(Properties properties) {
		setUsername(properties.getProperty("username"));
		setEncryptedPassword(properties.getProperty("encrypted_password"));
		setEmail(properties.getProperty("email"));
		try {
			setBirthDate(formatBirthDate(properties.getProperty("birth_date")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setFirstName(properties.getProperty("first_name"));
		setLastName(properties.getProperty("last_name"));
		setPhoneNumber(properties.getProperty("phone_number"));
		// TODO: if accountbalance is 0 or routing number is invalid, test that. if it exists, 
		// using the getRoutingNumber, getAccountNumber and getAccountBalance methods. 
		setAccountBalance(0);
		setRoutingNumber(NO_ACCOUNT_NUMBER);
		setAccountNumber(NO_ACCOUNT_NUMBER);
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the encrypted_password
	 */
	public String getEncryptedPassword() {
		return encrypted_password;
	}


	/**
	 * @param encrypted_password the encrypted_password to set
	 */
	public void setEncryptedPassword(String encrypted_password) {
		this.encrypted_password = encrypted_password;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the birth_date
	 */
	public Date getBirthDate() {
		return birth_date;
	}


	/**
	 * @param birth_date the birth_date to set
	 */
	public void setBirthDate(Date birth_date) {
		this.birth_date = birth_date;
	}


	/**
	 * @return the first_name
	 */
	public String getFirstName() {
		return first_name;
	}


	/**
	 * @param first_name the first_name to set
	 */
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}


	/**
	 * @return the last_name
	 */
	public String getLastName() {
		return last_name;
	}


	/**
	 * @param last_name the last_name to set
	 */
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}


	/**
	 * @return the phone_number
	 */
	public String getPhoneNumber() {
		return phone_number;
	}


	/**
	 * @param phone_number the phone_number to set
	 */
	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}


	/**
	 * @return the account_number
	 */
	public int getAccountNumber() {
		return account_number;
	}


	/**
	 * @param account_number the account_number to set
	 */
	public void setAccountNumber(int account_number) {
		this.account_number = account_number;
	}


	/**
	 * @return the routing_number
	 */
	public int getRoutingNumber() {
		return routing_number;
	}


	/**
	 * @param routing_number the routing_number to set
	 */
	public void setRoutingNumber(int routing_number) {
		this.routing_number = routing_number;
	}


	/**
	 * @return the account_balance
	 */
	public double getAccountBalance() {
		return account_balance;
	}


	/**
	 * @param account_balance the account_balance to set
	 */
	public void setAccountBalance(double account_balance) {
		this.account_balance = account_balance;
	}
	
	private Date formatBirthDate(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		Date birth_date = df.parse(date);
		return birth_date;
	}
}
