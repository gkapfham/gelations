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
public class ScalingTransformTest {

	ScalingTransform transformer;
	Individual indi;
	Individual indi010, indi050, indi025, indi075, indi098, indi099, indi100, 
		indi250;
	
	long seed = 1234567890;
	double fitness010, fitness050, fitness025, fitness075, fitness098,
		fitness099, fitness100, fitness250;
	
	ArrayList<Individual> individuals;
	Population population;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		transformer = new ScalingTransformUn(seed);
		
		fitness010 = 0.10;
		fitness025 = 0.25;
		fitness050 = 0.50;
		fitness075 = 0.75;
		fitness098 = 0.98;
		fitness099 = 0.99;
		fitness100 = 1.0;
		fitness250 = 2.50;
		
		indi010 = new Individual(new ArrayList<Chromosome>(), fitness010);
		indi050 = new Individual(new ArrayList<Chromosome>(), fitness050);
		indi025 = new Individual(new ArrayList<Chromosome>(), fitness025);
		indi075 = new Individual(new ArrayList<Chromosome>(), fitness075);
		indi098 = new Individual(new ArrayList<Chromosome>(), fitness098);
		indi099 = new Individual(new ArrayList<Chromosome>(), fitness099);
		indi100 = new Individual(new ArrayList<Chromosome>(), fitness100);
		indi250 = new Individual(new ArrayList<Chromosome>(), fitness250);
		
		individuals = new ArrayList<Individual>();
		
		individuals.add(indi010);
		individuals.add(indi025);
		individuals.add(indi050);
		individuals.add(indi075);
		individuals.add(indi098);
		individuals.add(indi099);
		individuals.add(indi100);
		individuals.add(indi250);
		
		population = new Population(individuals);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.ScalingTransform#transformPopulation(gelations.Population)}.
	 */
	@Test
	public void testTransformPopulation() {
		
		int initSize = population.getPopSize();
		
		population = transformer.transformPopulation(population);
		
		assertEquals(initSize, population.getPopSize());
		
		assertEquals(fitness010, population.getIndividuals().get(0).getFitness(), 0);
		assertEquals(fitness025, population.getIndividuals().get(1).getFitness(), 0);
		assertEquals(fitness050, population.getIndividuals().get(2).getFitness(), 0);
		assertEquals(fitness075, population.getIndividuals().get(3).getFitness(), 0);
		assertEquals(fitness098, population.getIndividuals().get(4).getFitness(), 0);
		assertEquals(fitness099, population.getIndividuals().get(5).getFitness(), 0);
		assertEquals(fitness100, population.getIndividuals().get(6).getFitness(), 0);
		assertEquals(fitness250, population.getIndividuals().get(7).getFitness(), 0);
		
	}

}
