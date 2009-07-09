/**
 * Alexander Conrad
 * gelations.ScalingTransform.java
 */
package gelations;

import java.util.Random;
import java.util.ArrayList;

/**
 * Abstract class for transforming the fitness of an individual 
 * with regard to a particular transformation method.
 * 
 * @author conrada
 *
 */
public abstract class ScalingTransform {

    Random rng;
    double minFitness;
    int popSize;
	
    public ScalingTransform(long seed) {
		
	rng = new Random(seed);
		
    }
	
    public ScalingTransform() {
		
	rng = new Random();
		
    }
	
    public Population transformPopulation(Population population) {
		
	ArrayList<Individual> individuals 
	    = population.getIndividuals();
	
	minFitness = population.getMinFitness();
	popSize = population.getPopSize();
		
	for (Individual individual: individuals) {
			
	    transformIndividual(individual);
			
	}
		
	return population;
		
    }
	
    // for testing purposes only
    public void setMinFitness(double _minFitness) {
		
	minFitness = _minFitness;
		
    }
	
    public abstract Individual transformIndividual
	(Individual individual);
	
}
