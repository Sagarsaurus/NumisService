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

	import java.util.Properties;

	/**
	* <h1>GroupDomain</h1>
	* 
	* This class represents the GroupDomain
	* node structure and properties of the
	* graph database. extends {@link DomainNode}
	* <p>
	*
	* @author Numis
	* @version 0.0.1
	* @since 0.0.1
	*/
	@NodeEntity
	public class Group extends DomainNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1072857842493938227L;

	// Annotated GroupDomain Properties
	@GraphId
	private Long id;

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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGroup_type() {
		return group_type;
	}

	public void setGroup_type(int group_type) {
		this.group_type = group_type;
	}
}
