package io.numis.domain.interfaces;

/**
 * <h1>DomainNode</h1>
 * <p>
 * DomainNode class extends 
 * {@link Comparable} interface.
 * DomainNode is used to abstract all Graph node representations
 * based simply on id.
 * </p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public interface DomainNode extends Comparable<DomainNode> {
	
	public Long getId();
}
