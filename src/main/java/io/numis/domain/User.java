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
package io.numis.domain;

import io.numis.common.DomainNode;
import lombok.Data;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Transient;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
* <h1>User</h1>
* 
* User node class with respective properties.
* <p>
* Extends {@link DomainNode} which
* makes the domain serializable.
* <p>
*
* @author Numis
* @version 0.0.1
* @since 0.0.1
*/
@Data
@NodeEntity
public class User extends DomainNode {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	// Annotated User Properties
	@GraphId
	private Long id;

	@Property
	private String username;

	@Property
	private String encrypted_password;

	@Property
	private String email;

	@Property
	@DateString("dd/mm/yyyy")
	private Date birth_date;

	@Property
	private String first_name;

	@Property
	private String last_name;

	@Property
	private String phone_number;

	@Property
	private int account_number;

	@Property
	private int routing_number;

	@Property
	private double account_balance;

	@Transient
	private final int NO_ACCOUNT_NUMBER = -1;

	// Empty Constructor
	public User() {}

	// Properties Constructor
	public User(Properties properties) {
		this.username = properties.getProperty("username");
		this.encrypted_password = properties.getProperty("encrypted_password");
		this.email = properties.getProperty("email");
		try {
			this.birth_date = formatBirthDate(properties.getProperty("birth_date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.first_name = properties.getProperty("first_name");
		this.last_name = properties.getProperty("last_name");
		this.phone_number = properties.getProperty("phone_number");
		// TODO: if accountbalance is 0 or routing number is invalid, test that. if it exists,
		// using the getRoutingNumber, getAccountNumber and getAccountBalance methods.
		this.account_balance = Integer.parseInt(properties.getProperty("0"));
		this.account_number = Integer.parseInt(properties.getProperty(String.valueOf(NO_ACCOUNT_NUMBER)));
		this.routing_number = Integer.parseInt(properties.getProperty(String.valueOf(NO_ACCOUNT_NUMBER)));
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * Custom birthday format for User domain.
	 *
	 * @param date - date of birth
	 * @return - formatted birthday
	 * @throws ParseException -  incorrect parsing
	 */
	private Date formatBirthDate(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		return df.parse(date);
	}
}
