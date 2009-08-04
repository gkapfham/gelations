/**
 * Alexander Conrad
 * gelations.InitializeParentSelector.java
 */
package gelations;

import java.util.ArrayList;

/**
 * This static class initializes and returns ParentSelectors 
 * based upon unique integer identifiers. 
 * 
 * @author conrada
 *
 */
public class InitializeParentSelector {

    /**
     * Returns a single ParentSelector by matching an int 
     * identifier with the correct 
     * cooresponding operator.
     * 
     * @param op - unique int identifier for a ParentSelector
     * @param seeds - array of seeds for the random number 
     *  generators
     * @return the operator matching the identifier
     */
    public static ParentSelector getParentSelector
	(int op, long seed) {
		
	ParentSelector parentSelector;
	switch (op) {
	case 0: parentSelector 
		= new ParentSelectorRou(seed); break;
	case 1: parentSelector 
		= new ParentSelectorTru(seed); break;
	case 2: parentSelector 
		= new ParentSelectorTou(seed); break;
	case 3: parentSelector 
	    = new ParentSelectorTou3(seed); break;
	case 4: parentSelector 
    	= new ParentSelectorTou4(seed); break;
	case 5: parentSelector 
		= new ParentSelectorTou5(seed); break;
	case 6: parentSelector 
		= new ParentSelectorTru40(seed); break;
	case 7: parentSelector 
		= new ParentSelectorTru60(seed); break;	
	case 8: parentSelector 
		= new ParentSelectorRouL(seed); break;	
	case 9: parentSelector 
		= new ParentSelectorRouE(seed); break;		
	default: System.out.println("Parent selection operator "
				    +op+" does not exist!"); 
	    parentSelector = null;
	    System.exit(1); break;
	}
		
	return parentSelector;
		
    }
	
    /**
     * Returns multiple ParentSelectors cooresponding to an 
     * array of ParentSelector
     * int identifiers. Note: if ops contains duplicates, the 
     * same operator will be duplicated
     * in the array of ParentSelectors as well.
     * 
     * @param ops - array of operator identifiers
     * @param seeds - array of seeds for the random number 
     *  generators
     * @return an array of operators matching the identifiers
     */
    public static ArrayList<ParentSelector> 
	getParentSelectors(int[] ops, int[] seeds) {
		
	ArrayList<ParentSelector> parentSelectors = 
	    new ArrayList<ParentSelector>(ops.length); 
		
	for (int i=0; i<ops.length; i++) {
			
	    parentSelectors.add(getParentSelector
				(ops[i], seeds[i]));
			
	}
		
	return parentSelectors;
		
    }
	
	
}
