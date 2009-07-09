/**
 * Alexander Conrad
 * gelations.MutationOperatorSIM.java
 */
package gelations;

import java.util.ArrayList;
/**
 * This class performs mutation using the simple inversion 
 * mutation operator. See "Genetic
 * Algorithms for the Travelling Salesman Problem: A Review of 
 * Representations and Operators"
 * for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class MutationOperatorSIM extends MutationOperator {

    /**
     * Length, in elements, of subtour to be selected.
     */
    private int subtourLength;
	
    public static final int DEFAULT_SUBTOUR_LENGTH = 3;
	
    public MutationOperatorSIM(int _subtourLength, long seed) {
		
	super(seed);
	subtourLength = _subtourLength;
		
    }
	
    public MutationOperatorSIM(int _subtourLength) {
		
	super();
	subtourLength = _subtourLength;
		
    }

    public MutationOperatorSIM(long seed) {
	
	super(seed);
	subtourLength = DEFAULT_SUBTOUR_LENGTH;
	
    }

    public MutationOperatorSIM() {
	
	super();
	subtourLength = DEFAULT_SUBTOUR_LENGTH;
	
    }
	
    public Individual mutateIndividual(Individual _individual) {
		
	int length = _individual.size();
	Individual individual = new Individual
	    (_individual.getChromosomes());
		
	// override DEFAULT_SUBTOUR_LENGTH, for now
	subtourLength = length / 4 + rng.nextInt(length / 4 + 1);
		
	ArrayList<Chromosome> subtour = new ArrayList<Chromosome>
	    (subtourLength); 
	int cut1;
	//int j;
		
	// first, select subtour
	cut1 = rng.nextInt(length-subtourLength);
		
	// next, remove subtour
	for (int i=0; i<subtourLength; i++) {
			
	    subtour.add(individual.removeChromosome(cut1));
			
	}
				
	// finally, insert subtour at same location, in inverted 
	//  order
	//j = 0;
	for (Chromosome chromosome: subtour) {
			
	    individual.addChromosome(cut1, chromosome);
	    //individual.addChromosome(cut1+j, chromosome);
	    //j++;
			
	}
		
	return individual;
		
    }
	
}

	
	
	
	
