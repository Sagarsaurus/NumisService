package io.numis.domain;

import java.lang.reflect.Field;

/**
 * <h1>AbstractDomainNode</h1>
 * This AbstractDomainNode class 
 * implements DomainNode interface
 * to handle handle basic Node behavior.
 * <p>
 * 
 * @author Numis
 * @version 0.0.1
 * @since 2016-12-06
 *
 */
public abstract class AbstractDomainNode implements DomainNode {
	
	public boolean equals(Object o) {
		return false;
		
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
