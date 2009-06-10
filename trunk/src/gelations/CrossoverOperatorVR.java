/**
 * Alexander Conrad
 * gelations.CrossoverOperatorVR.java
 */
package gelations;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class performs crossover using the voting recombination 
 * crossover operator. See 
 * "Genetic Algorithms for the Travelling Salesman Problem: A 
 * Review of Representations and 
 * Operators" for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class CrossoverOperatorVR extends CrossoverOperator {
		
    /**
     * Number of parents to be involved in the production of a 
     * single child.
     */
    //private int numParents;
    /**
     * Number of votes necessary in order for a Chromosome to be 
     * passed onto a child.
     */
    //private int threshold;
	
    private boolean hasCrossedOverAgain;
	
    /*
     * This section serves no purpose unless we are working with 
     * more than 2 parents
	 
     public static final int DEFAULT_THRESHOLD = 3;
     public static final int DEFAULT_NUM_PARENTS = 4;
	
     public CrossoverOperatorVR(int _numParents, int _threshold, 
     long seed) {
		
     super(seed);
     numParents = _numParents;  
     threshold =  _threshold;
		
     }
	
     public CrossoverOperatorVR(int _numParents,  
     int _threshold) {
		
     super();
     numParents = _numParents;  
     threshold =  _threshold;
		
     }

     public CrossoverOperatorVR(long seed) {
	
     super(seed);
     numParents = DEFAULT_NUM_PARENTS;
     threshold = DEFAULT_THRESHOLD;
		
     }

     public CrossoverOperatorVR() {
	
     super();
     numParents = DEFAULT_NUM_PARENTS;
     threshold = DEFAULT_THRESHOLD;
	
     }
	
    */
	
    public CrossoverOperatorVR(long seed) {
		
	super(seed);
	hasCrossedOverAgain = false;
		
    }
	
    public ArrayList<Individual> performCrossover
	(Individual parent1, Individual parent2) {

	int length = parent1.size();
		
	// each entry in the hashmap cooresponds to a 
	//  value,index pair within the child
	HashMap<Integer,Integer> childChromosomes 
	    = new HashMap<Integer,Integer>
	    ((int)(length * 1.25), (float)1.0);
	
	Individual child = new Individual(length);
	Chromosome dummy = new Chromosome();
	Chromosome nextChrom;
		
	int filledChroms = 0;
		
	for (int i=0; i<length; i++) {
			
	    child.addChromosome(dummy);
			
	}
		
	// if a chromosome appears in the same location in both 
	//  parents, copy it into the child
	for (int i=0; i<length; i++) {
			
	    nextChrom = parent1.getChromosome(i);
	    if (nextChrom.getId() 
		== parent2.getChromosome(i).getId()) {
		
		child.replaceChromosome(i, nextChrom);
		childChromosomes.put(nextChrom.getId(), i);
		filledChroms++;
		
	    }
	    
	}
		
	// fill in the remaining locations with 
	//  randomly-selected chromosomes
	for (int i=0; i<length; i++) {
			
	    // if the current index hasn't yet been filled in, 
	    //  randomly choose a chromosome
	    //	that is not yet present to put there
	    if (!childChromosomes.containsValue(i)) {
				
		do {
					
		    nextChrom = parent1.getChromosome
			(rng.nextInt(length));
					
		} while (childChromosomes.containsKey
			 (nextChrom.getId()));
				
		child.replaceChromosome(i, nextChrom);
		childChromosomes.put(nextChrom.getId(), i);
				
	    }
			
	}
		
	ArrayList<Individual> children;
	if (!hasCrossedOverAgain) {
	    hasCrossedOverAgain = true;
	    children = performCrossover(parent2, parent1);
	    hasCrossedOverAgain = false;
	} else {
	    children = new ArrayList<Individual>();
	}
		
	children.add(child);
	return children;
		
    }
	
    /*
     * these methods will not be needed unless VR is implemented 
     * with numParents > 2
     * 
     public ArrayList<Individual> createChildren
     (ArrayList<Individual> parents) {
     }
	
	
     public ArrayList<Individual> performCrossover
     (ArrayList<Individual> parents, Random rng) {
     }
     
    */
    
}
