/**
 * 
 */
package gelations;

import java.util.ArrayList;

/**
 * @author conrada
 *
 */
public class BruteForce {

	
	public static void main(String[] args) {
		
		// create the config obj?
		// hardcode for lab now, sk app
		String datapath = "/home/cs2/conrada/Catterwampus/data/real/";
		
		String config_params[] = {"1","0","0","0","0","0","1","1","10000000","1.0","1000","0",datapath+"SKCoverage.dat",datapath+"SKTime.dat",datapath+"SKSeed.dat","null","1"};
		
		Configuration config = new Configuration(config_params);
		
		// read in the data, create initial chroms
		ArrayList<Chromosome> initChromosomes = CreateInitialChromosomes.makeChromosomes(config.getCaseTests());
		
		// create the first ordering
		Individual firstIndividual = new Individual(initChromosomes);
		
		// do the brute force
		//start timing
		long startTime = System.currentTimeMillis();
		
		
		
		//end timing
		long runTime = startTime - System.currentTimeMillis();
		// print out the best ce, ordering, and the time
		
		//System.out.println("The best ordering, as determined by brute force, is "++" with a CE value of "++". The brute force execution took "+runTime+" milliseconds.");
		
	}
	
	
	
}
