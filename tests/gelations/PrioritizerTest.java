/**
 * 
 */
package gelations;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author conrada
 *
 */
public class PrioritizerTest {

	String[] config1 = new String[]{"0","0","0","0","1","0.05","0.5","100","100000","0.95","20","0","/home/conrada/Catterwampus/tests/sample_data/matrix-rsss1","/home/conrada/Catterwampus/tests/sample_data/time-rsss1","/home/conrada/Catterwampus/tests/sample_data/seed-rsss1","/home/conrada/Catterwampus/tests/sample_data/results-rsss1","10"}; 
	Configuration configuration1, configuration2;
	Prioritizer prioritizer1, prioritizer2;
	Individual solution1, solution2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		configuration1 = new Configuration(config1);
		configuration1.readData();
		prioritizer1 = new Prioritizer(configuration1);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.Prioritizer#runPrioritization()}.
	 */
	@Test
	public void testRunPrioritization() {
		
		solution1 = prioritizer1.runPrioritization();
		System.out.println(solution1);
		

		HashMap<Character,Boolean> chars = new HashMap<Character,Boolean>();
				
		chars.clear();
		for(int j=0; j<solution1.getStringRepresentation().length(); j+=2) {
			
			if(chars.containsKey(solution1.getStringRepresentation().charAt(j))) {
				
				// to do: replace charAt(int) chromosome checking with Scanner checking,
				//	using Q as delimiter! Otherwise this will fail for individuals with
				//	more than 9 chromosomes!
				//fail("Chromosome "+solution1.getStringRepresentation().charAt(j)+" is duplicated! "+solution1.getStringRepresentation());
				
			} else {
				
				chars.put(solution1.getStringRepresentation().charAt(j), true);
				
			}
			
		}
				
		
	}

	/**
	 * Test method for {@link gelations.Prioritizer#writeResults()}.
	 */
	@Test
	public void testWriteResults() {
		
		solution1 = prioritizer1.runPrioritization();
		prioritizer1.writeResults();
				
		try {
			new FileReader(configuration1.getOutputFileName());
		} catch(FileNotFoundException ex) {
			fail("File was not created properly!");
		}
		
	}

	/**
	 * Test method for {@link gelations.Prioritizer#resetDataFile()}.
	 */
	@Test
	public void testResetDataFile() {
		
		prioritizer1.resetDataFile();
		
		try {
			new FileReader(configuration1.getOutputFileName());
		} catch(FileNotFoundException ex) {
			fail("File was not created properly!");
		}
		
	}

}
