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

/**
 * <h1>io.numis.service</h1>
 * 
 * Application class implements {@link spark.servlet.SparkApplication}}
 * and contains primary init() methods for routing all domain nodes.
 * <p>
 * 
 * Provides the class necessary to instantiate
 * NumisService provider for numis.io.
 * <p>
 * 
 * numis.io consists of two entities:
 * The application and backend service.
 * The application is a native mobile app
 * for both iOS and Android.
 * <p>
 * 
 * The service package handles abstractions to do CRUD
 * operations on all Domain Objects. These high level
 * abstractions are called from the front-end as this is
 * the client facing REST API. All API calls created are
 * then executed by the by the persistence layer where the
 * implementation lies.
 * <p>
 *
 * @author Numis
 * @version 0.0.1
 * @since 0.0.1
 */
package io.numis.service;