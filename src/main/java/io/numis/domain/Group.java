package io.numis.domain;

import java.util.Properties;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * <h1>Group</h1>
 * <p>
 * User class extends 
 * {@link AbstractDomainNode} abstract class.
 * Group defined model for Group node in the database.
 * </p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
@NodeEntity
public class Group extends AbstractDomainNode {
	
	@GraphId
	private Long id;
	private double amount;
	private String purpose;
	private String description;
	private int group_type;
	
	// Set Relationships
	// Members: Set of users IN a group.
	@Relationship(direction = Relationship.INCOMING, type = "IN")
	private Set<User> members;
	
	// Contributions: Set of contributions OWNED by the group.
	@Relationship(direction = Relationship.INCOMING, type = "OWNS")
	private Set<Contribution> contributions;
	
	// TransactionHistory
	@Relationship(type = "LATEST_TRANSACTION", direction = Relationship.OUTGOING)
	private TransactionHistory transactionHistory;
	
	public Group() {}
	
	public Group(Properties properties) {
		
	}
	
	/**
	 * @return the members
	 */
	public Set<User> getMembers() {
		return members;
	}


	/**
	 * @param members the members to set
	 */
	public void setMembers(Set<User> members) {
		this.members = members;
	}


	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}


	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}


	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the group_type
	 */
	public int getGroup_type() {
		return group_type;
	}


	/**
	 * @param group_type the group_type to set
	 */
	public void setGroup_type(int group_type) {
		this.group_type = group_type;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

}
