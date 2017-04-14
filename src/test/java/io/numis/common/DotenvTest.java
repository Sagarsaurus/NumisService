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

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.numis.common.environment.Dotenv;
import io.numis.common.environment.NumisDotenv;
import io.numis.common.exceptions.DotenvException;

/**
* <h1>DotenvTest</h1>
* 
* Testing case for Dotenv Factory.
* <p>
* 
* Note: Not testing username, password,
* and teal_bolt_password to preserve
* identity and for security purposes.
* <p>
*
* @author Numis
* @version 0.0.1
* @since 0.0.1
*/
public class DotenvTest {
	private static Dotenv dotenvInstance;
	
	/**
	 * Initialize Dotenv dotenvInstance before
	 * testing.
	 */
	@BeforeClass
	public static void initialize() {
		dotenvInstance = NumisDotenv.createDotenvInstance();
	}
	
	/**
	 * Assert if teal_url value retrieved
	 * and actual match.
	 * Note: {@link Assert.assertEquals} is deprecated
	 * so it is better to use Assert.assertThat.
	 * 
	 * @throws DotenvException - Variables did not match
	 */
	@Test
	public void test_teal_url_integrity() throws DotenvException {
		String teal_url = "http://app58740712-RTZpup:om38CrfHWN2B81HzpY3s"
				+ "@hobby-cicfalafjildgbkecenjfjol.dbs.graphenedb.com:24789";
		String retrievedVar = NumisDotenv.retrieveTealURL(dotenvInstance);
		assertThat(retrievedVar, CoreMatchers.containsString(teal_url));
	}
	
	/**
	 * Assert if teal_bolt_url value retrieved
	 * and actual match.
	 * Note: {@link Assert.assertEquals} is deprecated
	 * so it is better to use Assert.assertThat.
	 * 
	 * @throws DotenvException - Variables did not match
	 */
	@Test
	public void test_teal_bolt_url_integrity() throws DotenvException {
		String teal_bolt_url = "bolt://hobby-cicfalafjildgbkecenjfjol.dbs.graphenedb.com:24786";
		String retrievedVar = NumisDotenv.retrieveTealBoltURL(dotenvInstance);
		assertThat(retrievedVar, CoreMatchers.containsString(teal_bolt_url));
	}
	
	/**
	 * Assert if teal_bolt_user value retrieved
	 * and actual match.
	 * Note: {@link Assert.assertEquals} is deprecated
	 * so it is better to use Assert.assertThat.
	 * 
	 * @throws DotenvException - Variables did not match
	 */
	@Test
	public void test_teal_bolt_user_integrity() throws DotenvException {
		String teal_bolt_user = "app58740712-RTZpup";
		String retrievedVar = NumisDotenv.retrieveTealBoltUser(dotenvInstance);
		assertThat(retrievedVar, CoreMatchers.containsString(teal_bolt_user));
	}
	
	/**
	 * Assert if driver value retrieved
	 * and actual match.
	 * Note: {@link Assert.assertEquals} is deprecated
	 * so it is better to use Assert.assertThat.
	 * 
	 * @throws DotenvException - Variables did not match
	 */
	@Test
	public void test_driver_integrity() throws DotenvException {
		String driver = "org.neo4j.ogm.drivers.bolt.driver.BoltDriver";
		String retrievedVar = NumisDotenv.retrieveDriverName(dotenvInstance);
		assertThat(retrievedVar, CoreMatchers.containsString(driver));
	}
	
	/**
	 * Assert if URI value retrieved
	 * and actual match.
	 * Note: {@link Assert.assertEquals} is deprecated
	 * so it is better to use Assert.assertThat.
	 * 
	 * @throws DotenvException - Variables did not match
	 */
	@Test
	public void uri_integrity() throws DotenvException {
		String URI = "bolt://hobby-cicfalafjildgbkecenjfjol.dbs.graphenedb.com:24786";
		String retrievedVar = NumisDotenv.retrieveURI(dotenvInstance);
		assertThat(retrievedVar, CoreMatchers.containsString(URI));
	}
	
	/**
	 * Release dotenvInstance
	 */
	@AfterClass
	public static void setNull() {
		dotenvInstance = null;
	}
}
