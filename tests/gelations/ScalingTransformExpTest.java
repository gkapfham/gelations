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
public class ScalingTransformExpTest {

	ScalingTransformExp transformer;
	Individual indi;
	Individual indi010, indi001, indi050, indi025, indi075, indi098, indi099, indi100, 
		indi250;
	long seed = 1234567890;
	double fitness010, fitness001, fitness050, fitness025, fitness075, fitness098,
		fitness099, fitness100, fitness250;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		transformer = new ScalingTransformExp(seed);
		indi001 = new Individual(new ArrayList<Chromosome>(), 0.01);
		indi010 = new Individual(new ArrayList<Chromosome>(), 0.10);
		indi050 = new Individual(new ArrayList<Chromosome>(), 0.50);
		indi025 = new Individual(new ArrayList<Chromosome>(), 0.25);
		indi075 = new Individual(new ArrayList<Chromosome>(), 0.75);
		indi098 = new Individual(new ArrayList<Chromosome>(), 0.98);
		indi099 = new Individual(new ArrayList<Chromosome>(), 0.99);
		indi100 = new Individual(new ArrayList<Chromosome>(), 1.00);
		indi250 = new Individual(new ArrayList<Chromosome>(), 2.50);
		
		fitness001 = 0.141421356;
		fitness010 = 0.331662479;
		fitness025 = 0.509901951;
		fitness050 = 0.714142843;
		fitness075 = 0.871779789;
		fitness098 = 0.994987437;
		fitness099 = 1;
		fitness100 = 1.004987562;
		fitness250 = 1.584297952;
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.ScalingTransformExp#transformIndividual(gelations.Individual)}.
	 */
	@Test
	public void testTransformIndividual() {
		
		assertEquals(transformer.transformIndividual(indi001).getFitness(),fitness001, 0.001);
		assertEquals(transformer.transformIndividual(indi010).getFitness(),fitness010, 0.001);
		assertEquals(transformer.transformIndividual(indi025).getFitness(),fitness025, 0.001);
		assertEquals(transformer.transformIndividual(indi050).getFitness(),fitness050, 0.001);
		assertEquals(transformer.transformIndividual(indi075).getFitness(),fitness075, 0.001);
		assertEquals(transformer.transformIndividual(indi098).getFitness(),fitness098, 0.001);
		assertEquals(transformer.transformIndividual(indi099).getFitness(),fitness099, 0.001);
		assertEquals(transformer.transformIndividual(indi100).getFitness(),fitness100, 0.001);
		assertEquals(transformer.transformIndividual(indi250).getFitness(),fitness250, 0.001);
		
		assertEquals(transformer.transformIndividual(indi001),indi001);		
		assertEquals(transformer.transformIndividual(indi250),indi250);
		
	}

}
