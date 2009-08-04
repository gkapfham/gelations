/**
 * Alexander Conrad
 * gelations.MutationOperatorDM.java
 */
package gelations;

import java.util.ArrayList;
/**
 * This class performs mutation using the displacement mutation 
 * operator. See "Genetic
 * Algorithms for the Travelling Salesman Problem: A Review of 
 * Representations and Operators"
 * for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class MutationOperatorDM extends MutationOperator {

    /**
     * Length, in elements, of subtour to be selected.
     */
    private int subtourLength;
	
    public static final int DEFAULT_SUBTOUR_LENGTH = 3;
	
    public MutationOperatorDM(int _subtourLength, long seed) {
		
	super(seed);
	subtourLength = _subtourLength;
		
    }
	
    public MutationOperatorDM(int _subtourLength) {
		
	super();
	subtourLength = _subtourLength;
		
    }

    public MutationOperatorDM(long seed) {
	
	super(seed);
	subtourLength = DEFAULT_SUBTOUR_LENGTH;
		
    }

    public MutationOperatorDM() {
	
	super();
	subtourLength = DEFAULT_SUBTOUR_LENGTH;
	
    }
	
    public Individual mutateIndividual(Individual _individual) {

	int length = _individual.size();
	Individual individual 
	    = new Individual(_individual.getChromosomes());
		
	// override DEFAULT_SUBTOUR_LENGTH, for now
	subtourLength = length / 4 + rng.nextInt(length / 4 + 1);
		
	ArrayList<Chromosome> subtour 
	    = new ArrayList<Chromosome>(subtourLength); 
	int cut1, insert;
	
	// first, select subtour
	cut1 = rng.nextInt(length-subtourLength);
	
	// next, remove subtour
	for (int i=0; i<subtourLength; i++) {
	    
	    subtour.add(0, individual.removeChromosome(cut1));
	    
	}
	
	// finally, insert subtour at random location
	insert = rng.nextInt(individual.size());
	for (Chromosome chromosome: subtour) {
	    
	    individual.addChromosome(insert, chromosome);
	    
	}
	
	return individual;
	
    }
    
}
