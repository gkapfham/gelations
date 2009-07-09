/**
 * Alexander Conrad
 * gelations.CreateRandomInitialPopulation.java
 */
package gelations;

import java.util.ArrayList;
import java.util.Random;
/**
 * Static class containing the necessary functionality for 
 * creating an initial random 
 * Population from a given set of Chromosomes.
 * 
 * @author conrada
 *
 */
public class CreateRandomInitialPopulation {

    /**
     * Stores a copy of the initial population, in case it 
     * should be needed later.
     */
    private static Population initialPopulation;
	
    /**
     * Creates a random Population of size popSize, and composed 
     * of Individuals constructed from Chromosomes chromosomes. 
     * 
     * @param chromosomes - the Chromosomes out of which the 
     *  Individuals are to be constructed
     * @param popSize - the number of Individuals to be present 
     *  within this Population
     * @param seed - the seed for the random number generator
     * @return a new, randomly-created Population
     */
    public static Population makePopulation
	(ArrayList<Chromosome> chromosomes, 
	 int popSize, long seed) {
		
	Random rng = new Random(seed);
	ArrayList<Individual> individuals 
	    = new ArrayList<Individual>(popSize);
		
	for (int indi=0; indi < popSize; indi++) {
			
	    individuals.add(makeNewRandomIndividual
			    (chromosomes, rng.nextLong()));
			
	}
		
	initialPopulation = new Population(individuals);
	return new Population(individuals);
		
    }
	
    /**
     * Creates a new Individual built out of a random 
     * reordering of chromosomes. 
     * 
     * @param chromosomes - the Chromosomes out of which the 
     *  Individual is to be constructed
     * @param seed - the seed for the random number generator
     * @return a new, randomly-constructed Individual
     */
    public static Individual makeNewRandomIndividual
	(ArrayList<Chromosome> chromosomes, long seed) {
		
	Random rng = new Random(seed);
	ArrayList<Chromosome> tempChromosomes 
	    = new ArrayList<Chromosome>(chromosomes);
	
	Individual tempIndividual 
	    = new Individual(tempChromosomes.size());
		
	for (int chrom=0; chrom < chromosomes.size(); chrom++) {
			
	    tempIndividual.addChromosome
		(tempChromosomes.remove
		 (rng.nextInt(tempChromosomes.size())));
				
	}
		
	return tempIndividual;
		
    }
	
    /**
     * 
     * @return a copy of the last-created initial random 
     *  population.
     */
    public static Population getInitialPopulation() {
	return initialPopulation;
    }
	
}
