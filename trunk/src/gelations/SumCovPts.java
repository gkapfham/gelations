/**
 * Alexander Conrad
 * gelations.SumCovPts.java
 */
package gelations;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 * @author conrada
 *
 */
public class SumCovPts {
    
    /**
     * This class calculates the number of coverage points
     * covered by a particular test suite.
     * 
     * The first argument is the coverage datafile
     * containing the coverage information
     */
    
    public static void main(String[] args) {
	
	// path to timing datafile on filesystem
	String datafile = args[0];
	
	// keeps track of the largest coverage point 
	//	encountered thus far
	int maxCovPt = 0;
	
	// temporary variable for current coverage point
	int curCovPt;
	
	try {
	    
	    Scanner coveragePts 
		= new Scanner(new BufferedReader
			      (new FileReader(datafile)));
	    
	    // get rid of header
	    coveragePts.nextLine();
	    
	    // for each instance of coverage...
	    while (coveragePts.hasNext()) {
		
		// ignore test case id num
		coveragePts.nextInt();
		
		// compare cov pt id with maxCovPt
		curCovPt = coveragePts.nextInt();
		if (curCovPt > maxCovPt) {
		    
		    // if greater, replace
		    maxCovPt = curCovPt;
		    
		}
		
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
	
	// print result
	System.out.println("The total number of coverage points "
			   +"covered by the test suite "
			   +"represented in "+datafile+" is "
			   +maxCovPt+".");
	
    }
    
}
