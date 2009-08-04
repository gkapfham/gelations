/**
 * Alexander Conrad
 * gelations.InitializeCrossoverOperators.java
 */
package gelations;

import java.util.ArrayList;
/**
 * This static class instantiates and returns a 
 * CrossoverOperator based upon a unique integer identifier.
 * 
 * @author conrada
 *
 */
public class InitializeCrossoverOperators {
	
    /**
     * Returns a single CrossoverOperator by matching an int 
     * identifier with the correct cooresponding operator.
     * 
     * @param op - unique int identifier for a CrossoverOperator
     * @param seeds - array of seeds for the random number 
     *  generators
     * @return the operator matching the identifier
     */
    public static CrossoverOperator getCrossoverOperator
	(int op, long seed) {
	
	CrossoverOperator crossoverOperator;
	switch (op) {
	case 0: crossoverOperator 
		= new CrossoverOperatorCX(seed); break;
	case 1: crossoverOperator 
		= new CrossoverOperatorMPX(seed); break;
	case 2: crossoverOperator 
		= new CrossoverOperatorOX1(seed); break;
	case 3: crossoverOperator 
		= new CrossoverOperatorOX2(seed); break;
	case 4: crossoverOperator 
		= new CrossoverOperatorPMX(seed); break;
	case 5: crossoverOperator 
		= new CrossoverOperatorPOS(seed); break;
	case 6: crossoverOperator 
		= new CrossoverOperatorVR(seed); break;
	default: System.out.println
		("Crossover operator "+op+" does not exist!"); 
	    crossoverOperator = null;
	    System.exit(1); break;
	}
		
	return crossoverOperator;
		
    }
	
    /**
     * Returns multiple CrossoverOperators cooresponding to an 
     * array of CrossoverOperator
     * int identifiers. Note: if ops contains duplicates, 
     * the same operator will be duplicated
     * in the array of CrossoverOperators as well.
     * 
     * @param ops - array of operator identifiers
     * @param seeds - array of seeds for the random number 
     *  generators
     * @return an array of operators matching the identifiers
     */
    public static ArrayList<CrossoverOperator> 
	getCrossoverOperators(int[] ops, int[] seeds) {
	
	ArrayList<CrossoverOperator> crossoverOperators = 
	    new ArrayList<CrossoverOperator>(ops.length); 
	
	for (int i=0; i<ops.length; i++) {
			
	    crossoverOperators.add(getCrossoverOperator
				   (ops[i], seeds[i]));
			
	}
		
	return crossoverOperators;
		
    }
	
}