/**
 * Alexander Conrad
 * gelations.CrossoverOperatorDummy.java
 */
package gelations;

import java.util.ArrayList;

/**
 * Dummy class to aid in testing of CrossoverOperator.
 * 
 * @author conrada
 *
 */
public class CrossoverOperatorDummy extends CrossoverOperator {

    public CrossoverOperatorDummy(long seed) {
		
	super(seed);
		
    }
	
    /* (non-Javadoc)
     * @see ga.CrossoverOperator#performCrossover(ga.Individual, 
     *  ga.Individual)
     */
    @Override
	public ArrayList<Individual> performCrossover
	(Individual parent1, Individual parent2) {
	
	ArrayList<Individual> children = new ArrayList<Individual>();
	children.add(parent1);
	children.add(parent2);
	return children;
	
    }

}
