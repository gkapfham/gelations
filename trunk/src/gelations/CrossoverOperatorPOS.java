/**
 * Alexander Conrad
 * gelations.CrossoverOperatorPOS.java
 */
package gelations;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class performs crossover using the position based 
 * crossover operator. See "Genetic
 * Algorithms for the Travelling Salesman Problem: A Review of 
 * Representations and Operators"
 * for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class CrossoverOperatorPOS extends CrossoverOperator {
	
    /**
     * Number of positions to be imposed upon the opposite child.
     */
    private int positions;
	
    public static final int DEFAULT_POSITIONS = 3;
	
    public CrossoverOperatorPOS(int _positions, long seed) {
		
	super(seed);
	positions = _positions;
		
    }
	
    public CrossoverOperatorPOS(int _positions) {
		
	super();
	positions = _positions;
		
    }

    public CrossoverOperatorPOS(long seed) {
	
	super(seed);
	positions = DEFAULT_POSITIONS;
		
    }

    public CrossoverOperatorPOS() {
	
	super();
	positions = DEFAULT_POSITIONS;
	
    }
	
    public ArrayList<Individual> performCrossover
	(Individual parent0, Individual parent1) {

	int length = parent0.size();
		
	// remove this section if we decide to manually specify 
	// subtourLength
	//	length/4 <= subtoutLength <= length/2
	positions = rng.nextInt(length/4+1) + length/4;
		
	// HashMaps store mapping of chromosome id, index in child
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
		
	int nextChromIndex, nextChromIndex0, nextChromIndex1;
		
	//ArrayList<Integer> openSlots 
	// = new ArrayList<Integer>(length);
	for (int i=0; i<length; i++) {
			
	    //openSlots.set(i, i);
	    child0.addChromosome(dummy);
	    child1.addChromosome(dummy);
			
	}
		
	// first, randomly select the positions to be imposed by 
	//  opposite parents and
	//  copy in the cooresponding elements
	//  note: the only difference between OX2 and POS: OX2 
	//  iterates from 0 to 
	//  length-positions in order to fill in all but the 
	//  selected positions; 
	//   POS iterates from 0 to positions to fill in only 
	//   the selected positions from the opposite parent.
	for (int i=0; i<positions; i++) {
			
	    // make sure that the index has not already been 
	    //  used for a position
	    do {
		nextChromIndex = rng.nextInt(length);
	    } while (child0Chromosomes.containsValue
		     (nextChromIndex));
			
	    nextChrom0 = parent0.getChromosome(nextChromIndex);
	    nextChrom1 = parent1.getChromosome(nextChromIndex);
			
	    //debug:
	    //System.out.println(nextChromIndex+" "
	    // +nextChrom0.getId()+" "+nextChrom1.getId());
			
	    child0.replaceChromosome(nextChromIndex, nextChrom1);
	    child1.replaceChromosome(nextChromIndex, nextChrom0);
			
	    child0Chromosomes.put(nextChrom1.getId(), 
				  nextChromIndex);
	    child1Chromosomes.put(nextChrom0.getId(), 
				  nextChromIndex);
			
	    //openSlots.remove(nextChromIndex);
			
	}

	nextChromIndex0 = 0;
	nextChromIndex1 = 0;
		
	// next, fill in the missing elements according to the 
	//  order in which they are present within the 
	//  coresponding parent
	for (int i=0; i<length; i++) {
			
	    nextChrom0 = parent0.getChromosome(i);
	    nextChrom1 = parent1.getChromosome(i);
			
	    // if Chromosome has not yet been used in child, 
	    //  put it in the next openSlot
	    if (!child0Chromosomes.containsKey
		(nextChrom0.getId())) {
				
		//debug
		//System.out.println(child0Chromosomes
		// .containsValue(nextChromIndex0));
				
		while (child0Chromosomes.containsValue
		       (nextChromIndex0)) {
		    nextChromIndex0++;
		}
				
		//debug
		//System.out.println("child 0: index:"
		// +nextChromIndex0+" chrom:"+nextChrom0.getId());
				
		//child0.replaceChromosome(openSlots.remove(0), 
		// nextChrom0);
		child0.replaceChromosome
		    (nextChromIndex0, nextChrom0);
		
		child0Chromosomes.put
		    (nextChrom0.getId(), nextChromIndex0);
		
		nextChromIndex0++;
				
	    }
			
	    if (!child1Chromosomes.containsKey
		(nextChrom1.getId())) {
				
		//debug
		//System.out.println(child1Chromosomes
		// .containsValue(nextChromIndex1));

		while (child1Chromosomes.containsValue
		       (nextChromIndex1)) {
		    nextChromIndex1++;
		}
				
		//debug
		//System.out.println("child 1: index:"
		// +nextChromIndex1+" chrom:"+nextChrom1.getId());
				
		//child1.replaceChromosome
		// (openSlots.remove(1), nextChrom1);
		
		child1.replaceChromosome
		    (nextChromIndex1, nextChrom1);
		
		child1Chromosomes.put
		    (nextChrom1.getId(), nextChromIndex1);
		
		nextChromIndex1++;
				
	    }
			
	}
		
		
	ArrayList<Individual> children 
	    = new ArrayList<Individual>(2);
	
	children.add(child0);
	children.add(child1);
	return children;
	
    }
    
}
