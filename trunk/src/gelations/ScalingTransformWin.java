/**
 * Alexander Conrad
 * gelations.ScalingTransformWin.java
 */
package gelations;

/**
 * @author conrada
 *
 */
public class ScalingTransformWin extends ScalingTransform {

    public ScalingTransformWin(long seed) {
		
	super(seed);
		
    }
	
    public Individual transformIndividual(Individual individual) {
		
	double newFitness 
	    = individual.getFitness() - minFitness + 0.01;
	individual.setFitness(newFitness);
	return individual;
		
    }
	
}
