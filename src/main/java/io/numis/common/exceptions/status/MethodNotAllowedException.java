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
package io.numis.common.exceptions.status;

/**
 * <h1>MethodNotAllowedException</h1>
 * 
 * Custom exception for HTTP status 405.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class MethodNotAllowedException extends RuntimeException {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	// Default Constructor
	public MethodNotAllowedException() {
		super();
	}
	
	// Message Constructor
	public MethodNotAllowedException(final String message) {
		super(message);
	}
	
	// throw cause
	public MethodNotAllowedException(final Throwable cause) {
		super(cause);
	}
	
	// Message, Cause
	public MethodNotAllowedException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
