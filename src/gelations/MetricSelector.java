/**
 * Alexander Conrad
 * gelations.MetricSelector.java
 */
package gelations;

/**
 * This static class selects and instantiates a calculator for a 
 * given fitness metric based upon an integer identifier.
 * 
 * @author conrada
 *
 */
public class MetricSelector {

	
    /**
     * Instantiates a calculator for a given fitness metric 
     * based upon an int identifier.
     * 
     * @param calc - int identifier
     * @return the cooresponding fitness calculator
     */
    public static MetricCalculator getFitnessCalculator
	(int calc) {
		
	MetricCalculator fitnessCalculator;
		
	switch (calc) {
	case 0: fitnessCalculator = new MetricCalculatorCE(); 
	    break;
		
	default: System.out.println
		("Invalid fitness metric selection!");
	    fitnessCalculator = null;
	    System.exit(1);
	}
		
	return fitnessCalculator;
		
    }
	
}
