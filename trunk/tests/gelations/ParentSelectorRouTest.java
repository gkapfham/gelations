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
public class ParentSelectorRouTest {
	
	ParentSelectorRou parentSelector;
	
	Individual indi1, indi2, indi3, indi4, indi5;
	ArrayList<Individual> individuals;
	Population population;
	
	double indi1Fitness, indi2Fitness, indi3Fitness, indi4Fitness, indi5Fitness;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		parentSelector = new ParentSelectorRou();
		
		indi1Fitness = 0.25;
		indi2Fitness = 0.50;
		indi3Fitness = 0.75;
		indi4Fitness = 0.99;
		indi5Fitness = 1.0;
		
		indi1 = new Individual(new ArrayList<Chromosome>(), indi1Fitness);
		indi2 = new Individual(new ArrayList<Chromosome>(), indi2Fitness);
		indi3 = new Individual(new ArrayList<Chromosome>(), indi3Fitness);
		indi4 = new Individual(new ArrayList<Chromosome>(), indi4Fitness);
		indi5 = new Individual(new ArrayList<Chromosome>(), indi5Fitness);
		
		individuals = new ArrayList<Individual>();
		individuals.add(indi1);
		individuals.add(indi2);
		individuals.add(indi3);
		individuals.add(indi4);
		individuals.add(indi5);
		
		population = new Population(individuals);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link gelations.ParentSelectorRou#computeAccumulatedFitness(java.util.ArrayList)}.
	 */
	@Test
	public void testComputeAccumulatedFitness() {
		
		double accFit025, accFit050, accFit075, accFit099, accFit100;
		
		parentSelector.computeAccumulatedFitness(population.getIndividuals());
		
		accFit025 = population.getIndividuals().get(0).getAccumulatedFitness();
		accFit050 = population.getIndividuals().get(1).getAccumulatedFitness();
		accFit075 = population.getIndividuals().get(2).getAccumulatedFitness();
		accFit099 = population.getIndividuals().get(3).getAccumulatedFitness();
		accFit100 = population.getIndividuals().get(4).getAccumulatedFitness();
		
		assertEquals(0.25, accFit025, 0.0000001);
		assertEquals(0.75, accFit050, 0.0000001);
		assertEquals(1.5, accFit075, 0.0000001);
		assertEquals(2.49, accFit099, 0.0000001);
		assertEquals(3.49, accFit100, 0.0000001);
		
	}
	
	/**
	 * Test method for {@link gelations.ParentSelectorRou#chooseParents(gelations.Population, double)}.
	 */
	@Test
	public void testChooseParentsPopulationDouble() {
		
		ArrayList<Individual> parents025, parents050, parents075, parents099, parents100;
		
		parents025 = parentSelector.chooseParents(population, 0.25);
		parents050 = parentSelector.chooseParents(population, 0.5);
		parents075 = parentSelector.chooseParents(population, 0.75);
		parents099 = parentSelector.chooseParents(population, 0.99);
		parents100 = parentSelector.chooseParents(population, 1.0);
		
		assertEquals(1, parents025.size());
		assertEquals(2, parents050.size());
		assertEquals(3, parents075.size());
		assertEquals(4, parents099.size());
		assertEquals(5, parents100.size());
		
		for (int i=0; i<parents025.size(); i++) {
			System.out.println("p025:"+parents025.get(i).getFitness());
		}
		for (int i=0; i<parents050.size(); i++) {
			System.out.println("p050:"+parents050.get(i).getFitness());
		}
		for (int i=0; i<parents075.size(); i++) {
			System.out.println("p075:"+parents075.get(i).getFitness());
		}
		for (int i=0; i<parents099.size(); i++) {
			System.out.println("p099:"+parents099.get(i).getFitness());
		}
		for (int i=0; i<parents100.size(); i++) {
			System.out.println("p100:"+parents100.get(i).getFitness());
		}
		
	}

}
