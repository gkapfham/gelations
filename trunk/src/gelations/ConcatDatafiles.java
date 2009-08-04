/**
 * Alexander Conrad
 * gelations.ConcatDatafiles.java
 */
package gelations;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;
//import java.io.FileNotFoundException;

/**
 * @author conrada
 *
 */
public class ConcatDatafiles {
    
    public static final String datapath = 
	"/home/conrada/Catterwampus/data/real/";
    public static final String[] datafile_prefix = 
    {"DS","GB","JD","LF","RM","RP","SK","TM"}; 
    public static final String concat_prefix = "AD";
    
    public static void main(String[] args) {
	
	String coverage_files[] = new String
	    [datafile_prefix.length];
	String time_files[] = new String
	    [datafile_prefix.length];
	
	for (int i=0; i<coverage_files.length; i++) {
	    coverage_files[i] = datapath + 
		datafile_prefix[i] + "Coverage.dat";
	    time_files[i] = datapath + 
		datafile_prefix[i] + "Time.dat";
	    
	    //debug
	    //System.out.println(coverage_files[i]+
	    // "\t"+time_files[i]);
	}
	
	String coverage_concat_file = datapath + 
	    concat_prefix + "Coverage.dat";
	String time_concat_file = datapath + 
	    concat_prefix + "Time.dat";
	
	//debug
	//System.out.println("\n"+coverage_concat_file+
	// "\t"+time_concat_file+"\n");
	
	concatCoverageDatafiles(coverage_files, 
				coverage_concat_file);
	concatTimeDatafiles(time_files, 
			    time_concat_file);		
	
    }
    
    public static void concatCoverageDatafiles(String[] 
					       datafiles,
					       String 
					       outputfile) {
	
	// number of test cases encountered before this app
	int count_cases_total = 0;
	// number of test cases discovered in this app
	int count_cases_app = 0;
	
	// number of reqs encountered before this app
	int count_reqs_total = 0;
	// number of reqs discovered in this app
	int count_reqs_app = 0;
	
	// temporary variables for the current test case
	//	which we are looking at
	int current_case;
	int current_req;
	
	try {
	    
	    Scanner coverage;
	    // to get rid of preexisting file...
	    (new File(outputfile)).delete();
	    
	    BufferedWriter out = new BufferedWriter
		(new FileWriter(outputfile));
	    
	    String header = "NameCD\tRequirements\n";
	    
	    out.write(header);
	    
	    // loop: for each iteration, read and write all
	    //	coverage info, incrementing test cases and
	    //	requirements accordingly.
	    for (int i=0; i<datafiles.length; i++) {
		
		// reset app-specific vars
		count_cases_app = 0;
		count_reqs_app = 0;
		
		coverage = new Scanner(new BufferedReader
				       (new FileReader
					(datafiles[i])));
		
		// to get rid of the header
		coverage.nextLine();
		
		// while there are more entries to read...
		while (coverage.hasNext()) {
		    
		    // read the first character, casting 
		    //	it to an int
		    current_case = coverage.nextInt();
		    
		    // if current test case number is the
		    //	largest encountered thus far, 
		    //	remember it
		    if (current_case > count_cases_app) {
			count_cases_app = current_case;
		    }
		    
		    // increment the test case number
		    current_case += count_cases_total; 
		    
		    // read the second character, casting
		    //	it to an int
		    current_req = coverage.nextInt();
		    
		    // if current req number is the
		    //	largest encountered thus far, 
		    //	remember it
		    if (current_req > count_reqs_app) {
			count_reqs_app = current_req;
		    }
		    
		    // increment the requirement number
		    current_req += count_reqs_total; 
		    
		    // construct and write the line to the
		    //	combined datafile
		    out.write(current_case+"\t"+current_req+"\n");
		    
		}
		
		// add number of cases and reqs encountered
		//	in this app to the total
		count_cases_total += count_cases_app;
		count_reqs_total += count_reqs_app;
		
	    }
	    
	    out.close();
	    
	} catch (Exception ex) {
	    ex.printStackTrace();
	    System.exit(1);
	}
	
    }
    
    public static void concatTimeDatafiles(String[] datafiles,
					   String outputfile) {
	
	// number of test cases encountered before this app
	int count_cases_total = 0;
	// number of test cases discovered in this app
	int count_cases_app = 0;
	// temporary variables for the current test case
	//	which we are looking at
	int current_case;
	double current_time;
	
	try {
	    
	    Scanner coverage;
	    // to get rid of preexisting file...
	    (new File(outputfile)).delete();
	    
	    BufferedWriter out = new BufferedWriter
		(new FileWriter(outputfile));
	    
	    String header = "NameTTD\tTime\n";
	    
	    out.write(header);
	    
	    // loop: for each iteration, read and write all
	    //	coverage info, incrementing test cases and
	    //	requirements accordingly.
	    for (int i=0; i<datafiles.length; i++) {
		
		// reset app-specific vars
		count_cases_app = 0;
		
		coverage = new Scanner(new BufferedReader
				       (new FileReader
					(datafiles[i])));
		
		// to get rid of the header
		coverage.nextLine();
		
		// while there are more entries to read...
		while (coverage.hasNext()) {
		    
		    // read the first character, casting 
		    //	it to an int
		    current_case = coverage.nextInt();
		    
		    // if current test case number is the
		    //	largest encountered thus far, 
		    //	remember it
		    if (current_case > count_cases_app) {
			count_cases_app = current_case;
		    }
		    
		    // increment the test case number
		    current_case += count_cases_total; 
		    
		    // read the second character, casting
		    //	it to a double
		    current_time = coverage.nextDouble();
		    
		    
		    // construct and write the line to the
		    //	combined datafile
		    out.write(current_case+"\t"+
			      current_time+"\n");
		    
		}
		
		// add number of cases and reqs encountered
		//	in this app to the total
		count_cases_total += count_cases_app;
		
	    }
	    
	    out.close();
	    
	} catch (Exception ex) {
	    ex.printStackTrace();
	    System.exit(1);
	}
	
    }
    
}
