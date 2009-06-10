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
import java.util.HashMap;

/**
 * @author conrada
 *
 */
public class CrossoverOperatorPOSTest {

	Random rng;
	CrossoverOperator crossoverOperator;
	
	CaseTest ct1, ct2, ct3, ct4, ct5, ct6;
	Chromosome chrom1, chrom2, chrom3, chrom4, chrom5, chrom6;
	Individual indi1, indi2, indi3, indi4;
	ArrayList<Chromosome> chroms1, chroms2, chroms3, chroms4;
	ArrayList<Individual> individuals;
	Population population;
	
	double ct1Time, ct2Time, ct3Time, ct4Time, ct5Time, ct6Time;
	int ct1Id, ct2Id, ct3Id, ct4Id, ct5Id, ct6Id;
	int[] ct1Reqs, ct2Reqs, ct3Reqs, ct4Reqs, ct5Reqs, ct6Reqs;
	
	double indi1Fitness, indi2Fitness, indi3Fitness, indi4Fitness, indi5Fitness, indi6Fitness;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		rng = new Random();
		
		crossoverOperator = new CrossoverOperatorPOS(rng.nextLong());
		
		ct1Time = 1.2345;
		ct2Time = 9.8765;
		ct3Time = 3.1415;
		ct4Time = 4.1;
		ct5Time = 5.2;
		ct6Time = 6.3;
		
		ct1Id = 0;
		ct2Id = 1;
		ct3Id = 2;
		ct4Id = 3;
		ct5Id = 4;
		ct6Id = 5;
		
		ct1Reqs = new int[]{1,2,3};
		ct2Reqs = new int[]{4,5,6};
		ct3Reqs = new int[]{1,3,5,7};
		ct4Reqs = new int[]{7,8};
		ct5Reqs = new int[]{1,3,5,9};
		ct6Reqs = new int[]{2,4,6,8};
		
		indi1Fitness = 0.9876;
		indi2Fitness = 0.5;
		indi3Fitness = 0.4321;
		indi4Fitness = 0.8;
		indi5Fitness = 0.6;
		indi6Fitness = 0.2;
		
		ct1 = new CaseTest(ct1Time, ct1Reqs, ct1Id);
		ct2 = new CaseTest(ct2Time, ct2Reqs, ct2Id);
		ct3 = new CaseTest(ct3Time, ct3Reqs, ct3Id);
		ct4 = new CaseTest(ct4Time, ct4Reqs, ct4Id);
		ct5 = new CaseTest(ct5Time, ct5Reqs, ct5Id);
		ct6 = new CaseTest(ct6Time, ct6Reqs, ct6Id);
		
		chrom1 = new Chromosome(ct1);
		chrom2 = new Chromosome(ct2);
		chrom3 = new Chromosome(ct3);
		chrom4 = new Chromosome(ct4);
		chrom5 = new Chromosome(ct5);
		chrom6 = new Chromosome(ct6);
		
		chroms1 = new ArrayList<Chromosome>();
		chroms1.add(chrom1);
		chroms1.add(chrom2);
		chroms1.add(chrom3);
		chroms1.add(chrom4);
		chroms1.add(chrom5);
		chroms1.add(chrom6);
		indi1 = new Individual(chroms1, indi1Fitness);
		
		chroms2 = new ArrayList<Chromosome>();
		chroms2.add(chrom6);
		chroms2.add(chrom5);
		chroms2.add(chrom4);
		chroms2.add(chrom3);
		chroms2.add(chrom2);
		chroms2.add(chrom1);
		indi2 = new Individual(chroms2, indi2Fitness);
		
		chroms3 = new ArrayList<Chromosome>();
		chroms3.add(chrom2);
		chroms3.add(chrom4);
		chroms3.add(chrom6);
		chroms3.add(chrom1);
		chroms3.add(chrom3);
		chroms3.add(chrom5);
		indi3 = new Individual(chroms3, indi3Fitness);
		
		chroms4 = new ArrayList<Chromosome>();
		chroms4.add(chrom1);
		chroms4.add(chrom3);
		chroms4.add(chrom5);
		chroms4.add(chrom2);
		chroms4.add(chrom4);
		chroms4.add(chrom6);
		indi4 = new Individual(chroms4, indi4Fitness);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.CrossoverOperatorPOS#performCrossover(gelations.Individual, gelations.Individual)}.
	 */
	@Test
	public void testPerformCrossover() {
		
		int indi1Int, indi2Int;
		Individual individual1, individual2;
		ArrayList<Individual> children;
		
		for (int i=0; i<20; i++) {
			
			indi1Int = rng.nextInt(4);
			indi2Int = rng.nextInt(4);
			
			switch (indi1Int) {
			case 0: individual1 = indi1; break;
			case 1: individual1 = indi2; break;
			case 2: individual1 = indi3; break;
			case 3: individual1 = indi4; break;
			default: individual1 = indi1; break;
			}
			
			switch (indi2Int) {
			case 0: individual2 = indi1; break;
			case 1: individual2 = indi2; break;
			case 2: individual2 = indi3; break;
			case 3: individual2 = indi4; break;
			default: individual2 = indi2; break;
			}
			
			System.out.println("p1:"+individual1.getFitness()+":"+individual1.getStringRepresentation()+" p2:"+individual2.getFitness()+":"+individual2.getStringRepresentation());
			children = crossoverOperator.performCrossover(individual1, individual2);
			System.out.println("p1:"+individual1.getFitness()+":"+individual1.getStringRepresentation()+" p2:"+individual2.getFitness()+":"+individual2.getStringRepresentation()+" c1:"+children.get(0).getStringRepresentation()+" c2:"+children.get(1).getStringRepresentation());
			System.out.println();

			HashMap<Character,Boolean> chars = new HashMap<Character,Boolean>();
			
			assertEquals(individual1.size(),children.get(0).size());
			assertEquals(individual1.size(),children.get(1).size());
			
			chars.clear();
			for(int j=0; j<children.get(0).getStringRepresentation().length(); j+=2) {
				
				if(chars.containsKey(children.get(0).getStringRepresentation().charAt(j))) {
					
					fail("Chromosome "+children.get(0).getStringRepresentation().charAt(j)+" is duplicated!");
					
				} else {
					
					chars.put(children.get(0).getStringRepresentation().charAt(j), true);
					
				}
				
			}
			
			chars.clear();
			for(int j=0; j<children.get(1).getStringRepresentation().length(); j+=2) {
				
				if(chars.containsKey(children.get(1).getStringRepresentation().charAt(j))) {
					
					fail("Chromosome is duplicated!");
					
				} else {
					
					chars.put(children.get(1).getStringRepresentation().charAt(j), true);
					
				}
				
			}
			
		}
		
	}

}