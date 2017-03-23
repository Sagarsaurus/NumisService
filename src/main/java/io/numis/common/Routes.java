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
 * <h1>Routes</h1>
 * 
 * File to hold all the REST api
 * routes for each node.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public enum Routes {
    // User URI
    GetUser("/api/v1/users/get"),
    CreateUser("/api/v1/users/new"),
    UpdateUser("/api/v1");
    // User URI

    private String URI;

    Routes(String URI) {
        this.URI = URI;
    }

    public String URI() {
        return URI;
    }
}
