/**
 * Provides the class necessary to instantiate
 * NumisService provider for numis.io.
 * <p>
 * numis.io consists of two entities:
 * The application and backend service.
 * The application is a native mobile app
 * for both iOS and Android.
 * </p>
 * <p>
 * The service package handles abstractions to do CRUD
 * operations on all Domain Objects. These high level
 * abstractions are called from the front-end as this is
 * the client facing REST API. All API calls created are
 * then executed by the by the persistence layer where the
 * implementation lies.
 * </p>
 * <p>
 * All one needs to do for the service class is call the correct
 * implementation in the persistence layer and tie the correct 
 * REST API call to the correct method in the service class. 
 * </p>
 * @since 0.0.1
 */
package io.numis.service;