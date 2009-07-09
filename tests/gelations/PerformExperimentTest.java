/**
 * 
 */
package gelations;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author conrada
 *
 */
public class PerformExperimentTest {

	String[] config1 = new String[]{"0","0","0","0","1","0.05","0.5","100","100000","0.95","8","0","/home/conrada/Catterwampus/tests/sample_data/matrix-rsss1","/home/conrada/Catterwampus/tests/sample_data/time-rsss1","/home/conrada/Catterwampus/tests/sample_data/seed-rsss1","/home/conrada/Catterwampus/tests/sample_data/results-rsss1","10"}; 
	Configuration configuration1, configuration2;
	
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
	 * Test method for {@link gelations.PerformExperiment#readConfiguration(java.lang.String)}.
	 */
	@Test
	public void testReadConfiguration() {
		
		configuration2 = PerformExperiment.readConfiguration("configs/"+configuration1.getObjectName());
		
		assertEquals(configuration1.getCaseTests().size(), configuration2.getCaseTests().size());
		for (int i=0; i<configuration1.getCaseTests().size(); i++) {
			assertEquals(configuration1.getCaseTests().get(i).getId(), configuration2.getCaseTests().get(i).getId());
			assertEquals(configuration1.getCaseTests().get(i).getTime(), configuration2.getCaseTests().get(i).getTime(), 0.001);
			assertEquals(configuration1.getCaseTests().get(i).getRequirements().length, configuration2.getCaseTests().get(i).getRequirements().length);
			for (int j=0; j<configuration1.getCaseTests().get(i).getRequirements().length; j++) {
				assertEquals(configuration1.getCaseTests().get(i).getRequirements()[j],configuration2.getCaseTests().get(i).getRequirements()[j]);
			}
		}
		assertEquals(configuration1.getChildRepresentation(), configuration2.getChildRepresentation(), 0.001);
		assertEquals(configuration1.getConfig(), configuration2.getConfig());
		assertEquals(configuration1.getCrossoverOperator(), configuration2.getCrossoverOperator());
		assertEquals(configuration1.getDatatype(), configuration2.getDatatype());
		assertEquals(configuration1.getFitnessTransform(), configuration2.getFitnessTransform());
		assertEquals(configuration1.getMaxTime(), configuration2.getMaxTime(), 0.001);
		assertEquals(configuration1.getMetric(), configuration2.getMetric());
		assertEquals(configuration1.getMutationOperator(), configuration2.getMutationOperator());
		assertEquals(configuration1.getMutationRate(), configuration2.getMutationRate(), 0.001);
		assertEquals(configuration1.getObjectName(), configuration2.getObjectName());
		assertEquals(configuration1.getOutputFileName(), configuration2.getOutputFileName());
		assertEquals(configuration1.getPopSize(), configuration2.getPopSize());
		assertEquals(configuration1.getRepetitions(), configuration2.getRepetitions());
		assertEquals(configuration1.getSeeds().size(), configuration2.getSeeds().size());
		for (int i=0; i<configuration1.getSeeds().size(); i++) {
			assertEquals(configuration1.getSeeds().get(i), configuration2.getSeeds().get(i));
		}
		assertEquals(configuration1.getSelectionOperator(), configuration2.getSelectionOperator());
		assertEquals(configuration1.getSelectionOperator(), configuration2.getSelectionOperator());
		assertEquals(configuration1.getTargetFitness(), configuration2.getTargetFitness(), 0.001);
		
	}

	/**
	 * Test method for {@link gelations.PerformExperiment#runExperiments(gelations.Configuration)}.
	 */
	@Test
	public void testRunExperiments() {
		
		PerformExperiment.runExperiments(configuration1);
		
	}

}
