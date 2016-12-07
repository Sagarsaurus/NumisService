package io.numis.domain;

/**
 * <h1>DomainNode</h1>
 * DomainNode class extends 
 * {@link Comparable} interface.
 * DomainNode is used to abstract all DomainNodes.
 * <p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 2016-12-06
 *
 */
public interface DomainNode extends Comparable<DomainNode> {
	
	public int getId();
}
