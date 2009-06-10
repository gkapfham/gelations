/**
 * Alexander Conrad
 * gelations.CrossoverOperatorOX1.java
 */
package gelations;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class performs crossover using the order crossover 
 * operator. See "Genetic
 * Algorithms for the Travelling Salesman Problem: A Review of 
 * Representations and Operators"
 * for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class CrossoverOperatorOX1 extends CrossoverOperator {
	
    /**
     * Length, in elements, of subtour to be selected.
     */
    private int subtourLength;
	
    public static final int DEFAULT_SUBTOUR_LENGTH = 3;
	
    public CrossoverOperatorOX1(int _subtourLength, long seed) {
		
	super(seed);
	subtourLength = _subtourLength;
		
    }
	
    public CrossoverOperatorOX1(int _subtourLength) {
		
	super();
	subtourLength = _subtourLength;
		
    }

    public CrossoverOperatorOX1(long seed) {
	
	super(seed);
	subtourLength = DEFAULT_SUBTOUR_LENGTH;
		
    }

    public CrossoverOperatorOX1() {
	
	super();
	subtourLength = DEFAULT_SUBTOUR_LENGTH;
	
    }
	
    public ArrayList<Individual> performCrossover
	(Individual parent0, Individual parent1) {

	int length = parent0.size();
		
	// remove this section if we decide to manually specify 
	//  subtourLength
	//	length/4 <= subtoutLength <= length/2
	subtourLength = rng.nextInt(length/4+1) + length/4;
		
	HashMap<Integer,Integer> child0Chromosomes 
	    = new HashMap<Integer,Integer>
	    ((int)(length * 1.25), (float)1.0);
	
	HashMap<Integer,Integer> child1Chromosomes 
	    = new HashMap<Integer,Integer>
	    ((int)(length * 1.25), (float)1.0);
		
	Individual child0 = new Individual(length);
	Individual child1 = new Individual(length);
		
	CaseTest dummyCase = new CaseTest(-1);
		
	Chromosome dummy = new Chromosome(dummyCase);
	Chromosome nextChrom0, nextChrom1;
		
	int cut1, cut2, posChild0, posChild1;
		
	// first, fill the individuals with dummy chromosomes to 
	//  be replaced later
	for (int i=0; i<length; i++) {
						
	    child0.addChromosome(dummy);
	    child1.addChromosome(dummy);
			
	}
		
	// next, select and copy subtours
	cut1 = rng.nextInt(length-subtourLength);
	cut2 = cut1 + subtourLength;
		
	for (int i=0; i<subtourLength; i++) {
			
	    nextChrom0 = parent0.getChromosome(cut1+i);
	    nextChrom1 = parent1.getChromosome(cut1+i);
			
	    child0.replaceChromosome(cut1+i, nextChrom0);
	    child1.replaceChromosome(cut1+i, nextChrom1);
			
	    child0Chromosomes.put(nextChrom0.getId(), cut1+i);
	    child1Chromosomes.put(nextChrom1.getId(), cut1+i);
			
	}
		
	// last, fill in elements based on the ordering of the 
	//  opposite parent, starting from the second cut point
	posChild0 = cut2;
	posChild1 = cut2;
		
	for (int i=0; i<length; i++) {
			
	    nextChrom0 = parent1.getChromosome((cut2+i)%length);
	    nextChrom1 = parent0.getChromosome((cut2+i)%length);
			
	    // if Chromosome has not yet been added to child0, 
	    //  add it to child0 and increment posChild0
	    if (!child0Chromosomes.containsKey
		(nextChrom0.getId())) {
				
		child0.replaceChromosome
		    (posChild0 % length, nextChrom0);
		posChild0++;
				
	    }
			
	    // if Chromosome has not yet been added to child1, 
	    //  add it to child1 and increment posChild1
	    if (!child1Chromosomes.containsKey
		(nextChrom1.getId())) {
				
		child1.replaceChromosome
		    (posChild1 % length, nextChrom1);
		
		posChild1++;
				
	    }
			
	}
		
	ArrayList<Individual> children 
	    = new ArrayList<Individual>();
	
	children.add(child0);
	children.add(child1);
	return children;
		
    }
	
}
