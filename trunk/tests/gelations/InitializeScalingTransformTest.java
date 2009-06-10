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
public class InitializeScalingTransformTest {

	ScalingTransform exp, un, win, linn, lint;
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
	 * Test method for {@link gelations.InitializeScalingTransform#getScalingTransform(int, long)}.
	 */
	@Test
	public void testGetScalingTransform() {
		
		exp = InitializeScalingTransform.getScalingTransform(0, seed);
		un = InitializeScalingTransform.getScalingTransform(1, seed);
		win = InitializeScalingTransform.getScalingTransform(2, seed);
		//linn = InitializeScalingTransform.getScalingTransform(3, seed);
		//lint = InitializeScalingTransform.getScalingTransform(4, seed);
		
		assertEquals("ScalingTransformExp", exp.getClass().getSimpleName());
		assertEquals("ScalingTransformUn", un.getClass().getSimpleName());
		assertEquals("ScalingTransformWin", win.getClass().getSimpleName());
		//assertEquals("ScalingTransformLinN", linn.getClass().getSimpleName());
		//assertEquals("ScalingTransformLinT", lint.getClass().getSimpleName());
		
		
	}

}
