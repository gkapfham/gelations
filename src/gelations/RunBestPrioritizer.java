/**
 * Alexander Conrad
 * gelations.RunBestPrioritizer.java
 */
package gelations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class RunBestPrioritizer {
	
	final static String SEEDFILE_NAME = "tempseed";
	final static int TOTAL_ARGS = 3;
	final static String CALL = "String coverage_data_file  " +
			"String timing_data_file String output_data_file";
	
	/**
	 * This class simply executes the prioritizer, with the
	 * supplied coverage and timing information and
	 * outputfile path, using the best configuration as 
	 * determined by empirical analysis
	 * 
	 */
	public static void main(String args[]) {
		
		// make sure we have the right number of args
		if (args.length != TOTAL_ARGS) {
		    
		    System.out.println("Incorrect arguments provided. "
				       +"Proper arguments are:");
		    System.out.println(" "+CALL);
		    System.exit(1);
		    
		}
		
		// have to make the seed datafile to 
		// appease Configuration
		(new File(SEEDFILE_NAME)).delete();
		BufferedWriter out;
		try {
		    out = new BufferedWriter(new FileWriter(SEEDFILE_NAME));
		    out.write("Seeds \n"+(new Random()).nextInt());
		    out.close();
		}
		catch (IOException ex) {ex.printStackTrace(); System.exit(-1);}

		// RunPrioritizer with best config
		String[] prioritizerArgs = {"1", "2", "3", "1", "1",
				"0.1", "0.5", "75", "10000000000", "1.0",
				"20000", "0", args[0], args[1], SEEDFILE_NAME,
				args[2], "1"};  
		//InitDatafile.main(prioritizerArgs);
		Configuration config = new Configuration(prioritizerArgs);
		WriteResults.writeHeader(config);
		
		RunPrioritizer.main(prioritizerArgs);
		
		// delete the temp seed datafile
		(new File(SEEDFILE_NAME)).delete();		
		
	}
	
}
