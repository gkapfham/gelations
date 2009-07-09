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
public class MakeConfigurationTest {

	String[] config1 = new String[]{"0","0","0","0","1","0.05","0.5","100","100000","0.95","8","0","/home/conrada/Catterwampus/tests/sample_data/matrix-rsss1","/home/conrada/Catterwampus/tests/sample_data/time-rsss1","/home/conrada/Catterwampus/tests/sample_data/seed-rsss1","/home/conrada/Catterwampus/tests/sample_data/results-rsss1","10"}; 
	Configuration configuration1;
		
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		configuration1 = new Configuration(config1);
		configuration1.readData();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.MakeConfiguration#serialize(java.lang.String, gelations.Configuration)}.
	 */
	@Test
	public void testSerialize() {
		
		String objectLocation = configuration1.getObjectName();
		
		assertEquals("synthetic_rsss1_DM_CX_ROU_UNT_0.05_0.5_100_100000.0_0.95_8_CE_",objectLocation);
		
		assertTrue(MakeConfiguration.serialize(objectLocation, configuration1, "/home/conrada/Catterwampus/configs/"));
		
	}

}
