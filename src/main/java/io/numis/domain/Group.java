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

import io.numis.common.DomainObject;
import io.numis.common.GroupType;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Properties;
import java.util.Set;

/**
 * <h1>Group</h1>
 *
 * Group node class with respective properties.
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
public class Group extends DomainObject {

    // Annotated Group Properties
    @GraphId
    private Long id;

    @Property
    private String group_name;

    @Property
    private double amount;

    @Property
    private String purpose;

    @Property
    private String description;

    @Property(name = "type")
    private int group_type;

    // Group Relationships
//    @Relationship(type="OWNS", direction=Relationship.INCOMING)
//    private Set<Contribution> groupContributions;
//
//    // Group -> [:LATEST_TRANSACTION] -> Transaction
//    @Relationship(type="LATEST_TRANSACTION")
//    private Set<Transaction> groupTransactionLink;
//
//    // Group <- [:MEMBER_OF] <- User
//    @Relationship(type="MEMBER_OF", direction=Relationship.INCOMING)
//    private Set<User> members;


    // Properties Constructor
    public Group(Properties properties) {
        setGroup_name(properties.getProperty("group_name"));
        setAmount(Integer.parseInt(properties.getProperty("0")));
        setPurpose(properties.getProperty("purpose"));
        setDescription(properties.getProperty("description"));
        setGroup_type(Integer.parseInt(properties.
                getProperty(String.valueOf(GroupType.DEFAULT.getType()))));
    }

    /**
     * @return id - id of the node
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
     * @return group_name - name of the node
     */
    public String getGroup_name() {
        return group_name;
    }

    /**
     * @param group_name the group_name to set
     */
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    /**
     * @return amount - amount of the node
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
     * @return amount - amount of the node
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * @param purpose the amount to set
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * @return description - description of the node
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
     * @return group_type - group_type of the node
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
}
