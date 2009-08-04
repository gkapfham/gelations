/**
 * Alexander Conrad
 * gelations.CrossoverOperatorCX.java
 */
package gelations;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class performs crossover using the cycle crossover 
 * operator. See "Genetic
 * Algorithms for the Travelling Salesman Problem: A Review of 
 * Representations and Operators"
 * for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class CrossoverOperatorCX extends CrossoverOperator {
	
    private boolean hasCrossedOverAgain;
	
    public CrossoverOperatorCX(long seed) {
		
	super(seed);
	hasCrossedOverAgain = false;
		
    }
	
    public ArrayList<Individual> performCrossover
	(Individual parent0, Individual parent1) {
		

	int length = parent0.size();
		
	// these HashMaps map a Chromosome Id onto the index at 
	// which it occurs in the parent
	HashMap <Integer, Integer> parent0Chromosomes 
	    = new HashMap<Integer, Integer>
	    ((int)(length * 1.25), (float)1.0);
	
	HashMap <Integer, Integer> parent1Chromosomes 
	    = new HashMap<Integer, Integer>
	    ((int)(length * 1.25), (float)1.0);
	
	Individual child = new Individual(length);
	Chromosome dummy = new Chromosome();
		
	int filledPositions, lastParent, lastChromosome, 
	    lastChromosomeIndex, lastCoorespondingChromosome;
	boolean positionIsEmpty;
	boolean[] hasBeenReplaced;
		
	hasBeenReplaced = new boolean[length];
		
	// populate the HashMaps with mappings between elements 
	//  and their indices in the parents
	// also, fill the individuals with dummy chromosomes
	for (int i=0; i<length; i++) {
			
	    parent0Chromosomes.put
		(parent0.getChromosome(i).getId(), i);
	    
	    parent1Chromosomes.put
		(parent1.getChromosome(i).getId(), i);
	    
	    child.addChromosome(dummy);
	    hasBeenReplaced[i] = false;
			
	}
		
	// assign chromosomes to the child, beginning with 
	//  chromosome 0 from random parent
	lastParent = rng.nextInt(2);
	
	if (lastParent == 0) {
	    
	    lastChromosome = parent0.getChromosome(0).getId();
	    lastChromosomeIndex = 0;
	    child.replaceChromosome(0, parent0.getChromosome(0));
	    hasBeenReplaced[0] = true;
	    
	} else {
	    
	    lastChromosome = parent1.getChromosome(0).getId();
	    lastChromosomeIndex = 0;
	    child.replaceChromosome(0, parent1.getChromosome(0));
	    hasBeenReplaced[0] = true;
	    
	}
	
	filledPositions = 1;
		
	while (filledPositions < length) {
			
	    if (lastParent == 0) {
				
		// remove last used chromosome from contributing 
		//  parent's hash
		parent0Chromosomes.remove(lastChromosome);
				
		// find out what chromosome existed in the same 
		//  location in the opposing parent
		lastCoorespondingChromosome = parent1.
		    getChromosome(lastChromosomeIndex).getId();
				
		// see if that chromosome has been supplied yet 
		//  by the contributing parent
		if (parent0Chromosomes.containsKey
		    (lastCoorespondingChromosome)) {
					
		    // if it hasn't yet, supply it now
		    lastChromosomeIndex = parent0Chromosomes.get
			(lastCoorespondingChromosome);
		    
		    lastChromosome = parent0.getChromosome
			(lastChromosomeIndex).getId();
		    
		    child.replaceChromosome
			(lastChromosomeIndex,
			 parent0.getChromosome
			 (lastChromosomeIndex));
		    
		    hasBeenReplaced[lastChromosomeIndex] = true;
		    filledPositions++;
					
		} else {
					
		    // if it already has, then the cycle is 
		    //  complete and we can randomly
		    //	select a parent to begin the new cycle
					
		    // first, find the next empty position
		    positionIsEmpty = false;
		    lastChromosomeIndex = 1;
		    while (!positionIsEmpty) {
						
			if (child.getChromosome
			    (lastChromosomeIndex).getCaseTest() 
			    == null) {
			    
			    positionIsEmpty = true;
			    
			} else {
			    
			    lastChromosomeIndex++;
			    
			}
						
		    }
					
		    // next, randomly select a parent to begin 
		    //  the new cycle
		    lastParent = rng.nextInt(2);
					
		    if (lastParent == 0) {
						
			lastChromosome = parent0.getChromosome
			    (lastChromosomeIndex).getId();
			
			child.replaceChromosome
			    (lastChromosomeIndex, 
			     parent0.getChromosome
			     (lastChromosomeIndex));
			
			hasBeenReplaced[lastChromosomeIndex] 
			    = true;
			
			filledPositions++;
						
		    } else {
						
			lastChromosome = parent1.getChromosome
			    (lastChromosomeIndex).getId();
			
			child.replaceChromosome
			    (lastChromosomeIndex,
			     parent1.getChromosome
			     (lastChromosomeIndex));
			
			hasBeenReplaced[lastChromosomeIndex] 
			    = true;
			filledPositions++;
						
		    }
					
		}
				
	    } else {
				
		// repeat everything for the reverse 
		//  configuration of the parents
				
		// remove last used chromosome from contributing 
		//  parent's hash
		parent1Chromosomes.remove(lastChromosome);
				
		// find out what chromosome existed in the same 
		//  location in the opposing parent
		lastCoorespondingChromosome = parent0.
		    getChromosome(lastChromosomeIndex).getId();
				
		// see if chromosome has been supplied yet by 
		//  the contributing parent
		if (parent1Chromosomes.containsKey
		    (lastCoorespondingChromosome)) {
					
		    // if it hasn't yet, supply it now
		    lastChromosomeIndex = parent1Chromosomes.get
			(lastCoorespondingChromosome);
		    
		    lastChromosome = parent1.getChromosome
			(lastChromosomeIndex).getId();
		    
		    child.replaceChromosome
			(lastChromosomeIndex, 
			 parent1.getChromosome
			 (lastChromosomeIndex));
		    
		    hasBeenReplaced[lastChromosomeIndex] = true;
		    filledPositions++;
					
		} else {
					
		    // if it already has, then the cycle is 
		    //  complete and we can randomly
		    //	select a parent to begin the new cycle
					
		    // first, find the next empty position
		    positionIsEmpty = false;
		    lastChromosomeIndex = 1;
		    while (!positionIsEmpty) {
						
			if (child.getChromosome
			    (lastChromosomeIndex).getCaseTest() 
			    == null) {
			    
			    positionIsEmpty = true;
			    
			} else {
			    
			    lastChromosomeIndex++;
			    
			}
						
		    }
					
		    // next, randomly select a parent to begin 
		    //  the new cycle
		    
		    lastParent = rng.nextInt(2);
					
		    if (lastParent == 0) {
						
			lastChromosome = parent0.getChromosome
			    (lastChromosomeIndex).getId();
			
			child.replaceChromosome
			    (lastChromosomeIndex, 
			     parent0.getChromosome
			     (lastChromosomeIndex));
			
			hasBeenReplaced[lastChromosomeIndex] 
			    = true;
			
			filledPositions++;
						
		    } else {
						
			lastChromosome 
			    = parent1.getChromosome
			    (lastChromosomeIndex).getId();
			
			child.replaceChromosome
			    (lastChromosomeIndex, 
			     parent1.getChromosome
			     (lastChromosomeIndex));
			
			hasBeenReplaced[lastChromosomeIndex] 
			    = true;
			
			filledPositions++;
						
		    }
					
		}
				
	    }
			
	}
		
	// perform this call so that we end up with two 
	//  children, to maintain uniformity
	//  with all other crossover operators
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
