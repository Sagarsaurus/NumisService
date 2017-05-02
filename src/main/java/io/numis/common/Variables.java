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

/**
 * <h1>Variables</h1>
 *
 * List of all key values in the .env file
 * to reference to throughout the service.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public enum Variables {
    // Variable Definitions
    GRAPHENEDB_TEAL_URL("GRAPHENEDB_TEAL_URL"),
    GRAPHENEDB_TEAL_BOLT_USER("GRAPHENEDB_TEAL_BOLT_USER"),
    GRAPHENEDB_TEAL_BOLT_URL("GRAPHENEDB_TEAL_BOLT_URL"),
    GRAPHENEDB_TEAL_BOLT_PASSWORD("GRAPHENEDB_TEAL_BOLT_PASSWORD"),
    USERNAME("username"),
    PASSWORD("password"),
    DRIVER("driver"),
    URI("URI");

    private String value;

    Variables(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
