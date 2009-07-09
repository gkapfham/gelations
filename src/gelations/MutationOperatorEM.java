/**
 * Alexander Conrad
 * gelations.MutationOperatorEM.java
 */
package gelations;

/**
 * This class performs mutation using the exchange mutation 
 * operator. See "Genetic
 * Algorithms for the Travelling Salesman Problem: A Review of 
 * Representations and Operators"
 * for a more detailed description of the operator. 
 * 
 * @author conrada
 *
 */
public class MutationOperatorEM extends MutationOperator {
    
    /**
     * Number of exchanges to be made in the genome per mutation.
     */
    private int exchanges;
    
    public static final int DEFAULT_EXCHANGES = 1;
    
    public MutationOperatorEM(int _exchanges, long seed) {
	
	super(seed);
	exchanges = _exchanges;
		
    }
	
    public MutationOperatorEM(int _exchanges) {
	
	super();
	exchanges = _exchanges;
	
    }
    
    public MutationOperatorEM(long seed) {
	
	super(seed);
	exchanges= DEFAULT_EXCHANGES;
	
    }
    
    public MutationOperatorEM() {
		
	super();
	exchanges = DEFAULT_EXCHANGES;
		
    }
    
    public Individual mutateIndividual(Individual _individual) {
		
	Chromosome tempChrom;
	int swap1, swap2, length;
		
	length = _individual.size();
	Individual individual 
	    = new Individual(_individual.getChromosomes());
		
	for (int i=0; i<exchanges; i++) {
	    
	    // choose unique swap points
	    swap1 = rng.nextInt(length);
	    do {
		swap2 = rng.nextInt(length);
	    } while (swap1 == swap2);
	    
	    // swap the Chromosomes in the two positions
	    tempChrom = individual.getChromosome(swap1);
	    individual.replaceChromosome
		(swap1, individual.getChromosome(swap2));
	    individual.replaceChromosome(swap2, tempChrom);
	    
	}
	
	return individual;
	
    }
    
}
