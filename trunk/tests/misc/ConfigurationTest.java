/**
 * 
 */
package unfinished;

import static org.junit.Assert.*;
import gelations.Configuration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author conrada
 *
 */
public class ConfigurationTest {

	String[] config1 = new String[]{"0","0","0","0","1","0.05","0.5","100","1000000","1.0","8","0","/home/conrada/Catterwampus/tests/sample_data/matrix-rsss1","/home/conrada/Catterwampus/tests/sample_data/time-rsss1","/home/conrada/Catterwampus/tests/sample_data/seed-rsss1","/home/conrada/Catterwampus/tests/sample_data/results-rsss1","10"}; 
	Configuration configuration1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		configuration1 = new Configuration(config1);
		configuration1.readData();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.Configuration#makeObjectName()}.
	 */
	@Test
	public void testMakeObjectName() {
		
		assertEquals("synthetic_rsss1_DM_CX_ROU_UNT_0.05_0.5_100_1000000.0_1.0_8_CE_",configuration1.makeObjectName());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getObjectName()}.
	 */
	@Test
	public void testGetObjectName() {
		
		assertEquals("synthetic_rsss1_DM_CX_ROU_UNT_0.05_0.5_100_1000000.0_1.0_8_CE_",configuration1.getObjectName());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getCaseTests()}.
	 */
	@Test
	public void testGetCaseTests() {
		
		assertEquals(10, configuration1.getCaseTests().size());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getSeeds()}.
	 */
	@Test
	public void testGetSeeds() {
		
		assertEquals(10, configuration1.getSeeds().size());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getOutputFileName()}.
	 */
	@Test
	public void testGetOutputFileName() {
		
		assertEquals("/home/conrada/Catterwampus/tests/sample_data/results-rsss1",configuration1.getOutputFileName());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getMutationOperator()}.
	 */
	@Test
	public void testGetMutationOperator() {
		
		assertEquals(0, configuration1.getMutationOperator());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getCrossoverOperator()}.
	 */
	@Test
	public void testGetCrossoverOperator() {
		
		assertEquals(0, configuration1.getCrossoverOperator());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getSelectionOperator()}.
	 */
	@Test
	public void testGetSelectionOperator() {
		
		assertEquals(0, configuration1.getSelectionOperator());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getMetric()}.
	 */
	@Test
	public void testGetMetric() {
		
		assertEquals(0, configuration1.getMetric());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getMutationRate()}.
	 */
	@Test
	public void testGetMutationRate() {
		
		assertEquals(0.05, configuration1.getMutationRate(), 0.001);
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getTargetFitness()}.
	 */
	@Test
	public void testGetTargetFitness() {
		
		assertEquals(1.0, configuration1.getTargetFitness(), 0.001);
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getMaxTime()}.
	 */
	@Test
	public void testGetMaxTime() {
		
		assertEquals(1000000.0, configuration1.getMaxTime(), 0.001);
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getRepetitions()}.
	 */
	@Test
	public void testGetRepetitions() {
		
		assertEquals(10, configuration1.getRepetitions());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getPopSize()}.
	 */
	@Test
	public void testGetPopSize() {
		
		assertEquals(100, configuration1.getPopSize());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getChildRepresentation()}.
	 */
	@Test
	public void testGetChildRepresentation() {
		
		assertEquals(0.5, configuration1.getChildRepresentation(), 0.001);
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getConfig()}.
	 */
	@Test
	public void testGetConfig() {
		
		assertEquals("rsss1", configuration1.getConfig());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getDatatype()}.
	 */
	@Test
	public void testGetDatatype() {
		
		assertEquals(0, configuration1.getDatatype());
		
	}

	/**
	 * Test method for {@link gelations.Configuration#getFitnessTransform()}.
	 */
	@Test
	public void testGetFitnessTransform() {
		
		assertEquals(1, configuration1.getFitnessTransform());
		
	}

}
