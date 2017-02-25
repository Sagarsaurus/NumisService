package io.numis.domain;

import java.lang.reflect.Field;

import io.numis.domain.interfaces.DomainNode;

/**
 * <h1>AbstractDomainNode</h1>
 * <p>
 * AbstractDomainNode class 
 * implements {@link DomainNode} interface
 * to handle basic Node behavior.
 * </p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 2016-12-06
 *
 */
public abstract class AbstractDomainNode implements DomainNode {
	
	/**
	 * @return boolean
	 */
	public boolean equals(Object o)	{
		if (this == o)		{
			return true;
		} else if (o == null || !(o instanceof DomainNode)) {
			return false;
		} else {
			return this.compareTo((DomainNode)o)==0;
		}
	}
	
	/**
	 * @return int
	 */
	public int compareTo(DomainNode o) {
		return ((Long)this.getId()).compareTo((Long)(o.getId()));
	}
	
	/**
	 * @return result
	 */
	public int hashCode(DomainNode o) {
		final int prime = 17;
		int result = 1;
		result = (int) (prime * result + ((this.getId() == null) ? 0 : o.getId()));
		return result;
	}
	
	/**
     * Used for debugging returning in this format:
     * 
     * Var {
     *   varName: value
     *   ...
     * }
     * <p>
     * 
     * @return result aggregated list of values of the class
     */
	public String toString() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		
		result.append(this.getClass().getName());
		result.append(" {");
		result.append(newLine);
		
		// Display class attributes of (this) class
		Field[] attributes = this.getClass().getDeclaredFields();
		
		// return attributes/value pairs
		for (Field field : attributes) {
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				// access private fields
				result.append(field.get(this));
			} catch (IllegalAccessException e) {
				System.out.println(e);
			}
			result.append(newLine);
		}
		result.append("}");
		
		return result.toString();
	}
}
