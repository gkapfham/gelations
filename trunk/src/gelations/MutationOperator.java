/**
 * Alexander Conrad
 * gelations.MutationOperator.java
 */
package gelations;

import java.util.Random;
import java.util.ArrayList;

/**
 * Abstract instance class for performing mutations upon a given 
 * population.
 * 
 * @author conrada
 *
 */
public abstract class MutationOperator {
	
    public Random rng;
	
    /**
     * Creates a new MutationOperator with a given seed.
     * 
     * @param seed
     */
    public MutationOperator(long seed) {
		
	rng = new Random(seed);
		
    }
	
    /**
     * Creates a new MutationOperator with a random seed.
     *
     */
    public MutationOperator() {
		
	rng = new Random();
		
    }
	
    /**
     * Mutates a set of randomly chosen Individuals within a 
     * Population. Note: arguments may
     * be modified. Passing a defensively-created copy rather 
     * than the active object is
     * recommended.
     * 
     * @param population - a population containing Individuals 
     *  to be mutated
     * @param probability - a decimal in the set (0,1) indicating
     *   the probability each Individual has of being mutated
     * @return the mutated Population
     */
    public Population mutatePopulation
	(Population population, double probability) {
		
	Individual tempIndi;
	ArrayList<Individual> individuals 
	    = new ArrayList<Individual>(population.getPopSize());
		
	for (int indi=0; indi<population.getPopSize(); indi++) {
			
	    if (rng.nextDouble() < probability) {
				
		tempIndi = Individual.copy
		    (population.getIndividuals().get(indi));
		tempIndi = mutateIndividual(tempIndi);
		individuals.add(tempIndi);
				
	    } else {
				
		individuals.add(population.getIndividuals()
				.get(indi));
		
	    }
	    
	}
	
	return new Population(individuals);
	
    }
    
    /**
     * Mutate a specific Individual. Note: arguments may be
     * modified. Passing a defensively-
     * created copy rather than active object is recommended. 
     * 
     * @param individual - Individual to be mutated
     * @return the mutated Individual
     */
    public abstract Individual mutateIndividual
	(Individual individual);
    
}
