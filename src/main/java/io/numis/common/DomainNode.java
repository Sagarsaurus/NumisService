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
package io.numis.common;

import java.lang.reflect.Field;

/**
 * <h1>DomainNode</h1>
 * 
 * Abstract class that implements
 * {@link SNode} interface.
 * <p>
 * Extended by all primary domain node
 * classes to be serializable.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class DomainNode implements SNode {

    /**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     * @return true if object is of DomainNode
     */
    public boolean equals(Object o) {
        return this == o || !(o == null ||
                !(o instanceof SNode)) &&
                this.compareTo((SNode) o) == 0;
    }

    /**
     *
     * @param node to compare
     * @return comparison metric
     */
    private int compareTo(SNode node) {
        return this.getId().compareTo(node.getId());
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

        // return attribute/value pairs
        for (Field field : attributes) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                // access private fields
                result.append(field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            result.append(newLine);
        }
        result.append("}");
        return result.toString();
    }
}
