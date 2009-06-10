/**
 * Alexander Conrad
 * gelations.ConfigurationReaderSynthetic.java
 */
package gelations;

import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * This class reads files containing synthetic test data.
 * 
 * @author conrada
 * @see ConfigurationReader.java
 */
public class ConfigurationReaderSynthetic 
    implements ConfigurationReader, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public static final int[] HEIGHT = {2, 5, 10};
    public static final int[] WIDTH = {10, 50, 100};
    
    
    public ArrayList<CaseTest> readCaseTests
	(String coverageFileName, String timeFileName) {
	
	//debug
	//System.out.println("entering reader:");
	
	ArrayList<CaseTest> caseTests = new ArrayList<CaseTest>();
	
	char height, width;
	int h, w;
	
	try {
	    
	    ArrayList<ArrayList<Integer>> caseTestsArray 
		= new ArrayList<ArrayList<Integer>>();
	    
	    ArrayList<Integer> timeArray 
		= new ArrayList<Integer>();
	    
	    Scanner coverage = new Scanner
		(new BufferedReader
		 (new FileReader(coverageFileName)));
	    
	    Scanner time = new Scanner
		(new BufferedReader
		 (new FileReader(timeFileName)));
	    
	    if (!(coverageFileName.charAt
		  (coverageFileName.length()-1)=='0')) {
		
		height = coverageFileName.charAt
		    (coverageFileName.length()-3);
		
		width = coverageFileName.charAt
		    (coverageFileName.length()-4);
		
	    } else {
		
		height = coverageFileName.charAt
		    (coverageFileName.length()-4);
		
		width = coverageFileName.charAt
		    (coverageFileName.length()-5);
		
	    }
	    
	    int[] reqs;
	    
	    switch (width) {
		
	    case 's': w = WIDTH[0]; break;
	    case 'm': w = WIDTH[1]; break;
	    case 'l': w = WIDTH[2]; break;
	    default: w = 0; 
		System.out.println("Invalid datafile name."); 
		System.exit(1); 
		break;
		
	    }
	    
	    //debug:
	    //System.out.println("w:"+width+","+w);
	    
	    switch (height) {
		
	    case 's': h = HEIGHT[0] * w; break;
	    case 'm': h = HEIGHT[1] * w; break;
	    case 'l': h = HEIGHT[2] * w; break;
	    default: h = 0; 
		System.out.println("Invalid datafile name."); 
		System.exit(1); 
		break;
		
	    }
	    
	    for (int c=0; c<w; c++) {
		
		caseTestsArray.add(new ArrayList<Integer>());
		
	    }
	    
	    //debug
	    //System.out.println("h:"+height+","+h);
	    
	    // get time information
	    time.nextLine();
	    
	    for (int r=0; r<w; r++) {
		
		time.nextInt();
		timeArray.add(time.nextInt());
		
	    }
	    
	    // for each row,
	    for (int r=0; r<h; r++) {
		
		// for each column in row,
		for (int c=0; c<w; c++) {
		    
		    // if entry is "1", add requirement to 
		    //  test case
		    if (coverage.nextInt()==1) {
			
			//debug
			//System.out.println("adding req "+r+" 
			// to case "+c+".");
			
			caseTestsArray.get(c).add(r);
			
		    }
		    
		}
		
		coverage.nextInt();
		
	    }
			
	    // put requirements into an array format as 
	    //  required by CaseTest
	    
	    for (int i=0; i<caseTestsArray.size(); i++) {
		
		ArrayList<Integer> requirements 
		    = caseTestsArray.get(i);
		
		reqs = new int[requirements.size()];
		
		for (int j=0; j<reqs.length; j++) {
		    
		    reqs[j] = requirements.get(j);
		    
		}
		
		caseTests.add(new CaseTest
			      (timeArray.get(i), reqs, i+1));
		
	    }		
	    
	}
	catch (FileNotFoundException ex) {
	    ex.printStackTrace();
	    System.exit(1);
	}
	
	return caseTests;
	
    }
    
    public ArrayList<Integer> readSeeds(String seedFileName) {
	
	Scanner seeds;
	ArrayList<Integer> seedValues = new ArrayList<Integer>();
	
	try {
	    seeds = new Scanner(new BufferedReader
				(new FileReader(seedFileName)));
	    
	    seeds.skip("Seeds");
	    
	    while (seeds.hasNextInt()) {
		
		seedValues.add(seeds.nextInt());
		
	    }
	    
	}
	catch (FileNotFoundException ex) {
	    ex.printStackTrace();
	    System.exit(1);
	}
	
	return seedValues;
	
    }
    
    public String getSetConfig(String coverageFileName) {
	
	if (coverageFileName.charAt
	    (coverageFileName.length()-1)=='0') {
	    
	    return coverageFileName.substring
		(coverageFileName.length()-6);
	    
	} else {
	    return coverageFileName.substring
		(coverageFileName.length()-5);			
	}
	
    }
    
}
