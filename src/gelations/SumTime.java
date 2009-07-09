/**
 * Alexander Conrad
 * gelations.SumTime.java
 */
package gelations;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 * @author conrada
 *
 */
public class SumTime {
    
    /**
     * This class calculates the total execution time of a 
     * particular test suite.
     * 
     * The first argument is the timing datafile containing
     * the timing information
     */
    public static void main(String[] args) {
	
	// path to timing datafile on filesystem
	String datafile = args[0];
	
	// accumulator to store the total runtime of suite
	double totalTime = 0;
	
	try {
	    
	    Scanner testCaseTimes = new Scanner
		(new BufferedReader(new FileReader(datafile)));
	    
	    // get rid of header
	    testCaseTimes.nextLine();
	    
	    // for each line in file...
	    while (testCaseTimes.hasNext()) {
		
		// get rid of first entry...
		testCaseTimes.nextInt();
		
		// and add second entry to accumulator
		totalTime += testCaseTimes.nextDouble();
		
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
	
	// print total
	System.out.println("The total execution time (in ms?) of "
			   +"the test suite represented in "
			   +datafile+" is "+totalTime+".");
	
    }
    
}
