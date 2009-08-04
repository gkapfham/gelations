/**
 * Alexander Conrad
 * gelations.ParentSelector.java
 */
package gelations;

import java.util.Random;
import java.util.ArrayList;

/**
 * @author conrada
 *
 */
public abstract class ParentSelector {

    public Random rng;
	
    public ParentSelector(long seed) {
		
	rng = new Random(seed);
		
    }
	
    public ParentSelector() {
		
	rng = new Random();
		
    }
	
	
    /**
     * Select a number of pairs of parent Individuals to be used 
     * later for recombination. 
     * 
     * @param population - the Population containing potential 
     *  parents
     * @param ratio - size ratio of original population to the 
     *  parent population
     * @return a list conatining pairs of parent Individuals
     */
    public abstract ArrayList<Individual> chooseParents
	(Population population, double ratio);
	
    /**
     * Select a number of pairs of parent Individuals to be used 
     * later for recombination,
     * where the size of the population of parents is 
     * approximately equal to the size of
     * the original population. Because very fit individuals will
     * probably be chosen to be
     * parents more than once, this does not mean that every 
     * individual from the original
     * population will also be present in the population of 
     * parents. The idea behind the 
     * 1-to-1 ratio is that, once the final population has been 
     * created by crossover, 
     * mutation, reduction, and merging of the child and previous
     * populations, half of the 
     * new population will be composed of members of the previous
     *  population, while the other
     * half will be composed of children.
     * 
     * @see chooseParents(Population population, double ratio);
     */
    public ArrayList<Individual> chooseParents
	(Population population) {
	return chooseParents(population, 0.5);
    }
	
}
