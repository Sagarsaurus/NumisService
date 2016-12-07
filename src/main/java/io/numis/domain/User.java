package io.numis.domain;

import java.util.Date;

public class User extends AbstractDomainNode {
	
	private int id;
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
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
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
	
	
	
	
}
