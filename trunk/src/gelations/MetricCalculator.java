/**
 * Alexander Conrad
 * gelations.MetricCalculator.java
 */
package gelations;

/**
 * Abstract instance class for computing the fitness of a given 
 * Individual. 
 * 
 * @author conrada
 *
 */
public abstract class MetricCalculator {
    
    /**
     * Memoization object for caching the results of previous 
     * fitness computations
     */
    Memoizer<String,Double> cache;
    
    /**
     * Creates a new MetricCalculator with an associated Memoizer.
     *
     */
    public MetricCalculator() {
	
	cache = new Memoizer<String,Double>();
	
    }
    
    /**
     * Check the Memoizer to see if the fitness for this 
     * Individual has been calculated
     * before. 
     * 
     * @param individual
     * @return fitness value if it has been computed, else null 
     *  if not yet computed
     */
    public Double lookup(Individual individual) {
	
	String indiStr = individual.getStringRepresentation();
	
	return cache.getFitness(indiStr);
	
    }
    
    /**
     * Compute the fitness values for all individuals in an 
     * entire Population.
     * 
     * @param population
     */
    public void computeFitness(Population population) {
	
	for (Individual individual: population.getIndividuals()) {
			
	    computeFitness(individual);
	    
	    //debug
	    //System.out.println(individual.getFitness());
	    
	}
	
    }
    
    /**
     * This method computes a particular metric and returns that 
     * value as the fitness of
     * the Individual. This method also stores the fitness within
     * the Individual itself.
     * 
     * @param individual - the Individual for which the fitness 
     *  is desired
     * @return the Individual's fitness
     */
    public abstract double computeFitness(Individual individual);
    
}
