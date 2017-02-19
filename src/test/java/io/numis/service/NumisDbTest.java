package io.numis.service;

// import org.junit.Before;
import org.neo4j.graphdb.GraphDatabaseService;
// import org.junit.After;

public class NumisDbTest {
	
	// Start with standard service variable.
	protected GraphDatabaseService numisDb;
	
	
}
//import org.junit.Before;
//import org.junit.After;
//import org.junit.Test;
//import org.neo4j.driver.v1.Transaction;
//import org.neo4j.graphdb.GraphDatabaseService;
//// import org.neo4j.test.TestGraphDatabaseFactory;
//import org.neo4j.graphdb.Node;
//
//import junit.framework.TestCase;
//
///**
// * Standard unit test for NumisDb Service.
// */
//public class NumisDbTest extends TestCase {
//	
//	protected GraphDatabaseService numisDb;
//	
//	/**
//	 * Create temporary database for each unit test.
//	 * (Taken from neo4j docs)
//	 */
//	// START SNIPPET: beforeTest
//	@Before
//	public void prepNumisTestDb() {
//		// TODO: Fix import. (For some reason can't import proper test libraries.)
//		// numisDb = new TestGraphDatabaseFactory().newImpermanentDatabase();
//	}
//	// END SNIPPET: beforeTest
//	
//	// START SNIPPET: afterTest
//	/**
//	 * Shutdown the database.
//	 * (Taken from neo4j docs)
//	 */
//	@After
//	public void destroyNumisTestDb() {
//		numisDb.shutdown();
//	}
//	// END SNIPPET: afterTest
//	
//	// START SNIPPET: genericUnitTest
//	/**
//	 * Test to see if a node can be created.
//	 * Apply:
//	 *   User
//	 *   Group
//	 *   TransactionHistory
//	 *   Contribution
//	 */
//    @Test
//    public void shouldCreateGenericNode() {
//    	Node n = null;
//    	try (Transaction tx = (Transaction) numisDb.beginTx()) {
//			n = numisDb.createNode();
//			// TODO: Use this generic method to test each node.
//			//       Apply all properties.
//			// n.setProperty("", arg1);
//		}
//    }
//    // END SNIPPET: genericUnitTest
//}
