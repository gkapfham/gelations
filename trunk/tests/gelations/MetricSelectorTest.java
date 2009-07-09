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
public class MetricSelectorTest {

	MetricCalculator metricCalculator;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.MetricSelector#getFitnessCalculator(int)}.
	 */
	@Test
	public void testGetFitnessCalculator() {
		
		metricCalculator = MetricSelector.getFitnessCalculator(0);
		assertEquals("MetricCalculatorCE",metricCalculator.getClass().getSimpleName());
		
	}

}
