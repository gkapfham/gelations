/**
 * Alexander Conrad
 * gelations.MutationOperatorISM.java
 */
package gelations;

/**
 * This class performs mutation using the insertion mutation 
 * operator. See "Genetic
 * Algorithms for the Travelling Salesman Problem: A Review of 
 * Representations and Operators"
 * for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class MutationOperatorISM extends MutationOperator {
    
    public MutationOperatorISM(long seed) {
	
	super(seed);
	
    }
    
    public Individual mutateIndividual(Individual _individual) {
	
	int length = _individual.size();
	Individual individual 
	    = new Individual(_individual.getChromosomes());
	Chromosome tempChrom = individual.removeChromosome
	    (rng.nextInt(length));
	individual.addChromosome(rng.nextInt(length-1), 
				 tempChrom);
		
	return individual;
		
    }

}
