package gelations;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;
//import java.io.FileNotFoundException;

public class CombineTransformAndSelection {
	
	public static final String REAL_DATAPATH = 
		"/home/conrada/Catterwampus/results/real/";

    public static final String[] DATAFILE_PREFIX = 
    	{"DS","GB","JD","LF","RM","RP","SK","TM"}; 
    
    public static final String DATAFILE_NAME = "Results.dat";
    
    public static final String OUTPUTFILE_NAME = "ResultsComb.dat";
	
    /*
     * reads all of the original datafiles, if a ROU line is found,
     * it is converted to ROU, ROUW, or ROUE depending on 
     * the transform operator used; for all rows, transform op is 
     * converted to UNT, redundant rows for other select ops are 
     * deleted
     */
	public static void main(String[] args) {
				
		// array for holding paths to datafiles
		String results_files[] = new String
			[DATAFILE_PREFIX.length];
		
		// array for holding paths to new output datafiles
		String output_files[] = new String
			[DATAFILE_PREFIX.length];			
		
		// fill path arrays with the paths 
		for (int i=0; i<results_files.length; i++) {
			results_files[i] = REAL_DATAPATH +
				DATAFILE_PREFIX[i] + DATAFILE_NAME;
			output_files[i] = REAL_DATAPATH +
				DATAFILE_PREFIX[i] + OUTPUTFILE_NAME;
		}

		try {
			
			// for each datafile...
			for (int i=0; i<results_files.length; i++) {
				
				System.out.println("starting to convert "+results_files[i]);
				
			    // to get rid of preexisting file...
			    (new File(output_files[i])).delete();
			    
				// set up the scanner, reader 
				Scanner data = new Scanner(
						new BufferedReader(
						new FileReader(
						results_files[i])));
				
				// set up writer
				BufferedWriter out = 
					new BufferedWriter(
					new FileWriter(
					output_files[i]));
				
			    // copy header
				out.write(data.nextLine()+"\n");
				
			    // while the datafile has more lines...
				while (data.hasNext()) {
										
					// set up a scanner to parse the line
					Scanner line = new Scanner(data.nextLine());
					
					// parse the line
					String datatype = line.next();
					String dataset = line.next();
					String crossover_operator = line.next();
					String mutation_operator = line.next();
					String selection_method = line.next();
					String fitness_transform = line.next();
					String mutation_rate = line.next();
					String child_density = line.next();
					String pop_size = line.next();
					String max_time = line.next();
					String target_fitness = line.next();
					String max_stagnancy = line.next();
					String metric = line.next();
					String rep = line.next();
					String order = line.next();
					String fitness = line.next();
					String runtime = line.next();
					String generations = line.next();
					String init_order = line.next();
					String init_fitness = line.next();
					String init_reverse_order = line.next();
					String init_reverse_fitness = line.next();
					String random_search_order = line.next();
					String random_search_fitness = line.next();
					String random_search_runtime = line.next();
					
					//System.out.println(selection_method+" "+fitness_transform);//debug
					// if not ROU and not UNT, delete
					if (!selection_method.equals("ROU") && !fitness_transform.equals("UNT")) {
						// do nothing
						//System.out.println("doing nothing");//debug
					} else {
						//System.out.println("doing something");//debug
						// if ROU and UNT, ROU
						if (selection_method.equals("ROU") && fitness_transform.equals("UNT")) {
							selection_method = "ROU";
						}
						
						// if ROU and LIN, ROUw
						else if (selection_method.equals("ROU") && fitness_transform.equals("LIN")) {
							selection_method = "ROUW";
							fitness_transform = "UNT";
						}
						
						// if ROU and EXP, ROUE
						else if (selection_method.equals("ROU") && fitness_transform.equals("EXP")) {
							selection_method = "ROUE";
							fitness_transform = "UNT";
						}
						
						// write line to the new datafile
						String toOutput = datatype+"\t"+
							dataset+"\t"+
							crossover_operator+"\t"+
							mutation_operator+"\t"+
							selection_method+"\t"+
							fitness_transform+"\t"+
							mutation_rate+"\t"+
							child_density+"\t"+
							pop_size+"\t"+
							max_time+"\t"+
							target_fitness+"\t"+
							max_stagnancy+"\t"+
							metric+"\t"+
							rep+"\t"+
							order+"\t"+
							fitness+"\t"+
							runtime+"\t"+
							generations+"\t"+
							init_order+"\t"+
							init_fitness+"\t"+
							init_reverse_order+"\t"+
							init_reverse_fitness+"\t"+
							random_search_order+"\t"+
							random_search_fitness+"\t"+
							random_search_runtime+"\n";
							
						//System.out.println("writing...");//debug
						out.write(toOutput);
						
					}
					
				}
				
				System.out.println("finished writing "+output_files[i]);
				out.close();
			}
			
		} catch (Exception ex) {
		    ex.printStackTrace();
		    System.exit(1);
		}
		
	}
	
}
