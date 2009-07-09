/**
 * 
 */
package gelations;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author conrada
 *
 */
public class ChromosomeTest {

	CaseTest ct1, ct2, ct3;
	Chromosome chrom1, chrom2, chrom3, chrom4;
	
	double ct1Time;
	int ct1Id, ct2Id, ct3Id;
	int ct2NumReqs;
	int[] ct1Reqs;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		ct1Time = 3.14159265;
		ct1Id = 0;
		ct2Id = 1;
		ct3Id = 2;
		ct2NumReqs = 4;
		ct1Reqs = new int[]{1, 2, 9, 11, 0, 984};
		
		ct1 = new CaseTest(ct1Time, ct1Reqs, ct1Id);
		ct2 = new CaseTest(ct2NumReqs, ct2Id);
		ct3 = new CaseTest(ct3Id);
		
		chrom1 = new Chromosome(ct1);
		chrom2 = new Chromosome(ct2);
		chrom3 = new Chromosome(ct3);
		chrom4 = new Chromosome();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.Chromosome#getTime()}.
	 */
	@Test
	public void testGetTime() {
		
		assertEquals(ct1Time, chrom1.getTime(), 0.01);
		assertEquals(-1, chrom2.getTime(), 0.01);
		assertEquals(-1, chrom3.getTime(), 0.01);
		
	}

	/**
	 * Test method for {@link gelations.Chromosome#getRequirements()}.
	 */
	@Test
	public void testGetRequirements() {
		
		assertEquals(ct1Reqs, chrom1.getRequirements());
		
	}

	/**
	 * Test method for {@link gelations.Chromosome#getId()}.
	 */
	@Test
	public void testGetId() {
		
		assertEquals(ct1Id,chrom1.getId());
		assertEquals(ct2Id,chrom2.getId());
		assertEquals(ct3Id,chrom3.getId());
		
	}

	/**
	 * Test method for {@link gelations.Chromosome#getCaseTest()}.
	 */
	@Test
	public void testGetCaseTest() {
		
		assertEquals(ct1,chrom1.getCaseTest());
		assertEquals(ct2,chrom2.getCaseTest());
		assertEquals(ct3,chrom3.getCaseTest());
		assertNull(chrom4.getCaseTest());
		
	}

	/**
	 * Test method for {@link gelations.Chromosome#copy(gelations.Chromosome)}.
	 */
	@Test
	public void testCopy() {
		
		assertNotSame(chrom1, Chromosome.copy(chrom1));
		assertNotSame(chrom2, Chromosome.copy(chrom2));
		assertNotSame(chrom3, Chromosome.copy(chrom3));
		assertNotSame(chrom4, Chromosome.copy(chrom4));
		
	}

}
