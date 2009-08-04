/**
 * Alexander Conrad
 * gelations.FindBest.java
 */
package gelations;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 * @author conrada
 *
 */
public class FindBest {
	
    /**
     * This class looks through a datafile produced by the
     * genetic algorithm-based prioritizer, locating the
     * record with the highest coverage effectiveness and
     * printing out the relevant data associated with that 
     * record.
     * 
     * The first argument is the datafile containing the 
     * results of the prioritizer execution.
     */
    public static void main(String[] args) {
		
	// path to timing datafile on filesystem
	String datafile = args[0];
		
	double maxCE = 0;
	double tempCE = 0;
	double maxCERuntime = 0;
		
	try {
			
	    Scanner data = new Scanner
		(new BufferedReader(new FileReader(datafile)));
			
	    // remove header
	    data.nextLine();
			
	    // for each record...
	    while (data.hasNext()) {
				
		// ignore the values we don't care about...
		for (int i=0; i<15; i++) {
		    data.next();
		}
				
		// if record has larger ce than all others so far
		tempCE = data.nextDouble();
		if(tempCE > maxCE) {

		    // save ce and runtime
		    maxCE = tempCE;
		    maxCERuntime = data.nextDouble();
					
		}
				
		data.nextLine();
				
	    }
			
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
		
	// print best ce and runtime
	System.out.println("The best ce in "
			   +datafile+" is "+maxCE+" with runtime "
			   +maxCERuntime+".");
		
    }
	
}
