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
public class InitializeCrossoverOperatorsTest {

	CrossoverOperator cx, mpx, ox1, ox2, pmx, pos, vr;
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
	 * Test method for {@link gelations.InitializeCrossoverOperators#getCrossoverOperator(int, long)}.
	 */
	@Test
	public void testGetCrossoverOperator() {
		
		cx = InitializeCrossoverOperators.getCrossoverOperator(0, seed);
		mpx = InitializeCrossoverOperators.getCrossoverOperator(1, seed);
		ox1 = InitializeCrossoverOperators.getCrossoverOperator(2, seed);
		ox2 = InitializeCrossoverOperators.getCrossoverOperator(3, seed);
		pmx = InitializeCrossoverOperators.getCrossoverOperator(4, seed);
		pos = InitializeCrossoverOperators.getCrossoverOperator(5, seed);
		vr = InitializeCrossoverOperators.getCrossoverOperator(6, seed);
		
		assertEquals("CrossoverOperatorCX",cx.getClass().getSimpleName());
		assertEquals("CrossoverOperatorMPX",mpx.getClass().getSimpleName());
		assertEquals("CrossoverOperatorOX1",ox1.getClass().getSimpleName());
		assertEquals("CrossoverOperatorOX2",ox2.getClass().getSimpleName());
		assertEquals("CrossoverOperatorPMX",pmx.getClass().getSimpleName());
		assertEquals("CrossoverOperatorPOS",pos.getClass().getSimpleName());
		assertEquals("CrossoverOperatorVR",vr.getClass().getSimpleName());
		
	}

}
