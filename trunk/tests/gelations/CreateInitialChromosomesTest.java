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
public class CreateInitialChromosomesTest {
	
	CaseTest ct1, ct2, ct3;
	ArrayList<CaseTest> caseTests;
	
	double ct1Time, ct2Time, ct3Time;
	int ct1Id, ct2Id, ct3Id;
	int[] ct1Reqs, ct2Reqs, ct3Reqs;
	
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
		
		ct1 = new CaseTest(ct1Time, ct1Reqs, ct1Id);
		ct2 = new CaseTest(ct2Time, ct2Reqs, ct2Id);
		ct3 = new CaseTest(ct3Time, ct3Reqs, ct3Id);
		
		caseTests = new ArrayList<CaseTest>();
		caseTests.add(ct1);
		caseTests.add(ct2);
		caseTests.add(ct3);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.CreateInitialChromosomes#makeChromosomes(java.util.ArrayList)}.
	 */
	@Test
	public void testMakeChromosomes() {
		
		ArrayList<Chromosome> chromosomes;
		ArrayList<Chromosome> otherChromosomes = new ArrayList<Chromosome>();
		
		chromosomes = CreateInitialChromosomes.makeChromosomes(caseTests);
		
		otherChromosomes.add(new Chromosome(ct1));
		otherChromosomes.add(new Chromosome(ct2));
		otherChromosomes.add(new Chromosome(ct3));
		
		assertEquals(otherChromosomes.get(0).getId(), chromosomes.get(0).getId());
		assertEquals(otherChromosomes.get(1).getId(), chromosomes.get(1).getId());
		assertEquals(otherChromosomes.get(2).getId(), chromosomes.get(2).getId());
		
	}

	/**
	 * Test method for {@link gelations.CreateInitialChromosomes#getCurrentChromosomes()}.
	 */
	@Test
	public void testGetCurrentChromosomes() {
		
		ArrayList<Chromosome> chromosomes;
		ArrayList<Chromosome> otherChromosomes = new ArrayList<Chromosome>();
		
		CreateInitialChromosomes.makeChromosomes(caseTests);
		chromosomes = CreateInitialChromosomes.getCurrentChromosomes();
		
		otherChromosomes.add(new Chromosome(ct1));
		otherChromosomes.add(new Chromosome(ct2));
		otherChromosomes.add(new Chromosome(ct3));
		
		assertEquals(otherChromosomes.get(0).getId(), chromosomes.get(0).getId());
		assertEquals(otherChromosomes.get(1).getId(), chromosomes.get(1).getId());
		assertEquals(otherChromosomes.get(2).getId(), chromosomes.get(2).getId());
		
	}

}
