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

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import io.numis.common.DomainNode;
import io.numis.common.GroupType;
import lombok.Data;

import java.util.Properties;

/**
* <h1>User</h1>
* 
* Group node class with respective properties.
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
public class Group extends DomainNode {

	/**
	 * Default Serial ID 
	 */
	private static final long serialVersionUID = 1L;

	// Annotated GroupDomain Properties
	@GraphId
	private Long id;
	
	@GraphId
	private String group_name;

	@Property
	private double amount;

	@Property
	private String purpose;

	@Property
	private String description;

	@Property
	private int group_type;

	// Empty Constructor
	public Group() {}

	// Properties Constructor
	public Group(Properties properties) {
		this.group_name = properties.getProperty("group_name");
		// Initial amount is 0
		this.amount = Integer.parseInt(properties.getProperty("0"));
		this.purpose = properties.getProperty("purpose");
		this.description = properties.getProperty("description");
		// Type 0 is default
		this.group_type = Integer.parseInt(properties.
				getProperty(String.valueOf(GroupType.DEFAULT.getType())));
		
	}

	/**
	 * @return id of node
	 */
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
