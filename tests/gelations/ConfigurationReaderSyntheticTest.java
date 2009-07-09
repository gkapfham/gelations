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
public class ConfigurationReaderSyntheticTest {

	String coverageFileName, timeFileName, seedFileName;
	ConfigurationReader configurationReader;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		coverageFileName = "/home/conrada/Catterwampus/tests/sample_data/matrix-rsss1";
		timeFileName = "/home/conrada/Catterwampus/tests/sample_data/time-rsss1";
		seedFileName = "/home/conrada/Catterwampus/tests/sample_data/seed-rsss1";
		
		configurationReader = new ConfigurationReaderSynthetic(); 
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.ConfigurationReaderSynthetic#readCaseTests(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testReadCaseTests() {
		
		ArrayList<CaseTest> caseTests = configurationReader.readCaseTests(coverageFileName, timeFileName);
		
		assertEquals(10,caseTests.size());
		
		assertEquals(4,caseTests.get(0).getRequirements().length);
		assertEquals(0,caseTests.get(0).getRequirements()[0]);
		assertEquals(1,caseTests.get(0).getRequirements()[1]);
		assertEquals(2,caseTests.get(0).getRequirements()[2]);
		assertEquals(4,caseTests.get(0).getRequirements()[3]);
		assertEquals(4.0,caseTests.get(0).getTime(),0.1);
		
		assertEquals(5,caseTests.get(1).getRequirements().length);
		assertEquals(1,caseTests.get(1).getRequirements()[0]);
		assertEquals(2,caseTests.get(1).getRequirements()[1]);
		assertEquals(3,caseTests.get(1).getRequirements()[2]);
		assertEquals(4,caseTests.get(1).getRequirements()[3]);
		assertEquals(10,caseTests.get(1).getRequirements()[4]);
		assertEquals(3.0,caseTests.get(1).getTime(),0.1);
		
		assertEquals(4,caseTests.get(2).getRequirements().length);
		assertEquals(3,caseTests.get(2).getRequirements()[0]);
		assertEquals(5,caseTests.get(2).getRequirements()[1]);
		assertEquals(12,caseTests.get(2).getRequirements()[2]);
		assertEquals(17,caseTests.get(2).getRequirements()[3]);
		assertEquals(8.0,caseTests.get(2).getTime(),0.1);
		
		assertEquals(6,caseTests.get(3).getRequirements().length);
		assertEquals(2,caseTests.get(3).getRequirements()[0]);
		assertEquals(3,caseTests.get(3).getRequirements()[1]);
		assertEquals(4,caseTests.get(3).getRequirements()[2]);
		assertEquals(5,caseTests.get(3).getRequirements()[3]);
		assertEquals(6,caseTests.get(3).getRequirements()[4]);
		assertEquals(8,caseTests.get(3).getRequirements()[5]);
		assertEquals(5.0,caseTests.get(3).getTime(),0.1);
		
		assertEquals(5,caseTests.get(4).getRequirements().length);
		assertEquals(0,caseTests.get(4).getRequirements()[0]);
		assertEquals(1,caseTests.get(4).getRequirements()[1]);
		assertEquals(2,caseTests.get(4).getRequirements()[2]);
		assertEquals(4,caseTests.get(4).getRequirements()[3]);
		assertEquals(14,caseTests.get(4).getRequirements()[4]);
		assertEquals(7.0,caseTests.get(4).getTime(),0.1);
		
		assertEquals(5,caseTests.get(5).getRequirements().length);
		assertEquals(3,caseTests.get(5).getRequirements()[0]);
		assertEquals(4,caseTests.get(5).getRequirements()[1]);
		assertEquals(9,caseTests.get(5).getRequirements()[2]);
		assertEquals(11,caseTests.get(5).getRequirements()[3]);
		assertEquals(18,caseTests.get(5).getRequirements()[4]);
		assertEquals(10.0,caseTests.get(5).getTime(),0.1);
		
		assertEquals(3,caseTests.get(6).getRequirements().length);
		assertEquals(0,caseTests.get(6).getRequirements()[0]);
		assertEquals(2,caseTests.get(6).getRequirements()[1]);
		assertEquals(15,caseTests.get(6).getRequirements()[2]);
		assertEquals(7.0,caseTests.get(6).getTime(),0.1);
		
		assertEquals(4,caseTests.get(7).getRequirements().length);
		assertEquals(3,caseTests.get(7).getRequirements()[0]);
		assertEquals(4,caseTests.get(7).getRequirements()[1]);
		assertEquals(10,caseTests.get(7).getRequirements()[2]);
		assertEquals(16,caseTests.get(7).getRequirements()[3]);
		assertEquals(10.0,caseTests.get(7).getTime(),0.1);
		
		assertEquals(2,caseTests.get(8).getRequirements().length);
		assertEquals(2,caseTests.get(8).getRequirements()[0]);
		assertEquals(19,caseTests.get(8).getRequirements()[1]);
		assertEquals(10.0,caseTests.get(8).getTime(),0.1);
		
		assertEquals(2,caseTests.get(9).getRequirements().length);
		assertEquals(7,caseTests.get(9).getRequirements()[0]);
		assertEquals(13,caseTests.get(9).getRequirements()[1]);
		assertEquals(5.0,caseTests.get(9).getTime(),0.1);
		
	}

	/**
	 * Test method for {@link gelations.ConfigurationReaderSynthetic#readSeeds(java.lang.String)}.
	 */
	@Test
	public void testReadSeeds() {
		
		ArrayList<Integer> seeds = configurationReader.readSeeds(seedFileName);
		
		assertEquals(new Integer(490775), seeds.get(0));
		assertEquals(new Integer(619415), seeds.get(1));
		assertEquals(new Integer(616621), seeds.get(2));
		assertEquals(new Integer(104894), seeds.get(3));
		assertEquals(new Integer(102781), seeds.get(4));
		assertEquals(new Integer(855892), seeds.get(5));
		assertEquals(new Integer(648944), seeds.get(6));
		assertEquals(new Integer(991909), seeds.get(7));
		assertEquals(new Integer(770574), seeds.get(8));
		assertEquals(new Integer(472075), seeds.get(9));
		
	}

	/**
	 * Test method for {@link gelations.ConfigurationReaderSynthetic#getSetConfig(java.lang.String)}.
	 */
	@Test
	public void testGetSetConfig() {
		
		String configName = configurationReader.getSetConfig(coverageFileName);
		
		assertEquals("rsss1",configName);
		
	}

}
