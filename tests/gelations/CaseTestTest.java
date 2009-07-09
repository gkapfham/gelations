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
public class CaseTestTest {

	CaseTest ct1, ct2, ct3;
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
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.CaseTest#setReqs(int[])}.
	 */
	@Test
	public void testSetReqs() {
		
		int[] ct2Reqs = new int[]{5,0,8,11};
		ct2.setReqs(ct2Reqs);
		
		assertEquals(ct2Reqs, ct2.getRequirements());
				
	}

	/**
	 * Test method for {@link gelations.CaseTest#getTime()}.
	 */
	@Test
	public void testGetTime() {
		
		assertEquals(ct1Time, ct1.getTime(), 0.01);
		
	}

	/**
	 * Test method for {@link gelations.CaseTest#getRequirements()}.
	 */
	@Test
	public void testGetRequirements() {
		
		assertEquals(ct1Reqs, ct1.getRequirements());
		
	}

	/**
	 * Test method for {@link gelations.CaseTest#getId()}.
	 */
	@Test
	public void testGetId() {
		
		assertEquals(ct3Id, ct3.getId());
		
	}

}
