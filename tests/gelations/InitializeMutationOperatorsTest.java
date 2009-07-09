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
public class InitializeMutationOperatorsTest {

	MutationOperator dm, em, ism, ivm, sim, sm;
	long seed;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		seed = 1234567890;
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.InitializeMutationOperators#getMutationOperator(int, long)}.
	 */
	@Test
	public void testGetMutationOperator() {
		
		dm = InitializeMutationOperators.getMutationOperator(0, seed);
		em = InitializeMutationOperators.getMutationOperator(1, seed);
		ism = InitializeMutationOperators.getMutationOperator(2, seed);
		ivm = InitializeMutationOperators.getMutationOperator(3, seed);
		sim = InitializeMutationOperators.getMutationOperator(4, seed);
		sm = InitializeMutationOperators.getMutationOperator(5, seed);
		
		assertEquals("MutationOperatorDM",dm.getClass().getSimpleName());
		assertEquals("MutationOperatorEM",em.getClass().getSimpleName());
		assertEquals("MutationOperatorISM",ism.getClass().getSimpleName());
		assertEquals("MutationOperatorIVM",ivm.getClass().getSimpleName());
		assertEquals("MutationOperatorSIM",sim.getClass().getSimpleName());
		assertEquals("MutationOperatorSM",sm.getClass().getSimpleName());
		
	}

}
