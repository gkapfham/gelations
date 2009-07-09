/**
 * Alexander Conrad
 * gelations.InitializeScalingTransform.java
 */
package gelations;

/**
 * This static class initializes and returns ScalingTransforms 
 * based upon unique integer identifiers. 
 * 
 * @author conrada
 *
 */
public class InitializeScalingTransform {
	
    /**
     * Returns a single ScalingTransform by matching an int 
     * identifier with the correct cooresponding operator.
     * 
     * @param op - unique int identifier for a 
     *  ScalingTransform
     * @param seeds - array of seeds for the random number 
     *  generators
     * @return the operator matching the identifier
     */
    public static ScalingTransform getScalingTransform
	(int op, long seed) {
		
	ScalingTransform scalingTransform;
		
	switch (op) {
	case 0: scalingTransform 
		= new ScalingTransformExp(seed); break;
	case 1: scalingTransform 
		= new ScalingTransformUn(seed); break;
	case 2: scalingTransform 
		= new ScalingTransformWin(seed); break;
	    //case 3: scalingTransform 
	    // = new ScalingTransformLinN(seed); break;
	    //case 4: scalingTransform 
	    // = new ScalingTransformLinT(seed); break;
	default: System.out.println("Scaling transform "
				    +op+" does not exist!"); 
	    scalingTransform = null;
	    System.exit(1); break;
	}
	
	return scalingTransform;
	
    }
    
}
