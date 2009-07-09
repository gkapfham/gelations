/**
 * Alexander Conrad
 * gelations.Memoizer.java
 */
package gelations;

import java.util.HashMap;
/**
 * This instance class acts as a memoizer for the 
 * MetricCalculator class. Type K cooresponds 
 * to the chosen representation for orderings, while type V 
 * cooresponds to the chosen 
 * representation for fitness.
 * 
 * @author conrada
 *
 */
public class Memoizer <K,V> {
	 
    /**
     * Stores all orderings thus far encountered during the 
     * program's execution, as well as
     * the associated fitness values of each ordering. E 
     * corresponds to the representation
     * of the ordering, while F cooresponds to the representation
     * of the fitness of the 
     * ordering.
     */
    private HashMap<K,V> cache;
	
    public Memoizer() {
		
	cache = new HashMap<K,V>(100);
		
    }
	
    /**
     *  
     * @param key - the ordering for which the fitness is 
     *  requested
     * @return the fitness associated with the ordering if the 
     *  ordering is present within the Memoizer, else null
     */
    public V getFitness(K ordering) {
	return cache.get(ordering);
    }
	
    /**
     * Add a ordering-fitness mapping to the Memoizer.
     * 
     * @param ordering
     * @param fitness - the fitness associated with the ordering
     */
    public void addFitness(K ordering, V fitness) {
	cache.put(ordering, fitness);		
    }	
	
	
}
