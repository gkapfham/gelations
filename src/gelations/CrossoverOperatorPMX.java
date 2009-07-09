/**
 * Alexander Conrad
 * gelations.CrossoverOperatorPMX.java
 */
package gelations;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class performs crossover using the partially-mapped 
 * crossover operator. See "Genetic
 * Algorithms for the Travelling Salesman Problem: A Review of 
 * Representations and Operators"
 * for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class CrossoverOperatorPMX extends CrossoverOperator {
	
    private int substringLength;
	
    public static final int DEFAULT_SUBSTRING_LENGTH = 3;
	
    public CrossoverOperatorPMX(int _substringLength, long seed) {
		
	super(seed);
	substringLength = _substringLength;
		
    }
	
    public CrossoverOperatorPMX(int _substringLength) {
		
	super();
	substringLength = _substringLength;
		
    }

    public CrossoverOperatorPMX(long seed) {
	
	super(seed);
	substringLength = DEFAULT_SUBSTRING_LENGTH;
	
    }

    public CrossoverOperatorPMX() {
	
	super();
	substringLength = DEFAULT_SUBSTRING_LENGTH;
	
    }
	
    public ArrayList<Individual> performCrossover
	(Individual parent1, Individual parent2) {

	int length = parent1.size();
		
	// remove this section if we decide to manually specify 
	//  subtourLength
	//	length/4 <= subtoutLength <= length/2
	substringLength = rng.nextInt(length/4+1) + length/4;
		
	int cut1, cut2, tempIndex;
	// contains mappings for child i; if an id key is 
	//  encountered that is already present,
	//  assign the Chromosome from parent i at the index 
	//  value to the child i.
	HashMap<Integer,Integer> child1Chromosomes 
	    = new HashMap<Integer,Integer>
	    ((int)(length * 1.25), (float)1.0);
	
	HashMap<Integer,Integer> child2Chromosomes 
	    = new HashMap<Integer,Integer>
	    ((int)(length * 1.25), (float)1.0);
	
	Individual child1 = new Individual(length);
	Individual child2 = new Individual(length);
	Chromosome chromosome1, chromosome2;
		
	CaseTest dummyCase = new CaseTest(-1);
	Chromosome dummy = new Chromosome(dummyCase);
				
	// fill the children with dummy chromosomes
	for (int i=0; i<length; i++) {
			
	    child1.addChromosome(dummy);
	    child2.addChromosome(dummy);
			
	}
		
	cut1 = rng.nextInt(length-substringLength);
	cut2 = cut1 + substringLength;
		
	//debug
	//System.out.println("cut1:"+cut1+" substringLength:"
	// +substringLength);
		
	// read the mapping sections and copy them into the 
	//  oppposite children
	for (int i=0; i<substringLength; i++) {
			
	    chromosome1 = Chromosome.copy
		(parent1.getChromosome(cut1+i));
	    
	    chromosome2 = Chromosome.copy
		(parent2.getChromosome(cut1+i));
			
	    child1Chromosomes.put(chromosome2.getId(), cut1+i);
	    child2Chromosomes.put(chromosome1.getId(), cut1+i);
			
	    child1.replaceChromosome(cut1+i, chromosome2);
	    child2.replaceChromosome(cut1+i, chromosome1);
			
	}
		
		
		
	// handle the first unfilled section of the children
	if (cut1 > 0) {
			
	    for (int i=0; i<cut1; i++) {
				
		chromosome1 = parent1.getChromosome(i);
		chromosome2 = parent2.getChromosome(i);
				
		// if chromosome i is already present within 
		//  child 1 (that is, its unique id
		//  already present as a key within the HashMap),
		//  look at parent 1 and
		//  add the chromosome at the associated value 
		//  index in the HashMap to child 1 instead
		if (child1Chromosomes.containsKey
		    (chromosome1.getId())) {
		    
		    do {
			
			tempIndex = child1Chromosomes.get
			    (chromosome1.getId());
			
			chromosome1 = parent1.getChromosome
			    (tempIndex);
						
		    } while (child1Chromosomes.containsKey
			     (chromosome1.getId()));
					
		    child1.replaceChromosome(i, chromosome1);
					
		}
		// else add chromosome i to child j
		else {
					
		    child1.replaceChromosome(i, chromosome1);
					
		}
				
		// reapeat for child2
		if (child2Chromosomes.containsKey
		    (chromosome2.getId())) {
					
		    do {
						
			tempIndex = child2Chromosomes.get
			    (chromosome2.getId());
			
			chromosome2 = parent2.getChromosome
			    (tempIndex);
						
		    } while (child2Chromosomes.containsKey
			     (chromosome2.getId()));
					
		    child2.replaceChromosome(i, chromosome2);
					
		} else {
					
		    child2.replaceChromosome(i, chromosome2);
					
		}
				
				
	    }
			
	}
		
	// handle the second unfilled section of the children
	if (cut2 < length) {
			
	    for (int i=cut2; i<length; i++) {
				
		chromosome1 = parent1.getChromosome(i);
		chromosome2 = parent2.getChromosome(i);
				
		// if chromosome i is already present within 
		//  child 1 (that is, its unique id
		//  already present as a key within the HashMap),
		//  look at parent 1 and
		//  add the chromosome at the associated value 
		//  index in the HashMap to child 1 instead
		if (child1Chromosomes.containsKey
		    (chromosome1.getId())) {
					
		    do {
			
			tempIndex = child1Chromosomes.get
			    (chromosome1.getId());
			
			chromosome1 = parent1.getChromosome
			    (tempIndex);
						
		    } while (child1Chromosomes.containsKey
			     (chromosome1.getId()));
					
		    child1.replaceChromosome(i, chromosome1);
					
		}
		// else add chromosome i to child j
		else {
					
		    child1.replaceChromosome(i, chromosome1);
					
		}
				
		// reapeat for child2
		if (child2Chromosomes.containsKey
		    (chromosome2.getId())) {
					
		    do {
						
			tempIndex = child2Chromosomes.get
			    (chromosome2.getId());
			
			chromosome2 = parent2.getChromosome
			    (tempIndex);
						
		    } while (child2Chromosomes.containsKey
			     (chromosome2.getId()));
					
		    child2.replaceChromosome(i, chromosome2);
					
		} else {
					
		    child2.replaceChromosome(i, chromosome2);
					
		}
				
	    }
			
	}
		
	ArrayList<Individual> children 
	    = new ArrayList<Individual>();
	
	children.add(child1);
	children.add(child2);
		
	return children;
	
    }
    
    
}