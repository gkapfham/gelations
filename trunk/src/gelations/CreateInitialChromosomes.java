/**
 * Alexander Conrad
 * gelations.CreateInitialChromosomes.java
 */
package gelations;

import java.util.ArrayList;
/**
 * Static class containing functions necessary for the creation 
 * of the initial list of 
 * Chromosomes from a given set of CaseTests.
 * 
 * @author conrada
 *
 */
public class CreateInitialChromosomes {

    /**
     * Stores a copy of the list of most recently created 
     * chromosomes, in case they need
     * to be retrieved for some reason.
     */
    private static ArrayList<Chromosome> currentChromosomes;
    
    /**
     * Given an ArrayList of CaseTests, this static method 
     * returns an ArrayList of 
     * Chromosomes representing those CaseTests.
     * 
     * @param testCases - the CaseTests for which chromosome 
     *  representation is requested
     * @return the list of chromosomes representing all test cases
     */
    public static ArrayList<Chromosome> makeChromosomes
	(ArrayList<CaseTest> testCases) {
	
	ArrayList<Chromosome> chromosomes 
	    = new ArrayList<Chromosome>(testCases.size());
	
	for (CaseTest testCase: testCases) {
	    chromosomes.add(new Chromosome(testCase));
	}
	
	currentChromosomes = new ArrayList<Chromosome>
	    (chromosomes);
	
	return new ArrayList<Chromosome>(chromosomes);
	
    }
    
    /**
     * 
     * @return a copy of the list of most recently created 
     *  Chromosomes. 
     */
    public static ArrayList<Chromosome> getCurrentChromosomes() {
	return new ArrayList<Chromosome>(currentChromosomes);
    }
	
}