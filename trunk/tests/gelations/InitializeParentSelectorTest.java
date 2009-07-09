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
public class InitializeParentSelectorTest {

	ParentSelector rou, tru, tou;
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
	 * Test method for {@link gelations.InitializeParentSelector#getParentSelector(int, long)}.
	 */
	@Test
	public void testGetParentSelector() {
		
		rou = InitializeParentSelector.getParentSelector(0, seed);
		tru = InitializeParentSelector.getParentSelector(1, seed);
		tou = InitializeParentSelector.getParentSelector(2, seed);
		//rnd = InitializeParentSelector.getParentSelector(3, seed);
		
		assertEquals("ParentSelectorRou", rou.getClass().getSimpleName());
		assertEquals("ParentSelectorTru", tru.getClass().getSimpleName());
		assertEquals("ParentSelectorTou", tou.getClass().getSimpleName());
		//assertEquals("ParentSelectorRnd", rnd.getClass().getSimpleName());
		
	}

}
