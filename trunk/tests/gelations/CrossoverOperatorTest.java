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
public class CrossoverOperatorTest {

	Random rng;
	Individual indi1, indi2, indi3, indi4, indi5, indi6, indi7;
	Population pop1, pop2, pop3;
	ArrayList<Individual> pop1Indis, pop2Indis, pop3Indis;
	CrossoverOperator crossoverOperator;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		rng = new Random();
		
		crossoverOperator = new CrossoverOperatorDummy(rng.nextLong());
		
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
	 * Test method for {@link gelations.CrossoverOperator#createChildren(java.util.ArrayList)}.
	 */
	@Test
	public void testCreateChildren() {
		
		ArrayList<Individual> children1, children2, children3;
		
		children1 = new ArrayList<Individual>();
		children2 = new ArrayList<Individual>();
		children3 = new ArrayList<Individual>();
		
		for (int i=0; i<20; i++) {
			
			children1 = crossoverOperator.createChildren(pop1Indis);
			children2 = crossoverOperator.createChildren(pop2Indis);
			children3 = crossoverOperator.createChildren(pop3Indis);
			
			assertEquals(pop1Indis.size()+1, children1.size());
			assertEquals(pop2Indis.size()+1, children2.size());
			assertEquals(pop3Indis.size(), children3.size());
			
			System.out.println("children1:");
			for (int j=0; j<children1.size(); j++) {
				
				System.out.print(children1.get(j).getFitness()+", ");
				
			}
			System.out.println();

			System.out.println("children2:");
			for (int j=0; j<children2.size(); j++) {
				
				System.out.print(children2.get(j).getFitness()+", ");
				
			}
			System.out.println();

			System.out.println("children3:");
			for (int j=0; j<children3.size(); j++) {
				
				System.out.print(children3.get(j).getFitness()+", ");
				
			}
			System.out.println();
			
		}
		
	}

}
