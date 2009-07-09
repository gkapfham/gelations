/**
 * Alexander Conrad
 * gelations.InitializeMutationOperators.java
 */
package gelations;

import java.util.ArrayList;
/**
 * This static class instantiates and returns a MutationOperator 
 * based upon a unique 
 * integer identifier.
 * 
 * @author conrada
 *
 */
public class InitializeMutationOperators {

    /**
     * Returns a single MutationOperator by matching an int 
     * identifier with the correct 
     * cooresponding operator.
     * 
     * @param op - unique int identifier for a MutationOperator
     * @param seeds - array of seeds for the random number 
     *  generators
     * @return the operator matching the identifier
     */
    public static MutationOperator getMutationOperator
	(int op, long seed) {
		
	MutationOperator mutationOperator;
	switch (op) {
	case 0: mutationOperator 
		= new MutationOperatorDM(seed); break;
	case 1: mutationOperator 
		= new MutationOperatorEM(seed); break;
	case 2: mutationOperator 
		= new MutationOperatorISM(seed); break;
	case 3: mutationOperator 
		= new MutationOperatorIVM(seed); break;
	case 4: mutationOperator 
		= new MutationOperatorSIM(seed); break;
	case 5: mutationOperator 
		= new MutationOperatorSM(seed); break;
	default: System.out.println("Mutation operator "
				    +op+" does not exist!"); 
	    mutationOperator = null;
	    System.exit(1); break;
	}
		
	return mutationOperator;
		
    }
	
    /**
     * Returns multiple MutationOperators cooresponding to an 
     * array of MutationOperator
     * int identifiers. Note: if ops contains duplicates, the 
     * same operator will be duplicated
     * in the array of MutationOperators as well.
     * 
     * @param ops - array of operator identifiers
     * @param seeds - array of seeds for the random number 
     *  generators
     * @return an array of operators matching the identifiers
     */
    public static ArrayList<MutationOperator> 
	getMutationOperators(int[] ops, int[] seeds) {
		
	ArrayList<MutationOperator> mutationOperators = 
	    new ArrayList<MutationOperator>(ops.length); 
		
	for (int i=0; i<ops.length; i++) {
			
	    mutationOperators.add(getMutationOperator
				  (ops[i], seeds[i]));
			
	}
		
	return mutationOperators;
		
    }
	
	
}
