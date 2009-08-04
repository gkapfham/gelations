/**
 * Alexander Conrad
 * gelations.ScalingTransformUn.java
 */
package gelations;

/**
 * Dummy class that performs no transformation upon the fitness 
 * of individuals. This class is
 * necessary to allow Prioritizer to be run upon unmodified 
 * fitness values.
 * 
 * @author conrada
 *
 */
public class ScalingTransformUn extends ScalingTransform {

    public ScalingTransformUn (long seed) {
		
	super(seed);
		
    }
	
    public Individual transformIndividual(Individual individual) {
		
	return individual;
    }
	
}
