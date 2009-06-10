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
public class MutationOperatorISMTest {

	Random rng;
	MutationOperator mutationOperator;
	
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
		
		mutationOperator = new MutationOperatorISM(rng.nextLong());
		
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
	 * Test method for {@link gelations.MutationOperatorISM#mutateIndividual(gelations.Individual)}.
	 */
	@Test
	public void testMutateIndividual() {
		
		Individual indi1Mu, indi2Mu, indi3Mu, indi4Mu;
		ArrayList<Individual> indisMu;
		ArrayList<Individual> indis;
		HashMap<Character,Boolean> chars;
		
		for (int i=0; i<20; i++) {
			
			indis = new ArrayList<Individual>();
			indisMu = new ArrayList<Individual>();
			
			indi1Mu = mutationOperator.mutateIndividual(indi1);
			indi2Mu = mutationOperator.mutateIndividual(indi2);
			indi3Mu = mutationOperator.mutateIndividual(indi3);
			indi4Mu = mutationOperator.mutateIndividual(indi4);
			
			indisMu.add(indi1Mu);
			indisMu.add(indi2Mu);
			indisMu.add(indi3Mu);
			indisMu.add(indi4Mu);
			
			for (int j = 0; j<indis.size(); j++) {
				
				System.out.println(j+": "+indis.get(j).getStringRepresentation()+" "+indisMu.get(j).getStringRepresentation());
				System.out.println();
				
				chars = new HashMap<Character,Boolean>();
				
				assertEquals(indisMu.get(j).size(),indis.get(j).size());
				
				chars.clear();
				for(int k=0; k<indisMu.get(j).getStringRepresentation().length(); k+=2) {
					
					if(chars.containsKey(indisMu.get(j).getStringRepresentation().charAt(k))) {
						
						fail("Chromosome "+indisMu.get(j).getStringRepresentation().charAt(k)+" is duplicated!");
						
					} else {
						
						chars.put(indisMu.get(j).getStringRepresentation().charAt(k), true);
						
					}
				}
			}
		}
	}
}