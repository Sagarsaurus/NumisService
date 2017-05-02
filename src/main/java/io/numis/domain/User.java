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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import io.numis.common.DomainObject;

/**
 * <h1>User</h1>
 *
 * User node class with respective properties.
 * <p>
 * Extends {@link DomainObject} containing
 * the base methods for comparison and debugging.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
@NodeEntity
public class User extends DomainObject {

    // Annotated User Properties
    @GraphId
    private Long id;

    @Property
    private String username;

    @Property(name="password")
    private String encrypted_password;

    @Property
    private String email;

    @Property(name="birthday")
    @DateString("dd/mm/yyyy")
    private Date birth_date;

    @Property(name="name")
    private String first_name;

    @Property(name="surname")
    private String last_name;

    @Property
    private String phone_number;

    @Property
    private int account_number;

    @Property
    private int routing_number;

    @Property
    private double account_balance;

    // User Relationships

    // User -> [:IS_FRIENDS_WITH] -> User
//    @Relationship(type="IS_FRIENDS_WITH", direction=Relationship.UNDIRECTED)
//    private Set<User> friends;
//
//    // User -> [:MEMBER_OF] -> Group
//    @Relationship(type="MEMBER_OF")
//    private Set<Group> groups;
//
//    // User -> [:HAS_CONTRIBUTION] -> Contribution
//    @Relationship(type="HAS_CONTRIBUTION")
//    private Set<Contribution> contributions;


    // Properties Constructor
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
        setAccountBalance(Integer.parseInt(properties.getProperty("0")));
        int NO_ACCOUNT_NUMBER = -1;
        setRoutingNumber(Integer.parseInt(properties.getProperty(String.valueOf(NO_ACCOUNT_NUMBER))));
        setAccountNumber(Integer.parseInt(properties.getProperty(String.valueOf(NO_ACCOUNT_NUMBER))));
    }

    /**
     * @return User id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id - User id to set
     */
    public void setId(final Long id) {
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

    /**
     * Custom birthday format for User domain.
     *
     * @param date - date of birth
     * @return - formatted birthday
     * @throws ParseException -  incorrect parsing
     */
    public Date formatBirthDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        return df.parse(date);
    }
}
