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
public class MetricCalculatorCETest {

	MetricCalculator metricCalculator;
	
	CaseTest ct1, ct2, ct3, ct4;
	Chromosome chrom1, chrom2, chrom3, chrom4;
	Individual indi1, indi2, indi3, indi4, indi5;
	ArrayList<Chromosome> chroms1, chroms2, chroms3, chroms4, chroms5;
	ArrayList<Individual> individuals;
	Population population;
	
	double ct1Time, ct2Time, ct3Time, ct4Time;
	int ct1Id, ct2Id, ct3Id, ct4Id;
	int[] ct1Reqs, ct2Reqs, ct3Reqs;
	
	double indi1Fitness, indi2Fitness, indi3Fitness, indi4Fitness, indi5Fitness;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		metricCalculator = new MetricCalculatorCE();
		
		indi1Fitness = 0.48591475179793018769;
		indi2Fitness = 0.47022326910065903225;
		indi3Fitness = 0.30371151690948645263;
		indi4Fitness = 0.47746940031203990595;
		indi5Fitness = 0.54917704968466389786;
		
		ct1Time = 1.2345;
		ct2Time = 9.8765;
		ct3Time = 3.1415;
		ct4Time = 2;
		ct1Id = 0;
		ct2Id = 1;
		ct3Id = 2;
		ct4Id = 3;
		ct1Reqs = new int[]{1,2,3};
		ct2Reqs = new int[]{4,5,6};
		ct3Reqs = new int[]{1,3,5,7};
		
		ct1 = new CaseTest(ct1Time, ct1Reqs, ct1Id);
		ct2 = new CaseTest(ct2Time, ct2Reqs, ct2Id);
		ct3 = new CaseTest(ct3Time, ct3Reqs, ct3Id);
		ct4 = new CaseTest(ct4Time, new int[0], ct4Id);
		
		chrom1 = new Chromosome(ct1);
		chrom2 = new Chromosome(ct2);
		chrom3 = new Chromosome(ct3);
		chrom4 = new Chromosome(ct4);
		
		chroms1 = new ArrayList<Chromosome>();
		chroms1.add(chrom1);
		chroms1.add(chrom2);
		chroms1.add(chrom3);
		indi1 = new Individual(chroms1);
		
		chroms2 = new ArrayList<Chromosome>();
		chroms2.add(chrom3);
		chroms2.add(chrom2);
		chroms2.add(chrom1);
		indi2 = new Individual(chroms2);
		
		chroms3 = new ArrayList<Chromosome>();
		chroms3.add(chrom2);
		chroms3.add(chrom1);
		chroms3.add(chrom4);
		chroms3.add(chrom3);
		indi3 = new Individual(chroms3);
		
		chroms4 = new ArrayList<Chromosome>();
		chroms4.add(chrom4);
		chroms4.add(chrom3);
		chroms4.add(chrom1);
		chroms4.add(chrom2);
		indi4 = new Individual(chroms4);
		
		chroms5 = new ArrayList<Chromosome>();
		chroms5.add(chrom1);
		chroms5.add(chrom2);
		chroms5.add(chrom3);
		chroms5.add(chrom4);
		indi5 = new Individual(chroms5);
		
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
	 * Test method for {@link gelations.MetricCalculatorCE#computeFitness(gelations.Individual)}.
	 */
	@Test
	public void testComputeFitnessIndividual() {
		
		System.out.println(indi1Fitness+" "+metricCalculator.computeFitness(indi1));
		System.out.println(indi2Fitness+" "+metricCalculator.computeFitness(indi2));
		System.out.println(indi3Fitness+" "+metricCalculator.computeFitness(indi3));
		System.out.println(indi4Fitness+" "+metricCalculator.computeFitness(indi4));
		System.out.println(indi5Fitness+" "+metricCalculator.computeFitness(indi5));
		assertEquals(indi1Fitness, metricCalculator.computeFitness(indi1), 0.000001);
		assertEquals(indi2Fitness, metricCalculator.computeFitness(indi2), 0.000001);
		assertEquals(indi3Fitness, metricCalculator.computeFitness(indi3), 0.000001);
		assertEquals(indi4Fitness, metricCalculator.computeFitness(indi4), 0.000001);
		assertEquals(indi5Fitness, metricCalculator.computeFitness(indi5), 0.000001);
		
	}

}
