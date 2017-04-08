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

import java.io.Serializable;

/**
 * <h1>SNode</h1>
 * 
 * Serializable node class that extends
 * {@link java.io.Serializable}
 * <p>
 * Used by standard domain node templates.
 * <p>
 *
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public interface INode extends Serializable {

	/**
	 *
	 * @return id - id of the node
	 */
	Long getId();

	/**
	 *
	 * @param id - being set
	 */
	void setId(final Long id);
}
