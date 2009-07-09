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
public class ParentSelectorTruTest {
	
	ParentSelectorTru parentSelector;
	
	Individual indi1, indi2, indi3, indi4, indi5;
	ArrayList<Individual> individuals;
	Population population;
	
	double indi1Fitness, indi2Fitness, indi3Fitness, indi4Fitness, indi5Fitness;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		parentSelector = new ParentSelectorTru();
		
		indi1Fitness = 0.1;
		indi2Fitness = 0.3;
		indi3Fitness = 0.5;
		indi4Fitness = 0.7;
		indi5Fitness = 0.9;
		
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
	 * Test method for {@link gelations.ParentSelectorTru#sortIndividuals(java.util.ArrayList)}.
	 */
	@Test
	public void testSortIndividuals() {
		
		ArrayList<Individual> unsorted = new ArrayList<Individual>();
		unsorted.add(indi3);
		unsorted.add(indi2);
		unsorted.add(indi5);
		unsorted.add(indi1);
		unsorted.add(indi4);
		
		ArrayList<Individual> sorted1 = parentSelector.sortIndividuals(unsorted);
		
		//System.out.println(sorted1.get(0).getFitness()+" "+sorted1.get(1).getFitness()+" "+sorted1.get(2).getFitness()+" "+sorted1.get(3).getFitness()+" "+sorted1.get(4).getFitness());
		///*
		assertEquals(0.9,sorted1.get(0).getFitness(), 0);
		assertEquals(0.7,sorted1.get(1).getFitness(), 0);
		assertEquals(0.5,sorted1.get(2).getFitness(), 0);
		assertEquals(0.3,sorted1.get(3).getFitness(), 0);
		assertEquals(0.1,sorted1.get(4).getFitness(), 0);
		//*/
		
		ArrayList<Individual> sorted2 = parentSelector.sortIndividuals(population.getIndividuals());
		
		
		//System.out.println(sorted2.get(0).getFitness()+" "+sorted2.get(1).getFitness()+" "+sorted2.get(2).getFitness()+" "+sorted2.get(3).getFitness()+" "+sorted2.get(4).getFitness());
		///*
		assertEquals(0.9,sorted2.get(0).getFitness(), 0);
		assertEquals(0.7,sorted2.get(1).getFitness(), 0);
		assertEquals(0.5,sorted2.get(2).getFitness(), 0);
		assertEquals(0.3,sorted2.get(3).getFitness(), 0);
		assertEquals(0.1,sorted2.get(4).getFitness(), 0);
		//*/
		
	}
	
	/**
	 * Test method for {@link gelations.ParentSelectorTru#chooseParents(gelations.Population, double)}.
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
