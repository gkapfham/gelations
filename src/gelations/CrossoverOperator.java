/**
 * Alexander Conrad
 * gelations.CrossoverOperator.java
 */
package gelations;

import java.util.ArrayList;
import java.util.Random;
/**
 * Abstract instance class for performing crossover operations 
 * upon a given Population.
 * 
 * @author conrada
 *
 */
public abstract class CrossoverOperator {

    /**
     * Random number generator for generating the seeds for 
     * the crossover operations
     */
    public Random rng;
	
    /**
     * Creates a new CrossoverOperator with a fixed seed.
     * 
     * @param seed - seed for the initial random number generator
     */
    public CrossoverOperator(long seed) {
		
	rng = new Random(seed);
		
    }
	
    /**
     * Creates a new CrossoverOperator with a random seed.
     *
     */
    public CrossoverOperator() {
		
	rng = new Random();
		
    }
	
    /**
     * Given a list of parent Individuals, this method uses a 
     * specific crossover technique to 
     * generate a list of child Individuals. If there are an 
     * odd number of parent
     * Individuals, the first Individual in the list will be 
     * duplicated so that each parent
     * will have a mate to be crossed with. 
     * 
     * @param parents - parent Individuals for crossover
     * @return list of child Individuals resulting from crossover
     */
    public ArrayList<Individual> createChildren
	(ArrayList<Individual> _parents) {
				
	ArrayList<Individual> parents = new ArrayList<Individual>
	    (_parents);
		
	// if the number of parents is odd...
	if (parents.size() % 2 != 0) {
			
	    // copy the first parent and add it to the end of 
	    // the list so that we have an even number of parents
	    parents.add(parents.get(0));
			
	}

	ArrayList<Individual> children 
	    = new ArrayList<Individual>(parents.size()/2);
		
	for (int p=0; p<parents.size(); p+=2) {
			
	    children.addAll(performCrossover
			    (parents.get(p), parents.get(p+1)));
			
	}
		
	return children;
		
    }
	
    /**
     * Given two parent Individuals, this method will produce 
     * one or more child Individuals,
     * depending upon the chosen CrossoverOperator.
     * 
     * @param parent1 - first parent Individual
     * @param parent2 - second parent Individual
     * @return - list containing child(ren)
     */
    public abstract ArrayList<Individual> performCrossover
	(Individual parent1, Individual parent2);
    
    
}
