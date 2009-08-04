/**
 * Alexander Conrad
 * gelations.Chromosome.java
 */
package gelations;

/**
 * Instance class whose objects represent the chromosomes to be 
 * used with the test suite 
 * prioritization ga. Chromosomes are combined and recombined 
 * into new Individuals by 
 * CrossoverOperators and MutationOperators. Each Chromosome 
 * represents a single CaseTest, 
 * storing a reference to the CaseTest object in order to 
 * conserve memory, preventing 
 * CaseTests from being unnecessarily duplicated.
 * 
 * @author conrada
 */
public class Chromosome {
    
    /**
     * CaseTest represented by this Chromosome.
     */
    CaseTest caseTest;
    
    /**
     * Creates a new Chromosome representing and containing 
     * a reference to a single CaseTest
     * object. Note: constructor should only be called by 
     * CreateInitialChromosomes.java. Note:
     * only CaseTests whose fields have already been 
     * completely filled should be used to
     * create Chromosomes. Using incomplete CaseTest 
     * objects may result in unpredictable 
     * behavior.
     * 
     * @param _caseTest
     */
    public Chromosome(CaseTest _caseTest) {
	
	caseTest = _caseTest;
	
    }
    
    /**
     * Creates a dummy Chromosome for filling space. 
     * Note: dummy Chromosomes should have no
     * getter methods called upon them, as these methods 
     * may well throw exceptions. Dummy
     * Chromosomes should only be used as placeholders.
     *
     */
    public Chromosome() {
	caseTest = null;
    }
    
    /**
     * 
     * @return the execution time of the test case 
     *  represented by the chromosome
     */
    public double getTime() {
	return caseTest.getTime();
    }
    
    /**
     * 
     * @return the list of requirements covered by the 
     *  test case represented by the chromosome
     */
    public int[] getRequirements() {
	return caseTest.getRequirements();
    }
    
    /**
     * 
     * @return the unique identifier for the test case 
     *  represented by the chromosome
     */
    public int getId() {
	return caseTest.getId();
    }
    
    /**
     * 
     * @return the CaseTest to which this Chromosome refers
     */
    public CaseTest getCaseTest() {
	return caseTest;
    }
    
    /**
     * This method should be used instead of a constructor 
     * to create new Chromosomes in all
     * locations except for CreateInitialChromosomes.java.
     * 
     * @return a defensive copy of this Chromosome
     */
    public static Chromosome copy(Chromosome chromosome) {
	return new Chromosome(chromosome.getCaseTest());
    }
    
}
