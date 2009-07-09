/**
 * 
 */
package gelations;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author conrada
 *
 */
public class MutationOperatorTest {

	Random rng;
	Individual indi1, indi2, indi3, indi4, indi5, indi6, indi7;
	Population pop1, pop2, pop3;
	ArrayList<Individual> pop1Indis, pop2Indis, pop3Indis;
	MutationOperator mutationOperator;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		rng = new Random();
		
		mutationOperator = new MutationOperatorDummy(rng.nextLong());
		
		indi1 = new Individual();
		indi2 = new Individual();
		indi3 = new Individual();
		indi4 = new Individual();
		indi5 = new Individual();
		indi6 = new Individual();
		indi7 = new Individual();
		
		indi1.setFitness(0.1);
		indi2.setFitness(0.2);
		indi3.setFitness(0.3);
		indi4.setFitness(0.4);
		indi5.setFitness(0.5);
		indi6.setFitness(0.6);
		indi7.setFitness(0.7);
		
		pop1Indis = new ArrayList<Individual>();
		pop2Indis = new ArrayList<Individual>();
		pop3Indis = new ArrayList<Individual>();
		
		pop1Indis.add(indi1);
		pop1Indis.add(indi3);
		pop1Indis.add(indi2);
		pop1Indis.add(indi4);
		pop1Indis.add(indi3);
		pop1Indis.add(indi7);
		pop1Indis.add(indi6);
		
		pop2Indis.add(indi7);
		pop2Indis.add(indi6);
		pop2Indis.add(indi5);
		pop2Indis.add(indi4);
		pop2Indis.add(indi3);
		pop2Indis.add(indi2);
		pop2Indis.add(indi1);
		
		pop3Indis.add(indi1);
		pop3Indis.add(indi2);
		pop3Indis.add(indi3);
		pop3Indis.add(indi4);
		pop3Indis.add(indi5);
		pop3Indis.add(indi6);
		
		pop1 = new Population(pop1Indis);
		pop2 = new Population(pop2Indis);
		pop3 = new Population(pop3Indis);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.MutationOperator#mutatePopulation(gelations.Population, double)}.
	 */
	@Test
	public void testMutatePopulation() {
		
		Population pop1Mu, pop2Mu, pop3Mu;
		
		for (int i=0; i<20; i++) {
			
			pop1Mu = mutationOperator.mutatePopulation(pop1, 0.2);
			pop2Mu = mutationOperator.mutatePopulation(pop2, 0.5);
			pop3Mu = mutationOperator.mutatePopulation(pop3, 0.8);
			
			assertEquals(pop1.getPopSize(), pop1Mu.getPopSize());
			assertEquals(pop2.getPopSize(), pop2Mu.getPopSize());
			assertEquals(pop3.getPopSize(), pop3Mu.getPopSize());
			

			System.out.println("mutants1:");
			for (int j=0; j<pop1Mu.getPopSize(); j++) {
				
				System.out.print(pop1Mu.getIndividuals().get(j).getFitness()+", ");
				
			}
			System.out.println();

			System.out.println("mutants2:");
			for (int j=0; j<pop2Mu.getPopSize(); j++) {
				
				System.out.print(pop2Mu.getIndividuals().get(j).getFitness()+", ");
				
			}
			System.out.println();

			System.out.println("mutants3:");
			for (int j=0; j<pop3Mu.getPopSize(); j++) {
				
				System.out.print(pop3Mu.getIndividuals().get(j).getFitness()+", ");
				
			}
			System.out.println();
			
			System.out.println();
			
		}
		
	}

}
