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
package io.numis.domain;

import io.numis.common.DomainObject;

/**
 * <h1>Transaction</h1>
 *
 * Transaction node class with respective properties.
 * <p>
 * Extends {@link DomainObject} containing
 * the base methods for comparison and debugging.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
public class Transaction extends DomainObject {
    /**
     * @return id - id of the node
     */
    @Override
    public Long getId() {
        return null;
    }
}
