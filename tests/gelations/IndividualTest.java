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
public class IndividualTest {

	CaseTest ct1, ct2, ct3;
	Chromosome chrom1, chrom2, chrom3;
	Individual indi1, indi2, indi3, indi4;
	ArrayList<Chromosome> chroms1, chroms2, chroms3;
	
	double ct1Time, ct2Time, ct3Time;
	int ct1Id, ct2Id, ct3Id;
	int[] ct1Reqs, ct2Reqs, ct3Reqs;
	
	double indi1Fitness;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		ct1Time = 1.2345;
		ct2Time = 9.8765;
		ct3Time = 3.1415;
		ct1Id = 0;
		ct2Id = 1;
		ct3Id = 2;
		ct1Reqs = new int[]{1,2,3};
		ct2Reqs = new int[]{4,5,6};
		ct3Reqs = new int[]{1,3,5,7};
		indi1Fitness = 0.8765;
		
		ct1 = new CaseTest(ct1Time, ct1Reqs, ct1Id);
		ct2 = new CaseTest(ct2Time, ct2Reqs, ct2Id);
		ct3 = new CaseTest(ct3Time, ct3Reqs, ct3Id);
		
		chrom1 = new Chromosome(ct1);
		chrom2 = new Chromosome(ct2);
		chrom3 = new Chromosome(ct3);
		
		chroms1 = new ArrayList<Chromosome>();
		chroms1.add(chrom1);
		chroms1.add(chrom2);
		chroms1.add(chrom3);
		indi1 = new Individual(chroms1, indi1Fitness);
		
		chroms2 = new ArrayList<Chromosome>();
		chroms2.add(chrom3);
		chroms2.add(chrom2);
		chroms2.add(chrom1);
		indi2 = new Individual(chroms2);
		
		chroms3 = new ArrayList<Chromosome>();
		chroms3.add(chrom2);
		chroms3.add(chrom1);
		indi3 = new Individual(chroms3);
		
		indi4 = new Individual();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.Individual#getChromosomes()}.
	 */
	@Test
	public void testGetChromosomes() {

		assertEquals(chroms1,indi1.getChromosomes());
		assertEquals(chroms2,indi2.getChromosomes());
		assertEquals(chroms3,indi3.getChromosomes());
		
	}

	/**
	 * Test method for {@link gelations.Individual#getFitness()} and 
	 * {@link gelations.Individual#setFitness(double)}.
	 */
	@Test
	public void testGetAndSetFitness() {
		
		double indi2Fitness = 0.9876;
		
		indi2.setFitness(indi2Fitness);
		
		assertEquals(indi1Fitness, indi1.getFitness(), 0.001);
		assertEquals(indi2Fitness, indi2.getFitness(), 0.001);
		assertEquals(-1.0, indi3.getFitness(), 0.001);
		
	}

	/**
	 * Test method for {@link gelations.Individual#addChromosome(gelations.Chromosome)} and 
	 * {@link gelations.Individual#getChromosome(int).
	 */
	@Test
	public void testAddChromosomeChromosome() {
		
		assertEquals(chroms3, indi3.getChromosomes());
		
		ArrayList<Chromosome> tempChroms = new ArrayList<Chromosome>(chroms3);
		tempChroms.add(chrom3);
		indi3.addChromosome(chrom3);
		
		assertEquals(tempChroms, indi3.getChromosomes());
		
	}

	/**
	 * Test method for {@link gelations.Individual#removeChromosome(int)}).
	 */
	@Test
	public void testRemoveChromosome() {
		
		assertEquals(chroms2, indi2.getChromosomes());
		
		ArrayList<Chromosome> tempChroms = new ArrayList<Chromosome>(chroms2);
		tempChroms.remove(1);
		indi2.removeChromosome(1);
		
		assertEquals(tempChroms, indi2.getChromosomes());
		
	}
	
	/**
	 * Test method for {@link gelations.Individual#getChromosome(int).
	 */
	@Test
	public void testGetChromosome() {
		
		assertEquals(chroms1.get(0), indi1.getChromosome(0));
		assertEquals(chroms2.get(1), indi2.getChromosome(1));
		assertEquals(chroms3.get(0), indi3.getChromosome(0));
		assertEquals(chroms1.get(2), indi1.getChromosome(2));
		assertEquals(chroms2.get(2), indi2.getChromosome(2));
		
	}

	/**
	 * Test method for {@link gelations.Individual#addChromosome(int, gelations.Chromosome)}.
	 */
	@Test
	public void testAddChromosomeIntChromosome() {
		
		assertEquals(chroms2, indi2.getChromosomes());
		
		ArrayList<Chromosome> tempChroms = new ArrayList<Chromosome>(chroms2);
		tempChroms.add(1, chrom3);
		indi2.addChromosome(1, chrom3);
		
		assertEquals(tempChroms, indi2.getChromosomes());
		
	}

	/**
	 * Test method for {@link gelations.Individual#replaceChromosome(int, gelations.Chromosome)}.
	 */
	@Test
	public void testReplaceChromosome() {
		
		assertEquals(chroms1, indi1.getChromosomes());
		
		ArrayList<Chromosome> tempChroms = new ArrayList<Chromosome>(chroms1);
		tempChroms.set(0, chrom3);
		indi1.replaceChromosome(0, chrom3);
		
		assertEquals(tempChroms, indi1.getChromosomes());
		
	}

	/**
	 * Test method for {@link gelations.Individual#size()}.
	 */
	@Test
	public void testSize() {
		
		assertEquals(chroms1.size(), indi1.size());
		assertEquals(chroms2.size(), indi2.size());
		assertEquals(chroms3.size(), indi3.size());
		assertEquals(0, indi4.size());
		
		indi1.removeChromosome(0);
		chroms1.remove(0);
		assertEquals(chroms1.size(), indi1.size());
		
	}

	/**
	 * Test method for {@link gelations.Individual#getIntArrayRepresentation()}.
	 */
	@Test
	public void testGetIntArrayRepresentation() {
		
		int[] tempArray = new int[]{0,1,2};
		
		for (int i=0; i<3; i++) {
			assertEquals(tempArray[i], indi1.getIntArrayRepresentation()[i]);
		}
		
		
	}

	/**
	 * Test method for {@link gelations.Individual#getStringRepresentation()}.
	 */
	@Test
	public void testGetStringRepresentation() {
		
		String tempString = "0Q1Q2";
		assertEquals(tempString, indi1.getStringRepresentation());
		
	}

	/**
	 * Test method for {@link gelations.Individual#setAccumulatedFitness(double)} and 
	 * {@link gelations.Individual#getAccumulatedFitness()}.
	 */
	@Test
	public void testSetAccumulatedFitnessAndGetAccumulatedFitness() {

		double accFitness = 12.345;
		
		indi1.setAccumulatedFitness(accFitness);
		assertEquals(accFitness, indi1.getAccumulatedFitness(), 0.001);
		
	}

	/**
	 * Test method for {@link gelations.Individual#copy(gelations.Individual)}.
	 */
	@Test
	public void testCopy() {
		
		Individual tempIndi2 = Individual.copy(indi2);
		assertNotSame(tempIndi2, indi2);
		
		Individual tempIndi4 = Individual.copy(indi4);
		assertNotSame(tempIndi4, indi4);
		
	}
	
	/**
	 * Test method for {@link gelations.Individual#compareTo(gelations.Individual)}.
	 */
	@Test
	public void testCompareTo() {
		
		double indi2Fitness = 0.5;
		double indi3Fitness = 0.5;
		double indi4Fitness = 0.1;
		
		indi2.setFitness(indi2Fitness);
		indi3.setFitness(indi3Fitness);
		indi4.setFitness(indi4Fitness);
		
		assertTrue(indi2.compareTo(indi3)==0);
		assertTrue(indi3.compareTo(indi2)==0);
		assertTrue(indi1.compareTo(indi2)>0);
		assertTrue(indi2.compareTo(indi1)<0);
		assertTrue(indi4.compareTo(indi3)<0);
		assertTrue(indi3.compareTo(indi4)>0);		
		
	}

}
