/**
 * Alexander Conrad
 * gelations.CrossoverOperatorMPX.java
 */
package gelations;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class performs crossover using the maximal preservative 
 * crossover operator. See 
 * "Genetic Algorithms for the Travelling Salesman Problem: A 
 * Review of Representations and 
 * Operators" for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class CrossoverOperatorMPX extends CrossoverOperator {
    
    /**
     * Length, in elements, of subtour to be selected.
     */
    private int subtourLength;
	
    private boolean hasCrossedOverAgain;
	
    public static final int DEFAULT_SUBTOUR_LENGTH = 3;
	
    public CrossoverOperatorMPX(int _subtourLength, long seed) {
		
	super(seed);
	subtourLength = _subtourLength;
	hasCrossedOverAgain = false;
		
    }
	
    public CrossoverOperatorMPX(int _subtourLength) {
		
	super();
	subtourLength = _subtourLength;
	hasCrossedOverAgain = false;
		
    }

    public CrossoverOperatorMPX(long seed) {
	
	super(seed);
	subtourLength = DEFAULT_SUBTOUR_LENGTH;
	hasCrossedOverAgain = false;
		
    }

    public CrossoverOperatorMPX() {
	
	super();
	subtourLength = DEFAULT_SUBTOUR_LENGTH;
	hasCrossedOverAgain = false;
	
    }
	
    public ArrayList<Individual> performCrossover
	(Individual parent0, Individual parent1) {
		
	int length = parent0.size();
		
	// remove this section if we decide to manually specify 
	//  subtourLength
	//	length/4 <= subtoutLength <= length/2
	subtourLength = rng.nextInt(length/4+1) + length/4;
		
	HashMap<Integer,Integer> childChromosomes 
	    = new HashMap<Integer,Integer>
	    ((int)(length * 1.25), (float)1.0);
	
	Individual child = new Individual();
	Chromosome nextChromosome;
	int cut;
		
		
	// first, select subtour from first parent and copy it 
	//  into child
	cut = rng.nextInt(length-subtourLength);
	for (int i=cut; i<cut+subtourLength; i++) {
			
	    nextChromosome = parent0.getChromosome(i);
	    child.addChromosome(nextChromosome);
	    childChromosomes.put(nextChromosome.getId(), i);
			
	}
		
	// next, fill the remaining spots with Chromosomes from 
	//  second parent 
	for (int i=0; i<length; i++) {
			
	    nextChromosome = parent1.getChromosome(i);
	    // if the next chromosome from the second parent 
	    //  isn't already present in the child, add it
	    if (!childChromosomes.containsKey
		(nextChromosome.getId())) {
				
		child.addChromosome(nextChromosome);
		childChromosomes.put(nextChromosome.getId(), i);
				
	    }
			
	}
		
	// call this function again with the parents in reversed 
	//  order in order to produce two offspring, to maintain 
	//  comparability with other crossover operators 
	ArrayList<Individual> children;
	if (!hasCrossedOverAgain) {
	    hasCrossedOverAgain = true;
	    children = performCrossover(parent1, parent0);
	    hasCrossedOverAgain = false;
	} else {
	    children = new ArrayList<Individual>();
	}
		
	children.add(child);
		
	return children;		
		
    }
	
}
