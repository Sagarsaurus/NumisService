package io.numis.persistence;

import io.numis.domain.User;

import java.util.Properties;
import java.util.logging.Logger;

import static org.apache.http.client.utils.DateUtils.formatDate;

/**
 * Created by yuetinggg on 1/21/17.
 */
public class UserPersistenceImpl extends PersistenceImpl{
    private final static Logger LOGGER = Logger.getLogger(UserPersistenceImpl.class.getName());
    //Create user variable block
    private static final String USERNAME = "$username$";
    private static final String ENCRYPTED_PASSWORD = "$encryped_password$";
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

    @Override
    public String getInsertStatement(Object obj) {
        User user = (User) obj;
        String createUserStatement = CREATE_USER
                                        .replace(USERNAME,user.getUsername())
                                        .replace(ENCRYPTED_PASSWORD, user.getEncryptedPassword())
                                        .replace(EMAIL, user.getEmail())
                                        .replace(BIRTH_DATE,formatDate(user.getBirthDate()))
                                        .replace(FIRST_NAME, user.getFirstName())
                                        .replace(LAST_NAME, user.getLastName())
                                        .replace(PHONE_NUMBER,user.getPhoneNumber())
                                        .replace(ACCOUNT_NUMBER, user.getAccountNumber()+"")
                                        .replace(ROUTING_NUMBER, user.getRoutingNumber()+"")
                                        .replace(ACCOUNT_BALANCE, user.getAccountBalance()+"");
        return createUserStatement;
    }

    @Override
    public Object getObject(Properties properties) {
        User user = new User(properties);
        LOGGER.info("Created " + user.getUsername() + " User");
        return user;
    }
}
