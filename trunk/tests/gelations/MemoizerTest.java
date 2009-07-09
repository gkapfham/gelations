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
public class MemoizerTest {

	Memoizer<String,Double> memoizer;
	String str1, str2, str3, str4;
	double dub1, dub2, dub3, dub4;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		memoizer = new Memoizer<String,Double>();
		
		str1 = "0Q1Q2Q3";
		str2 = "3Q2Q1Q0";
		str3 = "1Q3Q0Q2";
		str4 = "2Q0Q3Q1";
		
		dub1 = 0.23;
		dub2 = 0.45;
		dub3 = 0.67;
		dub4 = 0.89;
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.Memoizer#getFitness(java.lang.Object)} and 
	 * {@link gelations.Memoizer#addFitness(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testGetAndAddFitness() {
		
		memoizer.addFitness(str1, dub1);
		memoizer.addFitness(str2, dub2);
		
		assertEquals(memoizer.getFitness(str1), dub1, 0.0000001);
		assertEquals(memoizer.getFitness(str2), dub2, 0.0000001);
		
		assertNull(memoizer.getFitness(str3));
		assertNull(memoizer.getFitness(str4));
		
	}

}
