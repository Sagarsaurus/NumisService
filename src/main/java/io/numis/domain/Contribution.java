package io.numis.domain;

import org.neo4j.ogm.annotation.GraphId;

public class Contribution extends AbstractDomainNode {

	@GraphId
	private Long id;
	
	@Override
	public Long getId() {
		return id;
	}

}
