/**
 * 
 */
package gelations;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author conrada
 *
 */
public class ConfigurationReaderRealTest {
	
	String coverageFileName, timeFileName, seedFileName;
	ConfigurationReader configurationReader;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		coverageFileName = "/home/conrada/Catterwampus/tests/sample_data/SACoverage.dat";
		timeFileName = "/home/conrada/Catterwampus/tests/sample_data/SATime.dat";
		seedFileName = "/home/conrada/Catterwampus/tests/sample_data/SASeed.dat";
		
		configurationReader = new ConfigurationReaderReal(); 
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.ConfigurationReaderReal#readCaseTests(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testReadCaseTests() {
		
		ArrayList<CaseTest> caseTests = configurationReader.readCaseTests(coverageFileName, timeFileName);
		
		assertEquals(7,caseTests.size());
		
		assertEquals(3,caseTests.get(0).getRequirements().length);
		assertEquals(1,caseTests.get(0).getRequirements()[0]);
		assertEquals(2,caseTests.get(0).getRequirements()[1]);
		assertEquals(3,caseTests.get(0).getRequirements()[2]);
		assertEquals(1.0,caseTests.get(0).getTime(),0.1);

		assertEquals(0,caseTests.get(1).getRequirements().length);
		assertEquals(12.0,caseTests.get(1).getTime(),0.1);

		assertEquals(1,caseTests.get(2).getRequirements().length);
		assertEquals(4,caseTests.get(2).getRequirements()[0]);
		assertEquals(10.0,caseTests.get(2).getTime(),0.1);

		assertEquals(3,caseTests.get(3).getRequirements().length);
		assertEquals(2,caseTests.get(3).getRequirements()[0]);
		assertEquals(3,caseTests.get(3).getRequirements()[1]);
		assertEquals(5,caseTests.get(3).getRequirements()[2]);
		assertEquals(500.0,caseTests.get(3).getTime(),0.1);
		
		assertEquals(1,caseTests.get(4).getRequirements().length);
		assertEquals(6,caseTests.get(4).getRequirements()[0]);
		assertEquals(7.0,caseTests.get(4).getTime(),0.1);

		assertEquals(0,caseTests.get(5).getRequirements().length);
		assertEquals(2.0,caseTests.get(5).getTime(),0.1);
		

		assertEquals(1,caseTests.get(6).getRequirements().length);
		assertEquals(7,caseTests.get(6).getRequirements()[0]);
		assertEquals(3.0,caseTests.get(6).getTime(),0.1);		
		
	}

	/**
	 * Test method for {@link gelations.ConfigurationReaderReal#readSeeds(java.lang.String)}.
	 */
	@Test
	public void testReadSeeds() {
		
		ArrayList<Integer> seeds = configurationReader.readSeeds(seedFileName);
		
		assertEquals(4,seeds.size());
		
		assertEquals(new Integer(876543),seeds.get(0));
		assertEquals(new Integer(210987),seeds.get(1));
		assertEquals(new Integer(135791),seeds.get(2));
		assertEquals(new Integer(246802),seeds.get(3));
		
	}

	/**
	 * Test method for {@link gelations.ConfigurationReaderReal#getSetConfig(java.lang.String)}.
	 */
	@Test
	public void testGetSetConfig() {
		
		assertEquals("SA",configurationReader.getSetConfig(coverageFileName));
		
	}

}
